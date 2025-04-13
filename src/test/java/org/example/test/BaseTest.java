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
 * Обеспечивает инициализацию WebDriver и управление жизненным циклом браузера.
 */
public class BaseTest {

    /**
     * Экземпляр WebDriver, используемый в тестах.
     */
    private WebDriver driver;

    /**
     * Метод, выполняемый перед каждым тестом.
     * Инициализирует ChromeDriver, максимизирует окно браузера и устанавливает неявные ожидания.
     */
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver(); // если нужен Google замените FirefoxDriver() -> ChromeDriver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL_PORTAL_DEMO);
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Делает скриншот, закрывает браузер и освобождает ресурсы WebDriver.
     */
    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                Allure.getLifecycle().addAttachment(
                        "Скриншот",
                        "image/png",
                        "png",
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
                );
            }
        } catch (Exception e) {
            System.err.println("Не удалось сделать скриншот: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    /**
     * Получает текущий экземпляр WebDriver.
     * @return Активный экземпляр WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }
}