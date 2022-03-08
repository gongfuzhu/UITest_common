package com.gongfuzhu.common.core.tools;

import com.jayway.jsonpath.JsonPath;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.showdoc.com.cn/1723788762180300/8055380618566522
 */
@Component
@Log4j2
public class VerificationCode {

    /**
     * 登录获取token
     *
     * @return
     */
    public static String login(String account, String pwd) {

        String requesGet = HttpClientTool.requesGet(String.format("http://api.weilai.best/login?username=%s&password=%s", account, pwd));
        String token = null;
        token = JsonPath.read(requesGet, "$.token");
        Assert.isTrue(JsonPath.read(requesGet, "$.code").equals("ok"), "业务异常");

        return token;
    }

    /**
     * 账户剩余额度
     *
     * @param token
     * @return
     */
    public static String getmoney(String token) {

        String url = String.format("http://api.weilai.best/getmoney?token=%s", token);
        String requesGet = HttpClientTool.requesGet(url);
        String account = null;
        Assert.isTrue(JsonPath.read(requesGet, "$.code").equals("ok"), "业务异常");
        account = JsonPath.read(requesGet, "$.money");
        return account;

    }

    /**
     * {"code":"ok","requestid":"236573","phone":"15607045713","residual":"149"}
     * 获取号码
     *
     * @param token
     * @return
     */
    public static String getPhone(String token, String productId) {


        String url = "http://app.weilai.best/getphone?token=" + token + "&id=" + productId + "&operator=1&province=%E4%B8%8D%E9%99%90&phone=&autoblack=true";

        String requesGet = HttpClientTool.requesGet(url);
        Assert.isTrue(!JsonPath.read(requesGet, "$.residual").equals("0"), "residual 值为0 停止使用");

        return requesGet;

    }

    /**
     * 获取短信
     *
     * @param token
     * @param requestid
     * @return
     */
    public static String getmessage(String token, String requestid) {

        String url = String.format("http://api.weilai.best/getmessage?token=%s&requestid=%s", token, requestid);
        for (int i = 0; i < 20; i++) {
            String requesGet = HttpClientTool.requesGet(url);
            if (JsonPath.read(requesGet, "$.code").equals("ok") && !JsonPath.read(requesGet, "$.sms").equals("")) {
                return JsonPath.read(requesGet, "$.sms");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        freePhone(token, requestid);
        return null;
    }

    /**
     * 释放号码
     *
     * @param token
     * @param requestid
     */
    public static void freePhone(String token, String requestid) {
        log.info("释放手机号");
        String url = String.format("http://api.weilai.best/freephone?token=%s&requestid=%s", token, requestid);
        HttpClientTool.requesGet(url);

    }

    /**
     * 获取验证码
     *
     * @param body
     * @param lenth
     * @return
     */
    public static String code(String body, int lenth) {
        Pattern p = Pattern.compile("(?<![0-9])([0-9]{" + lenth + "})(?![0-9])");
        Matcher m = p.matcher(body);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }
}
