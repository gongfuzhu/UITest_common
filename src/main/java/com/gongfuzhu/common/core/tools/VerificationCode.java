package com.gongfuzhu.common.core.tools;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.showdoc.com.cn/1723788762180300/8055380618566522
 */

public class VerificationCode {

    /**
     * 登录获取token
     *
     * @return
     */
    public  String login() {

        String requesGet = HttpClientTool.requesGet("http://api.weilai.best/login?username=gongfuzhu&password=admin123456");
        String token = null;
        try {
            JSONObject jsonObject = new JSONObject(requesGet);
            Assert.isTrue(jsonObject.get("code").equals("ok"), "业务异常");
            token = (String) jsonObject.get("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * 账户剩余额度
     *
     * @param token
     * @return
     */
    public  String getmoney(String token) {

        String url = String.format("http://api.weilai.best/getmoney?token=%s", token);
        String requesGet = HttpClientTool.requesGet(url);
        String account = null;
        try {
            JSONObject jsonObject = new JSONObject(requesGet);
            Assert.isTrue(jsonObject.get("code").equals("ok"), "业务异常");
            account = (String) jsonObject.get("money");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return account;

    }

    /**
     * {"code":"ok","requestid":"236573","phone":"15607045713","residual":"149"}
     * @param token
     * @return
     */
    public  String getPhone(String token,String productId) throws JSONException {


        String url = "http://app.weilai.best/getphone?token=" + token + "&id="+productId+"&operator=1&province=%E4%B8%8D%E9%99%90&phone=&autoblack=true";

        String requesGet = HttpClientTool.requesGet(url);
            JSONObject jsonObject = new JSONObject(requesGet);
            String residual = (String) jsonObject.get("residual");
            Assert.isTrue(!residual.equals("0"), "residual 值为0 停止使用");

        return requesGet;

    }

    public  String getmessage(String token, String requestid) throws JSONException {

        String url = String.format("http://api.weilai.best/getmessage?token=%s&requestid=%s", token, requestid);
        for (int i = 0; i < 10; i++) {
            String requesGet = HttpClientTool.requesGet(url);
            JSONObject jsonObject = new JSONObject(requesGet);
            if (jsonObject.get("code").equals("ok") && !String.format((String) jsonObject.get("sms")).isEmpty()) {
                return String.format((String) jsonObject.get("sms"));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public  Map<String, String> aiwenzhe() throws JSONException {
        String token = login();
        String phone = getPhone(token,"22819");
        JSONObject jsonObject = new JSONObject(phone);
        Object requestid = jsonObject.get("requestid");
        Object no = jsonObject.get("phone");
        System.out.println("电话："+no);
        String getmessage = getmessage(token, String.valueOf(requestid));
        String code = code(getmessage, 6);
       return Map.of("phone",String.valueOf(no),"code",code);
    }

//    public static void main(String[] args) throws JSONException {
//        Map<String, String> aiwenzhe = aiwenzhe();
//        System.out.println(aiwenzhe.get("phone"));
//        System.out.println(aiwenzhe.get("code"));
//    }
    private  String code(String body, int lenth) {
        Pattern p = Pattern.compile("(?<![0-9])([0-9]{" + lenth + "})(?![0-9])");
        Matcher m = p.matcher(body);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }
}
