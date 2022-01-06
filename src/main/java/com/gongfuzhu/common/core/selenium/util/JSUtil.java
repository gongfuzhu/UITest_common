package com.gongfuzhu.common.core.selenium.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSUtil {

    /**
     * 移动到最底部
     * @param driver
     */
    public static void moveToBottom(WebDriver driver){

        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");


    }


    /**
     * 滚动到顶部
     * @param driver
     */
    public static void moveToTope(WebDriver driver){


        ((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=0");


    }

    /**
     * 移动屏幕一般的距离
     * @param driver
     */
    public static void moveHalf(WebDriver driver){
        Dimension size = driver.manage().window().getSize();

        String format = String.format("window.scrollTo(0,%s)", size.getHeight() / 2);
        ((JavascriptExecutor)driver).executeScript(format);

    }
}
