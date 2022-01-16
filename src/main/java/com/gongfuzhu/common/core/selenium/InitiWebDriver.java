package com.gongfuzhu.common.core.selenium;

import com.gongfuzhu.common.core.selenium.mode.TaskMode;
import com.gongfuzhu.common.core.selenium.util.ChromeDevToolsUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v96.performance.model.Metric;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class InitiWebDriver {



    private final String recordingPath = "D:\\application\\";
    private String videoName;
    private ThreadLocal<TaskMode> taskMode = ThreadLocal.withInitial(() -> null);


    /**
     * 本地浏览器
     * @param type
     * @return
     */

    public WebDriver localDriver(DriverManagerType type, ChromeOptions capabilities) {
        WebDriver webDriver;
        WebDriverManager wd = WebDriverManager.getInstance(type).capabilities(capabilities);
        webDriver = wd.create();

        generalSettings(webDriver);

        this.taskMode.set(new TaskMode(webDriver, wd));


        return webDriver;

    }

    /**
     * 本地docker
     * @param type
     * @return
     */

    public WebDriver dockerDriver(DriverManagerType type) {


        WebDriver webDriver;
        WebDriverManager wd = WebDriverManager.getInstance(type).browserInDocker();


        wd.dockerScreenResolution("1920x1080x24").enableRecording().enableVnc();
        wd.recordingOutput(recordingPath + System.currentTimeMillis() + ".mp4");
        webDriver = wd.create();


        generalSettings(webDriver);
        this.taskMode.set(new TaskMode(webDriver, wd));

        return webDriver;

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
