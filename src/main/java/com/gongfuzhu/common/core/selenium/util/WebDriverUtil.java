package com.gongfuzhu.common.core.selenium.util;

import com.gongfuzhu.common.core.tools.PictureTool;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;

public class WebDriverUtil {



    public static void screenshot(WebDriver driver,String text,String fileName){


        String path = ClassLoader.getSystemResource("").getPath();


        File file = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("文件名称:"+fileName);

        File file1 = new File(path + fileName);

        file.renameTo(file1);
        System.out.println(file1.getPath());

        PictureTool.addString(file1,text, Color.RED,new Font("宋体",Font.PLAIN,30));


    }
}
