package com.gongfuzhu.common.core.appium.android.tool;


public class AndroidUIAutomatorUtil {

    public static  String scrollaFindEelementId(String id){
        return String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"%s\"))",id);
    }


    public static String scrollaFindEelementText(String text){
        return String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"%s\"))",text);
    }


    public static String findEelementText(String text){
        return String.format("new UiSelector().text(\"%s\")",text);
    }

    public static String findEelemenTextContains(String text){
        return String.format("new UiSelector().textContains(\"%s\")",text);
    }


    public static String findEelementId(String id){
        return String.format("new UiSelector().resourceId(\"%s\")",id);
    }

}
