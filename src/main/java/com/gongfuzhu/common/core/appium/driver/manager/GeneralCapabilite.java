package com.gongfuzhu.common.core.appium.driver.manager;

import com.gongfuzhu.common.core.tools.SystemTool;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.PLATFORM_NAME;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

@Component
public class GeneralCapabilite {

    public DesiredCapabilities capabilite(String platform, String udid) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        Platform mobilePlatform = Platform.valueOf(platform.toUpperCase());
        capabilities.setCapability(UDID, udid);
        capabilities.setCapability(DEVICE_NAME, udid);
//        capabilities.setCapability(PLATFORM_VERSION, platformVersion);
//        capabilities.setCapability(APP, appPath);

        capabilities.setCapability(NEW_COMMAND_TIMEOUT, 0);
        capabilities.setCapability(NO_RESET, false);

        switch (mobilePlatform) {
            case IOS:

                capabilities.setCapability(PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
                break;
            case ANDROID:
                capabilities.setCapability(PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setCapability(NATIVE_WEB_SCREENSHOT,true);
                capabilities.setCapability(ENSURE_WEBVIEWS_HAVE_PAGES,true);
                capabilities.setCapability(AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
                //解锁
                capabilities.setCapability(UNLOCK_TYPE,"pin");
                capabilities.setCapability(UNLOCK_KEY,"2580");
                capabilities.setCapability(SKIP_DEVICE_INITIALIZATION,true);
                capabilities.setCapability("skipServerInstallation",true);
                capabilities.setCapability(SYSTEM_PORT, SystemTool.getFreeProt());
//                capabilities.setCapability("connectHardwareKeyboard",true);
                break;
        }



        return capabilities;
    }
}
