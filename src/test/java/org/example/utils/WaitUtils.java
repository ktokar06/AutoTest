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

    /**
     * Ожидает кликабельности элемента.
     *
     * @param driver Веб-драйвер
     * @param element Элемент для проверки
     * @return {@code true}, если элемент кликабелен, иначе {@code false}.
     */
    public static boolean waitForElementToBeClickable(WebDriver driver, WebElement element) {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT)
                    .until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ожидает исчезновения элемента со страницы.
     *
     * @param driver Веб-драйвер
     * @param locator Локатор элемента
     * @return {@code true}, если элемент исчез, иначе {@code false}.
     */
    public static boolean waitForElementToDisappear(WebDriver driver, By locator) {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT)
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}