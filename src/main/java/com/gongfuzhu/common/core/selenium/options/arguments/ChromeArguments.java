package com.gongfuzhu.common.core.selenium.options.arguments;

import lombok.Data;

import java.io.File;

@Data
public class ChromeArguments {


    public static String headless = "--headless";
    public static String userDataDir = String.format("--user-data-dir=%s", System.getenv("LOCALAPPDATA") + File.separator + "Google\\Chrome\\User Data");
    public static String disableInfobars = "disable-infobars";
    // 关闭自动化特征（可绕过网站机器人检测）
    public static String automationControlled = "--disable-blink-features=AutomationControlled";
    public static String disableBlinkFeatures= "--disable-blink-features";

    public static String iPhoneX ="--user-agent=iphone X";

    //打开开发者工具F12
    public static String devtools="--auto-open-devtools-for-tabs";






}
