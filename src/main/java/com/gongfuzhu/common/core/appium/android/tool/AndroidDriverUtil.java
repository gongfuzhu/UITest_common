package com.gongfuzhu.common.core.appium.android.tool;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.html5.Location;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AndroidDriverUtil {
    private static String imagePath="test-output"+File.separator+"images";
    private static String videoPath="test-output"+File.separator+"video";



    /**
     * 解锁设备
     * @param driver
     * @param keys
     */
    public static void  unlockDevices(AndroidDriver driver, AndroidKey... keys){

        if (driver.isDeviceLocked()){
            driver.unlockDevice();
            new AndroidTouchUtil().swipeScreen(driver, AndroidTouchUtil.Direction.UP);
            for (AndroidKey androidKey:keys){

                driver.pressKey(new KeyEvent(androidKey));
            }
        }
    }



    /**
     * 检查页面是否包含某元素
     * @param driver
     * @param value
     * @return
     */

    public static boolean checkPage(AndroidDriver driver,String value){
        for (int i=0;i<10;i++){
            if (driver.getPageSource().contains(value)){
                return true;

            }

        }
        return false;
    }

    /**
     * 录制屏幕
     * @param driver
     */

    public static void startRecording(AndroidDriver driver){

        long apiLevel= (long) driver.getCapabilities().getCapability("deviceApiLevel");

        if (apiLevel<27){
            log.error("deviceApiLevel 小于27，不支持录屏");
            return;
        }

        log.info("开始录屏.........");
        AndroidStartScreenRecordingOptions androidStartScreenRecordingOptions =AndroidStartScreenRecordingOptions.startScreenRecordingOptions();
        androidStartScreenRecordingOptions.startScreenRecordingOptions().enableBugReport();
        androidStartScreenRecordingOptions.withTimeLimit(Duration.ofMinutes(30));
        driver.startRecordingScreen(androidStartScreenRecordingOptions);
    }

    /**
     * 停止录像
     * @param driver
     */

    public static void stopRecording(AndroidDriver driver,String videoName){
        long apiLevel= (long) driver.getCapabilities().getCapability("deviceApiLevel");

        if (apiLevel<27){
            log.info("deviceApiLevel 小于27，不支持录屏");
            return;
        }
        try {

            String base64String = driver.stopRecordingScreen();
            byte[] data = Base64.decodeBase64(base64String);

            File outPath = new File(videoPath);
            outPath.mkdirs();

            File mp4 = new File(outPath.getPath(),videoName+".mp4");

            Files.write(mp4.toPath(),data);
            log.info("录屏成功");
        } catch (Exception e) {
            log.error("录屏失败");
            e.printStackTrace();
        }
    }

    /**
     *截图
     * @param driver
     * @param imageName
     */
    public static void getScreenshot(AndroidDriver driver,String imageName) {
        try {
            String image = driver.getScreenshotAs(OutputType.BASE64);
            byte[] data = Base64.decodeBase64(image);
            File outPath = new File(imagePath);
            outPath.mkdirs();
            File pic = new File(outPath.getPath(),imageName+".jpg");
            Files.write(pic.toPath(),data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过id检查元素是否存在
     * @param driver
     * @param id
     * @return
     */
    public static boolean elementExistById(AndroidDriver driver,String id){

        List<Object> e=driver.findElementsByAndroidUIAutomator(AndroidUIAutomatorUtil.findEelementId(id));

        if (e.size()==0){
            return false;
        }else {
            return true;
        }

    }

    /**
     * 通过文本检查元素是否存在
     * @param driver
     * @param text
     * @return
     */
    public static boolean elementExistByText(AndroidDriver driver,String text){

        List<Object> e=driver.findElementsByAndroidUIAutomator(AndroidUIAutomatorUtil.findEelementText(text));


        if (e.size()==0){
            return false;
        }else {
            return true;
        }

    }






    /**
     * 获取短信
     *
     * @param driver
     */
    public static void getSMSd(AndroidDriver driver) {
        System.out.println(((JavascriptExecutor) driver).executeScript("mobile: listSms"));
    }


    /**
     * 获取权限列表
     * @param driver
     * @param appPackage
     * @param type
     */
    public ArrayList getPermissions(AndroidDriver driver, String appPackage, PermissionType type) {
        return  (ArrayList)((JavascriptExecutor) driver).executeScript("mobile:getPermissions", ImmutableMap.of(
                "appPackage", appPackage, "type", type.toString()));
    }

    /**
     * 更改权限
     * @param driver
     */
    public static void changePermissions(AndroidDriver driver, String appPackage, PrantPermission permission, String... authority) {
        ((JavascriptExecutor) driver).executeScript("mobile:changePermissions", ImmutableMap.of(
                "appPackage", appPackage, "action", permission.toString(), "permissions", authority));
    }


    /**
     * 确认警告
     * @param driver
     */
    public static void acceptAlert(AndroidDriver driver) {
        ((JavascriptExecutor) driver).executeScript("mobile:acceptAlert");
    }

    /**
     * 关闭警告
     * @param driver
     */
    public static void dismissAlert(AndroidDriver driver) {
        ((JavascriptExecutor) driver).executeScript("mobile:dismissAlert");
    }

    /**
     * 设置设备地理位置
     * @param driver
     * @param location
     */
    public static void setLocation(AndroidDriver driver, Location location){
        driver.setLocation(location);
    }




    public enum PermissionType {
        denied,
        granted,
        requested;
    }


    public enum PrantPermission {
        grant,
        revoke;
    }




}


