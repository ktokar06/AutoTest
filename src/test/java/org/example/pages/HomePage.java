package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Базовый класс для всех страниц приложения.
 * Содержит общие элементы и функциональность для работы с WebDriver и ожиданиями.
 * Инициализирует элементы страницы с помощью PageFactory.
 */
public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Конструктор базовой страницы.
     *
     * @param driver WebDriver, используемый для взаимодействия с браузером
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Геттер для WebDriver, доступен для наследников.
     *
     * @return экземпляр WebDriver
     */
    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * Геттер для WebDriverWait, доступен для наследников.
     *
     * @return экземпляр WebDriverWait
     */
    protected WebDriverWait getWait() {
        return wait;
    }
}