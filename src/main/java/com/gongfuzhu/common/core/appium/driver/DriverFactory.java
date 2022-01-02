package com.gongfuzhu.common.core.appium.driver;

import com.gongfuzhu.common.core.appium.AppiumEvenLisen;
import com.gongfuzhu.common.core.appium.driver.manager.AndroidDriverManager;
import com.gongfuzhu.common.core.appium.driver.manager.IOSDriverManager;
import com.gongfuzhu.common.core.exception.PlatformNotSupportedException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
public class DriverFactory {

    public AppiumDriver createInstance(String platform, DesiredCapabilities capabilities, URL url) {


        AppiumDriver<WebElement> driver;
        Platform mobilePlatform = Platform.valueOf(platform.toUpperCase());

        switch (mobilePlatform) {
            case IOS:
                driver = new IOSDriverManager().createInstance(capabilities,url);
                break;

            case ANDROID:
                driver = new AndroidDriverManager().createInstance(capabilities,url);
                break;

            default:
                throw new PlatformNotSupportedException(
                    "Platform not supported! Check if you set ios or android on the parameter.");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        return EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new AppiumEvenLisen());
    }
}
