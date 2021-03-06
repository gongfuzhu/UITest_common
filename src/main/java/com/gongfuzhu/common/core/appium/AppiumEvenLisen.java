package com.gongfuzhu.common.core.appium;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class AppiumEvenLisen implements AppiumWebDriverEventListener {


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

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {

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

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {

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
}
