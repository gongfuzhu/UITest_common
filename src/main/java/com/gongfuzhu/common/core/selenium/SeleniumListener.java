package com.gongfuzhu.common.core.selenium;

import com.gongfuzhu.common.core.selenium.options.GeneralChromeOptions;
import com.gongfuzhu.common.core.selenium.options.arguments.ChromeArguments;
import com.gongfuzhu.common.core.selenium.util.WebDriverUtil;
import com.gongfuzhu.common.core.tools.PictureTool;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class SeleniumListener implements WebDriverEventListener {

    private  int step=1;

    private boolean screenshot=false;

    private long time = 1000;

    /**
     * 确认提示框之前
     * @param driver
     */
    @Override
    public void beforeAlertAccept(WebDriver driver) {
        waitTime();

    }

    /**
     * 确认提示框之后
     * @param driver
     */
    @Override
    public void afterAlertAccept(WebDriver driver) {
        waitTime();
    }

    /**
     * 取消提示框之前
     * @param driver
     */
    @Override
    public void afterAlertDismiss(WebDriver driver) {
        waitTime();
    }

    /**
     * 取消提示框之后
     * @param driver
     */
    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        waitTime();
    }

    /**
     * 打开url之前
     * @param url
     * @param driver
     */
    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        waitTime();
        log.info("即将跳转到:{}",url);

    }

    /**
     * 打开url之后
     * @param url
     * @param driver
     */
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        waitTime();
        System.out.println("已经跳转到:"+url);

    }

    /**
     * 后退之前
     * @param driver
     */
    @Override
    public void beforeNavigateBack(WebDriver driver) {
        waitTime();

    }

    /**
     * 后退之后
     * @param driver
     */
    @Override
    public void afterNavigateBack(WebDriver driver) {
        waitTime();

    }

    /**
     * 前进之前
     * @param driver
     */
    @Override
    public void beforeNavigateForward(WebDriver driver) {

        waitTime();
    }

    /**
     * 前进之后
     * @param driver
     */
    @Override
    public void afterNavigateForward(WebDriver driver) {

        waitTime();
    }

    /**
     * 刷新之前
     * @param driver
     */
    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

        waitTime();
    }

    /**
     * 刷新之后
     * @param driver
     */
    @Override
    public void afterNavigateRefresh(WebDriver driver) {

        waitTime();
    }

    /**
     * 查找元素之前
     * @param by
     * @param element
     * @param driver
     */
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        waitTime();
    }

    /**
     * 查找元素之后
     * @param by
     * @param element
     * @param driver
     */
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

        waitTime();
    }

    /**
     * 点击之前
     * @param element
     * @param driver
     */
    @SneakyThrows
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        waitTime();

        log.info("点击元素：{}",element.getText());

    }

    /**
     * 点击之后
     * @param element
     * @param driver
     */
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        waitTime();

    }

    /**
     * 输入值之前
     * @param element
     * @param driver
     * @param keysToSend
     */
    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        waitTime();
        log.info("输入内容：{}",keysToSend);

    }

    /**
     * 输入值之后
     * @param element
     * @param driver
     * @param keysToSend
     */
    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

        waitTime();
    }

    /**
     * 执行脚本之前
     * @param script
     * @param driver
     */
    @Override
    public void beforeScript(String script, WebDriver driver) {
        waitTime();

    }

    /**
     * 执行脚本之后
     * @param script
     * @param driver
     */
    @Override
    public void afterScript(String script, WebDriver driver) {
        waitTime();

    }

    /**
     * 切换窗口之前
     * @param windowName
     * @param driver
     */
    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        waitTime();

    }

    /**
     * 切换窗口之后
     * @param windowName
     * @param driver
     */
    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        waitTime();

    }

    /**
     * 出现异常的时候
     * @param throwable
     * @param driver
     */
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        waitTime();

    }

    /**
     * 截图之前
     * @param target
     * @param <X>
     */
    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        waitTime();

    }

    /**
     * 截图之后
     * @param target
     * @param screenshot
     * @param <X>
     */
    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        waitTime();

    }

    /**
     * 获取文本之前
     * @param element
     * @param driver
     */
    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        waitTime();

    }

    /**
     * 获取文本之后
     * @param element
     * @param driver
     * @param text
     */
    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        waitTime();

    }


    @SneakyThrows
    private void waitTime(){
        Thread.sleep(time);


    }



    private void screenshot(WebDriver driver,String text){

//        if (!screenshot){
//
//            return;
//        }

        String path = ClassLoader.getSystemResource("").getPath();

        String no = "No:";
        no+=step;

        String fileName= step+"_"+text+".png";


        String content =no+" "+text;

        WebDriverUtil.screenshot(driver,content,fileName);

        step++;


    }


    public static void main(String[] args) {
        InitiWebDriver initiWebDriver = new InitiWebDriver();
        DesiredCapabilities h5Capabilities = GeneralChromeOptions.getH5Capabilities();
        RemoteWebDriver webDriver = (RemoteWebDriver)initiWebDriver.localDriver(DriverManagerType.CHROME,h5Capabilities);

        webDriver.get("https://www.baidu.com");



        SeleniumListener seleniumListener = new SeleniumListener();
        seleniumListener.screenshot(webDriver,"打开百度");
        initiWebDriver.closeDriver();

    }




}
