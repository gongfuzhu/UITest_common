package com.gongfuzhu.common.core.selenium.mode;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskMode {

    private WebDriver webDriver;

    private WebDriverManager webDriverManager;
}
