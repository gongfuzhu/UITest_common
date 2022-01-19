package com.gongfuzhu.common.core.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Clock;

public abstract class BasePage<T extends LoadableComponent<T>> extends SlowLoadableComponent<T> {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage(WebDriver driver) {
        super(Clock.systemDefaultZone(), 20);
        this.driver = driver;
        this.webDriverWait=new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }
}
