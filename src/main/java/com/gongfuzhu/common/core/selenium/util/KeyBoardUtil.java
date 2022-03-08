package com.gongfuzhu.common.core.selenium.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

// TODO: 2022/3/8 修改为selenium的方式 
public class KeyBoardUtil {

    /**
     * Tab键封装
     */
    public static void pressTabKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //调用keypress方法来实现按下Tab键
        assert robot != null;
        robot.keyPress(KeyEvent.VK_TAB);
        //调用keyrelease方法来实现释放Tab键
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    /**
     * Enter键封装
     */
    public static void pressEnterKey(WebDriver webDriver) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //调用keypress方法来实现按下Enter键
        assert robot != null;
        robot.keyPress(KeyEvent.VK_ENTER);
        //调用keyrelease方法来实现释放Enter键
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * 将指定字符串设为剪切板内容，执行黏贴操作
     * 将页面焦点切换到输入框后，调用此函数，将指定字符串黏贴到输入框
     */
    public static void setAndctrlVClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * 键盘向下键封装
     */
    public static void pressDownKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
    }
}