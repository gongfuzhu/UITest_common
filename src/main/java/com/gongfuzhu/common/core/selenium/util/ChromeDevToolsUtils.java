package com.gongfuzhu.common.core.selenium.util;

import com.google.common.net.MediaType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v95.emulation.Emulation;
import org.openqa.selenium.devtools.v95.log.Log;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v95.network.model.ResponseReceived;
import org.openqa.selenium.devtools.v96.performance.Performance;
import org.openqa.selenium.devtools.v96.performance.model.Metric;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.openqa.selenium.remote.http.Contents.utf8String;

@Log4j2
public class ChromeDevToolsUtils {

    /**
     * 网络监听
     * @param driver
     */

    public void captureRequestSelenium(ChromeDriver driver) {

        Object browserVersion = driver.getCapabilities().getCapability("browserVersion");

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),
                requestWillBeSent ->

                        request(requestWillBeSent)
        );
        devTools.addListener(Network.responseReceived(), responseReceived ->

                response(responseReceived)


        );


    }

    /**
     * 监听console
     */
    public  void consoleLinsen(ChromeDriver driver){

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(),
                logEntry -> {
                    log.info("text：{}",logEntry.getText());
                    log.info("log:{}"+logEntry.getText());
                    log.info("level:{}"+logEntry.getLevel());
                });
    }

    /**
     * 获取性能指标
     * @param driver
     * @return
     */

    public List<Metric> performanceMetricsExample(ChromeDriver driver) {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        java.util.List<Metric> metricList = devTools.send(Performance.getMetrics());


//        for(Metric m : metricList) {
//            System.out.println(m.getName() + " = " + m.getValue());
//        }

        return metricList;
    }


    public void phone(ChromeDriver driver){

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
// iPhone 11 Pro dimensions
        devTools.send(Emulation.setDeviceMetricsOverride(375,
                812,
                50,
                true,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

    }

    public static void networ(ChromeDriver driver){
        NetworkInterceptor interceptor = new NetworkInterceptor(
                driver,
                Route.matching(req -> req.getUri().equals(""))
                        .to(() -> req ->  new HttpResponse()
                                .setStatus(200)
                                .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
                                .setContent(utf8String("Creamy, delicious cheese!"))));

        driver.get("https://example-sausages-site.com");

        String source = driver.getPageSource();

//        assertThat(source).contains("delicious cheese!");
    }

    public static ArrayList<JavascriptException> jsExceptionsExample(ChromeDriver driver) {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        ArrayList<JavascriptException> jsExceptionsList = new ArrayList<>();
        Consumer<JavascriptException> addEntry = jsExceptionsList::add;
        devTools.getDomains().events().addJavascriptExceptionListener(addEntry);

//        driver.get("<your site url>");
//
//        WebElement link2click = driver.findElement(By.linkText("<your link text>"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
//                link2click, "onclick", "throw new Error('Hello, world!')");
//        link2click.click();
//
//        for (JavascriptException jsException : jsExceptionsList) {
//            System.out.println("JS exception message: " + jsException.getMessage());
//            System.out.println("JS exception system information: " + jsException.getSystemInformation());
//            jsException.printStackTrace();
//        }

        return jsExceptionsList;
    }




    private void request(RequestWillBeSent requestWillBeSent) {

        log.info("请求id：{}", requestWillBeSent.getRequestId());
        log.info("请求url：{}",requestWillBeSent.getRequest().getUrl());
        log.info("请求头：{}", requestWillBeSent.getRequest().getHeaders().toJson());
        log.info("请求内容：{}", requestWillBeSent.getRequest().getPostData().toString());


    }

    private void response(ResponseReceived responseReceived) {

        log.info("响应id：{}", responseReceived.getRequestId());
        log.info("响应url：{}", responseReceived.getResponse().getUrl());
        log.info("响应内容：{}", responseReceived.getResponse());
        log.info("响应内容：{}", responseReceived.getResponse());
        log.info("响应内容：{}", responseReceived.getResponse());
        log.info("响应内容：{}", responseReceived.getResponse());
        log.info("响应内容：{}", responseReceived.getResponse());


    }
}
