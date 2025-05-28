package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Утилитарный класс для ожидания различных состояний элементов на веб-странице.
 */
public class WaitUtils {

    /**
     * Ожидает появления элемента на странице с использованием WebElement.
     *
     * @param driver Веб-драйвер, используемый для управления браузером.
     * @param element WebElement, который необходимо ожидать.
     * @return {@code true}, если элемент появился, иначе {@code false}.
     */
    public static boolean waitForElementPresence(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}