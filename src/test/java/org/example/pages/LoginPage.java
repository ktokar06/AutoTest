package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница авторизации в Report Portal.
 * Предоставляет методы для ввода учетных данных, выполнения входа и проверки успешности авторизации.
 */
public class LoginPage extends HomePage {

    @FindBy(name = "login")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "form[class^='loginForm__login-form'] button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".allLatestDropdown__value--QwA8E.allLatestDropdown__active--qisno")
    private WebElement loginSuccessElement;

    /**
     * Конструктор страницы авторизации.
     *
     * @param driver WebDriver для взаимодействия с браузером
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Вводит имя пользователя в поле "Логин".
     *
     * @param username имя пользователя для авторизации
     * @return текущий экземпляр {@link LoginPage} для цепочки вызовов
     */
    @Step("Ввод имени пользователя: {username}")
    public LoginPage enterUsername(String username) {
        WaitUtils.waitForElementPresence(getDriver(), usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    /**
     * Вводит пароль в поле "Пароль".
     *
     * @param password пароль пользователя
     * @return текущий экземпляр {@link LoginPage} для цепочки вызовов
     */
    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        WaitUtils.waitForElementPresence(getDriver(), passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Нажимает кнопку "Login" для выполнения входа.
     *
     * @return текущий экземпляр {@link LoginPage} для цепочки вызовов
     */
    @Step("Нажатие кнопки 'Login'")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    /**
     * Выполняет полный процесс авторизации с заданными учетными данными.
     *
     * @param username имя пользователя
     * @param password пароль
     * @return текущий экземпляр {@link LoginPage} для цепочки вызовов
     */
    @Step("Выполнение авторизации пользователя {username}")
    public LoginPage performLogin(String username, String password) {
        enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        return this;
    }

    /**
     * Проверяет успешность авторизации, ожидая появления определенного элемента.
     *
     * @return {@code true}, если элемент, подтверждающий вход, присутствует; {@code false} иначе
     */
    @Step("Проверка успешности авторизации")
    public boolean isLoginSuccessful() {
        return WaitUtils.waitForElementPresence(getDriver(), loginSuccessElement);
    }
}