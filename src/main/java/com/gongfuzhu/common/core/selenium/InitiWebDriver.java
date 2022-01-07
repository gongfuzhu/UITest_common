package com.gongfuzhu.common.core.selenium;

import com.gongfuzhu.common.core.selenium.mode.TaskMode;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class InitiWebDriver {



    private final String recordingPath = "D:\\application\\";
    private String videoName;
    private ThreadLocal<TaskMode> taskMode = ThreadLocal.withInitial(() -> null);


    /**
     * 本地浏览器
     * @param type
     * @return
     */

    public WebDriver localDriver(DriverManagerType type, ChromeOptions capabilities, boolean record) {
        WebDriver webDriver;
        WebDriverManager wd = WebDriverManager.getInstance(type).capabilities(capabilities);
        webDriver = wd.create();

        generalSettings(webDriver);

        this.taskMode.set(new TaskMode(webDriver, wd));


        return listener(webDriver,record);

    }

    /**
     * 本地docker
     * @param type
     * @return
     */

    public WebDriver dockerDriver(DriverManagerType type,boolean record) {


        WebDriver webDriver;
        WebDriverManager wd = WebDriverManager.getInstance(type).browserInDocker();


        wd.dockerScreenResolution("1920x1080x24").enableRecording().enableVnc();
        wd.recordingOutput(recordingPath + System.currentTimeMillis() + ".mp4");
        webDriver = wd.create();


        generalSettings(webDriver);
        this.taskMode.set(new TaskMode(webDriver, wd));

        return listener(webDriver,record);

    }

    private EventFiringWebDriver listener(WebDriver webDriver,boolean record) {
        SeleniumListener seleniumListener = new SeleniumListener(record);

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);

        return eventFiringWebDriver.register(seleniumListener);

    }

    @SneakyThrows
    public void closeDriver() {

        //等待录制功能结束
        Thread.sleep(5000);


        TaskMode taskMode = this.taskMode.get();

        taskMode.getWebDriver().quit();
        taskMode.getWebDriverManager().quit();

    }

    @SneakyThrows
    private void generalSettings(WebDriver driver) {
        //等待录制功能启动
        Thread.sleep(5000);



        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);

    }


}
