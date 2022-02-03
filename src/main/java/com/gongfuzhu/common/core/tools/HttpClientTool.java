package com.gongfuzhu.common.core.tools;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Log4j2
public class HttpClientTool {




    @SneakyThrows
    public static String requesPost(String url, String body, Map<String, String> header) {

        CloseableHttpClient client = HttpClients.createDefault();
        StringEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);


        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
            httpPost.setHeader(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        httpPost.setEntity(stringEntity);
        System.out.println(EntityUtils.toString(httpPost.getEntity()));
        String response = null;
        try {
            HttpResponse execute = client.execute(httpPost);
            HttpEntity entity = execute.getEntity();
            response = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }

        return response;
    }


    @SneakyThrows
    public static String requesGet(String url, Header ...header) {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeaders(header);
        String body = null;
        try {
            CloseableHttpResponse execute = client.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            body = EntityUtils.toString(entity, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }


        return unicodeToString(body);
    }


    public static String unicodeToString(String str) {
        log.info("请求结果：{}",str);

        if (str.isEmpty()){
            return "";
        }


        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch+"" );

        }

        return str;

    }
}
