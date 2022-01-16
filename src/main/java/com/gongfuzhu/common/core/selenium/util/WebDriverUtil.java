package com.gongfuzhu.common.core.selenium.util;

import com.gongfuzhu.common.core.tools.PictureTool;
import com.google.common.net.MediaType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.devtools.v96.performance.Performance;
import org.openqa.selenium.devtools.v96.performance.model.Metric;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.openqa.selenium.remote.http.Contents.utf8String;

@Log4j2
public class WebDriverUtil {


    public static void screenshot(WebDriver driver, String text, String fileName, String savePath) {


        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);

        File file = eventFiringWebDriver.getScreenshotAs(OutputType.FILE);
        if (null !=savePath) {

            File file1 = new File(savePath);
            file1.mkdir();

            File toFile = new File(file1.getAbsoluteFile()+ File.separator + fileName);

            log.info("文件存储路径：{}", toFile.getAbsoluteFile());

            file.renameTo(toFile);

            PictureTool.addString(toFile, text, Color.RED, new Font("宋体", Font.PLAIN, 30));

        }else {

            log.info("文件存储路径：{}", file.getPath());
            PictureTool.addString(file, text, Color.RED, new Font("宋体", Font.PLAIN, 30));
        }



    }


}
