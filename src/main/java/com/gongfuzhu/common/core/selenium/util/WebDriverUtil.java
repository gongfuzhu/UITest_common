package com.gongfuzhu.common.core.selenium.util;

import com.gongfuzhu.common.core.tools.PictureTool;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.awt.*;
import java.io.File;

@Log4j2
public class WebDriverUtil {



    public static void screenshot(WebDriver driver,String text,String fileName,String savePath){




        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);

        File file = eventFiringWebDriver.getScreenshotAs(OutputType.FILE);

        log.info("存储路径：{}",savePath);
        File toFile = new File(savePath + File.separator+fileName);

        log.info("文件存储路径：{}",toFile.getPath());

        file.renameTo(toFile);

        PictureTool.addString(toFile,text, Color.RED,new Font("宋体",Font.PLAIN,30));


    }
}
