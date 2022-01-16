package com.gongfuzhu.common.core.selenium;

import com.gongfuzhu.common.core.selenium.options.GeneralChromeOptions;
import com.gongfuzhu.common.core.selenium.options.arguments.ChromeArguments;
import com.gongfuzhu.common.core.selenium.util.WebDriverUtil;
import com.gongfuzhu.common.core.tools.JarTool;
import com.gongfuzhu.common.core.tools.PictureTool;
import com.gongfuzhu.common.core.tools.SystemTool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.File;


@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeleniumListener implements WebDriverEventListener {

    private int step = 1;

    private boolean screenshot = false;

    private long time = 1000;

    private String savePath;


    /**
     * 确认提示框之前
     *
     * @param driver
     */
    @Override
    public void beforeAlertAccept(WebDriver driver) {
        log.info("步骤{}：确认提示框-before",step);
        screenshot(driver, "确认提示框-before");
    }

    /**
     * 确认提示框之后
     *
     * @param driver
     */
    @Override
    public void afterAlertAccept(WebDriver driver) {
        log.info("步骤{}：确认提示框-after",step);
        waitTime();
        screenshot(driver, "确认提示框-after");
    }

    /**
     * 取消提示框之后
     *
     * @param driver
     */
    @Override
    public void afterAlertDismiss(WebDriver driver) {
        log.info("步骤{}：取消提示框-after",step);
        screenshot(driver, "取消提示框-after");
    }

    /**
     * 取消提示框之前
     *
     * @param driver
     */
    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        log.info("步骤{}：取消提示框-before",step);
        waitTime();
        screenshot(driver, "取消提示框-before");
    }

    /**
     * 打开url之前
     *
     * @param url
     * @param driver
     */
    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("步骤{}：打开url-before:{}",step, url);
        screenshot(driver, "打开url-before");
    }

    /**
     * 打开url之后
     *
     * @param url
     * @param driver
     */
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        log.info("步骤{}：打开url-after:{}",step, url);
        waitTime();
        screenshot(driver, "打开url-after");
    }

    /**
     * 后退之前
     *
     * @param driver
     */
    @Override
    public void beforeNavigateBack(WebDriver driver) {
        log.info("步骤{}：后退-before",step);
        screenshot(driver, "后退-before");
    }

    /**
     * 后退之后
     *
     * @param driver
     */
    @Override
    public void afterNavigateBack(WebDriver driver) {
        log.info("步骤{}：后退-after",step);
        screenshot(driver, "后退-after");

    }

    /**
     * 前进之前
     *
     * @param driver
     */
    @Override
    public void beforeNavigateForward(WebDriver driver) {

        log.info("步骤{}：前进-before",step);
        screenshot(driver, "前进-before");
    }

    /**
     * 前进之后
     *
     * @param driver
     */
    @Override
    public void afterNavigateForward(WebDriver driver) {

        log.info("步骤{}：前进-after",step);
        waitTime();
        screenshot(driver, "前进-after");
    }

    /**
     * 刷新之前
     *
     * @param driver
     */
    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

        log.info("步骤{}：刷新-before",step);
        screenshot(driver, "刷新-before");
    }

    /**
     * 刷新之后
     *
     * @param driver
     */
    @Override
    public void afterNavigateRefresh(WebDriver driver) {

        log.info("步骤{}：刷新-after",step);
        waitTime();
        screenshot(driver, "刷新-after");
    }

    /**
     * 查找元素之前
     *
     * @param by
     * @param element
     * @param driver
     */
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

        log.info("步骤{}：通过{}查找元素-before",step,by);
        screenshot(driver, "查找元素-before");
    }

    /**
     * 查找元素之后
     *
     * @param by
     * @param element
     * @param driver
     */
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        log.info("步骤{}：通过{}查找元素{}-after",step,by.toString(),element.getText());
        waitTime();
        screenshot(driver, "查找元素-after");
    }

    /**
     * 点击之前
     *
     * @param element
     * @param driver
     */
    @SneakyThrows
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {


        log.info("步骤{}：点击元素{}-before",step,element.getText());
        screenshot(driver, "点击-before");

    }

    /**
     * 点击之后
     *
     * @param element
     * @param driver
     */
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        log.info("步骤{}：点击元素{}-after",step,element.getText());
        screenshot(driver, "点击-after");

    }

    /**
     * 输入值之前
     *
     * @param element
     * @param driver
     * @param keysToSend
     */
    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log.info("步骤{}：在元素{}中输入{}-before",step,element.getText(),keysToSend);
        waitTime();
        screenshot(driver, "输入值-before");

    }

    /**
     * 输入值之后
     *
     * @param element
     * @param driver
     * @param keysToSend
     */
    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log.info("步骤{}：在元素{}中输入{}-after",step,element.getText(),keysToSend);
        waitTime();
        screenshot(driver, "输入值-after");
    }

    /**
     * 执行脚本之前
     *
     * @param script
     * @param driver
     */
    @Override
    public void beforeScript(String script, WebDriver driver) {
        log.info("步骤{}：执行脚本{}-before",step,script);
        screenshot(driver, "执行脚本-before");

    }

    /**
     * 执行脚本之后
     *
     * @param script
     * @param driver
     */
    @Override
    public void afterScript(String script, WebDriver driver) {
        log.info("步骤{}：执行脚本{}-after",step,script);
        waitTime();
        screenshot(driver, "执行脚本-after");

    }

    /**
     * 切换窗口之前
     *
     * @param windowName
     * @param driver
     */
    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        log.info("步骤{}：切换到窗口{}-before",step,windowName);
        screenshot(driver, "切换窗口-before");

    }

    /**
     * 切换窗口之后
     *
     * @param windowName
     * @param driver
     */
    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        log.info("步骤{}：切换到窗口{}-after",step,windowName);
        waitTime();
        screenshot(driver, "切换窗口-after");

    }

    /**
     * 出现异常的时候
     *
     * @param throwable
     * @param driver
     */
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        screenshot=true;
        log.info("步骤{}：出现异常{}",step,throwable.getMessage());
        screenshot(driver, "出现异常");

    }

    /**
     * 截图之前
     *
     * @param target
     * @param <X>
     */
    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        log.info("步骤{}：执行截图-before",step);

    }

    /**
     * 截图之后
     *
     * @param target
     * @param screenshot
     * @param <X>
     */
    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        log.info("步骤{}：执行截图-after",step);

    }

    /**
     * 获取文本之前
     *
     * @param element
     * @param driver
     */
    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        log.info("步骤{}：获取元素本文{}-before",step);
        screenshot(driver, "获取文本-before");

    }

    /**
     * 获取文本之后
     *
     * @param element
     * @param driver
     * @param text
     */
    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        log.info("步骤{}：获取元素本文{}-after",step);
        waitTime();
        screenshot(driver, "获取文本-after");

    }


    @SneakyThrows
    private void waitTime() {
        Thread.sleep(time);


    }


    private void screenshot(WebDriver driver, String text) {
        log.info("截图方法被执行：{}",screenshot);

        if (!screenshot) {
            step++;
            return;
        }


        String no = "No:";
        no += step;

        String fileName = step + "_" + text + ".png";


        String content = no + " " + text;


//        File file3 = new File(this.getClass().getResource("/").getPath());

//        log.info("文件路径：",file3.getPath());

//        String jarPath = SystemTool.getJarPath();
//        String jarPath= JarTool.getJarDir();

        WebDriverUtil.screenshot(driver, content, fileName,savePath);
        step++;



    }









}
