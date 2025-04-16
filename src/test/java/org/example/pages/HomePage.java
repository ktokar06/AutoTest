package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Базовый класс для домашней страницы, содержащий общие элементы интерфейса.
 * Предоставляет основные методы навигации и проверки состояния элементов.
 */
public class HomePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    /**
     * Конструктор инициализирует домашнюю страницу.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


}