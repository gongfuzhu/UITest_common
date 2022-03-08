package com.gongfuzhu.common.core.selenium.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

public class MouseUtil {


    private WebDriver webDriver;
    private TouchActions actions;


    public MouseUtil(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new TouchActions(webDriver);
    }



    /**
     * 鼠标移动到元素上悬停
     * @param webElement
     */
    public void moveToEle(WebElement webElement){
         actions.moveToElement(webElement).perform();

    }

    /**
     * 当前位置点击左键
     */
    public void lefClick(){
        actions.click();

    }

    /**
     * 当前位置右键
     */
    public void rightClick(){
        actions.contextClick();
    }

    /**
     * 当前位置双击
     */
    public void doubleClick(){

        actions.doubleClick();
    }

    /**
     * 将A元素拖拽到元素B上
     * @param source
     * @param target
     */
    public void dragAction(WebElement source, WebElement target){
        actions.dragAndDrop(source,target);
    }

    /**
     * 控制滚动条向下拉到底
     * @param driver 浏览器驱动
     */
    public static void toBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //向下拉到底//
        js.executeScript("document.documentElement.scrollTop=10000");
    }

    /**
     * 控制滚动条向上拉到顶
     *
     */
    public  void toTop() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //向上拉到顶
        js.executeScript("document.documentElement.scrollTop=0");
    }

    /**
     * 控制滚动条向下拉到底
     */
    public  void scrolltoBottom() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //向下拉到底
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    /**
     * 控制滚动条向上拉到顶
     */
    public  void scrolltoTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //向上拉到顶
        js.executeScript("window.scrollTo(0,1)");
    }

    /**
     * 控制滚动条拉到中间
     */
    public  void verticaltoMiddle() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //上下拉到中间
        js.executeScript("window.scrollBy(0, 0-document.body.scrollHeight *1/2)");
    }

    /**
     * 控制水平滚动条左右拉到中间
     */
    public  void horizontaltoMiddle() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //左右拉到中间
        js.executeScript("window.scrollBy(0, 0-document.body.scrollWidht *1/2)");
    }

    /**
     * 控制滚动条拉到元素可见
     * @param element 页面元素定位
     */
    public  void scrolltoPresence( WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //移动到元素element对象的“顶端”与当前窗口的“顶部”对齐
        //js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        //移动到元素element对象的“底端”与当前窗口的“底部”对齐
        //js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    /**
     * 使用JavaScript的ScrollTo函数和document.body.scrollHeight参数
     * 将页面滚动到最下方
     */
    public  void scrollingToBottomofPage(){
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用JavaScript的ScrollTo函数，使用0和800的横纵坐标参数
     * 将页面的滚动条纵向下滑800个像素
     */
    public  void scrollingByCoordinateofPage(){

        ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,200)");
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
