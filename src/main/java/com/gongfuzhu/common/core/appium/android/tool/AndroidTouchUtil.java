package com.gongfuzhu.common.core.appium.android.tool;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AndroidTouchUtil {

    /**
     * 滑动屏幕
     *
     * @param driver
     * @param dir
     */
    public void swipeScreen(AndroidDriver driver, Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void swipeScreen(AndroidDriver driver, Direction dir,int PRESS_TIME) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

//        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }


    /**
     * 向前滑动一个视图
     *
     * @param driver
     */
    public void scrollForward(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
        } catch (Exception e) {
            // ignore
        }

    }

    /**
     * 向前滚动
     *
     * @param driver
     */
    public void flingForward(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingForward()");
        } catch (Exception e) {
            // ignore
        }

    }

    /**
     * 向后滑动一个视图
     *
     * @param driver
     */
    public void scrollBackward(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()");
        } catch (Exception e) {
            // ignore
        }

    }

    /**
     * 向后滚动-滑动后放开
     *
     * @param driver
     */
    public void flingBackward(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingBackward()");
        } catch (Exception e) {
            // ignore
        }

    }

    /**
     * 滚动到一页（缓慢）
     *
     * @param driver
     */
    public void scrollToBeginning(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(10)");
        } catch (Exception e) {
            // ignore
        }

    }

    /**
     * 滚动到第一页（快速）
     *
     * @param driver
     */
    public void flingToBeginning(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToBeginning(10)");
        } catch (Exception e) {
            // ignore
        }

    }


    /**
     * 滚动到最后一页（缓慢）
     *
     * @param driver
     */
    public void scrollToEnd(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)");
        } catch (Exception e) {
            // ignore
        }

    }

    /**
     * 滚动到最后一页（快速）
     *
     * @param driver
     */
    public void flingToEnd(AndroidDriver driver) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(10)");
        } catch (Exception e) {
            // ignore
        }

    }

    /**
     * 放大手势
     *
     * @param driver
     */
    public void pinchOpenGesture(AndroidDriver driver) {

        AndroidElement element = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.FrameLayout\")");
        ((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "elementId", element.getId(),
                "percent", 0.75
        ));


    }

    /**
     * 缩小手势
     *
     * @param driver
     */
    public void pinchCloseGesture(AndroidDriver driver) {

        AndroidElement element = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.FrameLayout\")");
        ((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
                "elementId", element.getId(),
                "percent", 0.75
        ));
    }

    /**
     * 双击一个元素
     * @param driver
     * @param element
     */
    public void doubleTap(AndroidDriver driver, AndroidElement element) {
        TouchActions action = new TouchActions(driver);
        action.doubleTap(element);
        action.perform();

    }


    /**
     * 长按一个元素
     * @param driver
     * @param element
     */
    public void longpre(AndroidDriver driver, AndroidElement element) {

        TouchActions action = new TouchActions(driver);
        action.longPress(element);
        action.perform();

    }


    /**
     * 从一个元素滚动到另外一个元素
     * @param driver
     * @param element1
     * @param element2
     */
    public void scrollBackTo(AndroidDriver driver, AndroidElement element1,AndroidElement element2) {
        ((JavascriptExecutor) driver).executeScript("mobile:scrollBackTo", ImmutableMap.of(
                "elementId", element1.getId(),
                "elementId", element2.getId()));


    }

    /**
     * 长按一个元素
     * @param driver
     * @param element
     */
    public void longClickGesture(AndroidDriver driver, AndroidElement element) {

        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    /**
     * 双击坐标
     * @param driver
     * @param pointOption
     */
    public void doubleTap(AndroidDriver driver,PointOption pointOption){

        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withTapsCount(2).withPosition(pointOption)).perform();
    }


    /**
     * 单机坐标
     * @param driver
     * @param pointOption
     */
    public void tap(AndroidDriver driver,PointOption pointOption){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(new TapOptions().withPosition(pointOption));
        touchAction.perform();
    }




    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }


}


