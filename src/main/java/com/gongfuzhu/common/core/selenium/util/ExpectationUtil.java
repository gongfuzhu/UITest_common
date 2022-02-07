package com.gongfuzhu.common.core.selenium.util;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * WebElement为po模式下的元素，@FindBy(xx) 否则达不到期望效果
 */
@Log4j2
public class ExpectationUtil {

    /**
     * 提示语消失并打印提示语
     * @param webElement
     * @return
     */
    public static ExpectedCondition<Boolean> invisibilityOfElementWithReminder(WebElement webElement) {

        return driver -> {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            try {
                log.info("提示：{}", webElement.getText());
                return false;
            } catch (NoSuchElementException e) {
                return true;
            } catch (StaleElementReferenceException e) {
                return true;
            } finally {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }

        };
    }

    /**
     * 检查元素文本内容
     * @param wait
     * @param element
     */
    public static String logElmentText(WebDriverWait wait,WebElement element){

        WebElement webElement = wait.until(ExpectedConditions.visibilityOf(element));
        log.info("检查元素：{}",webElement.getText());


        return webElement.getText();


    }
}
