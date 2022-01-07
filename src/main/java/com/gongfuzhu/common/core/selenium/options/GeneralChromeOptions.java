package com.gongfuzhu.common.core.selenium.options;

import com.gongfuzhu.common.core.selenium.options.arguments.ChromeArguments;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class GeneralChromeOptions {


    /**
     * 没有用户信息
     * @return
     */
    public static ChromeOptions getCapabilities(){

        ChromeOptions chromeOptions = new ChromeOptions();


        //加载用户信息
        chromeOptions.addArguments( ChromeArguments.disableBlinkFeatures, ChromeArguments.automationControlled);
        //禁止弹窗提示
        chromeOptions.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});

        return chromeOptions;

    }

    /**
     * 加载有用户信息的浏览器
     * @return
     */

    public static ChromeOptions getUserCapabilities(){

        ChromeOptions chromeOptions = new ChromeOptions();



        chromeOptions.addArguments(ChromeArguments.userDataDir, ChromeArguments.disableBlinkFeatures, ChromeArguments.automationControlled);
        //禁止弹窗提示
        chromeOptions.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});

        return chromeOptions;

    }


    /**
     * 加载H5
     */
    public static ChromeOptions getH5Capabilities(){
        HashMap<String, String> mobileEmulation  = new HashMap<>();
        String deviceName = "iPhone X";	//iPhone X/Galaxy S5
        mobileEmulation.put("deviceName",deviceName);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation",mobileEmulation);

        return chromeOptions;

    }



}
