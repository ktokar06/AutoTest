package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Класс страницы авторизации в Report Portal.
 * Предоставляет методы для выполнения входа в систему и проверки успешности авторизации.
 */
public class LoginPage extends HomePage {

    @FindBy(css = "input[name='login']")
    private WebElement usernameField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    @FindBy(css = "form.loginForm__login-form--UYW8B button[type='submit']")
    private WebElement loginButton;

    /**
     * Конструктор класса WidgetPage
     * @param driver WebDriver экземпляр драйвера
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Вводит имя пользователя в поле ввода
     * @param username имя пользователя для авторизации
     * @return текущий экземпляр {@link LoginPage}
     */
    @Step("Ввод имени пользователя: {username}")
    public LoginPage enterUsername(String username) {
        WaitUtils.waitForElementPresence(driver, By.name("login"));
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    /**
     * Вводит пароль в поле ввода
     * @param password пароль для авторизации
     * @return текущий экземпляр {@link LoginPage}
     */
    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        WaitUtils.waitForElementPresence(driver, By.name("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Нажимает кнопку входа в систему
     * @return текущий экземпляр {@link LoginPage}
     */
    @Step("Нажатие кнопки 'Login'")
    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    /**
     * Выполняет полный процесс авторизации
     * @param username имя пользователя
     * @param password пароль пользователя
     * @return текущий экземпляр {@link LoginPage}
     */
    @Step("Выполнение авторизации пользователя {username}")
    public LoginPage performLogin(String username, String password) {
        enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        return this;
    }

    /**
     * Проверяет успешность авторизации
     * @return true если авторизация успешна, иначе false
     */
    @Step("Проверка успешности авторизации")
    public boolean isLoginSuccessful() {
        return WaitUtils.waitForElementPresence(driver,
                By.cssSelector(".allLatestDropdown__value--QwA8E.allLatestDropdown__active--qisno"));
    }
}