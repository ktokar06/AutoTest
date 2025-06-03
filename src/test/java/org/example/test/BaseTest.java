package org.example.test;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static org.example.config.MyConfig.URL_PORTAL_DEMO;

/**
 * Базовый класс для всех тестов, содержащий общие настройки и методы.
 */
public class BaseTest {

    private WebDriver driver;

    /**
     * Метод, выполняемый перед каждым тестом.
     * Инициализирует FirefoxDriver, максимизирует окно браузера и устанавливает неявные ожидания.
     */
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL_PORTAL_DEMO);
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Делает скриншот, добавляет его в Allure-отчет и закрывает браузер.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                Allure.getLifecycle().addAttachment(
                        "Скриншот",
                        "image/png",
                        "png",
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
                );
            } catch (Exception e) {
                System.err.println("Не удалось сделать скриншот: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }

    /**
     * Получает текущий экземпляр WebDriver.
     *
     * @return Активный экземпляр WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }
}