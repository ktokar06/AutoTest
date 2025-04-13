package org.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Утилитарный класс для ожидания различных состояний элементов на веб-странице.
 */
public class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    /**
     * Ожидает появления элемента на странице.
     *
     * @param driver Веб-драйвер, используемый для управления браузером.
     * @param locator Локатор элемента, который необходимо ожидать.
     * @return {@code true}, если элемент появился, иначе {@code false}.
     */
    public static boolean waitForElementPresence(WebDriver driver, By locator) {
        return waitForElementPresence(driver, locator, DEFAULT_TIMEOUT);
    }

    public static boolean waitForElementPresence(WebDriver driver, By locator, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}