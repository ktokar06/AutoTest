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
 * Предоставляет функционал для взаимодействия с элементами страницы входа
 * и выполнения операций авторизации различными способами.
 */
public class LoginPage extends HomePage {

    // Элементы страницы виджетов
    @FindBy(name = "login")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "button.bigButton__big-button--BmG4Q")
    private WebElement standardLoginButton;

    @FindBy(css = "input.js-sign-in-button[value='Sign in']")
    private WebElement gitLoginButton;

    @FindBy(css = ".allLatestDropdown__value--QwA8E.allLatestDropdown__active--qisno")
    protected WebElement allLaunchesDropdown;

    /**
     * Конструктор класса LoginPage.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Вводит указанное имя пользователя в соответствующее поле ввода.
     * Перед вводом очищает поле от предыдущих значений.
     *
     * @param username имя пользователя для авторизации
     * @return текущий экземпляр LoginPage для поддержки fluent-интерфейса
     */
    @Step("Ввод имени пользователя: {username}")
    public LoginPage enterUsername(String username) {
        WaitUtils.waitForElementPresence(driver, By.name("login"));
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    /**
     * Вводит указанный пароль в соответствующее поле ввода.
     * Перед вводом очищает поле от предыдущих значений.
     *
     * @param password пароль для авторизации
     * @return текущий экземпляр LoginPage для поддержки fluent-интерфейса
     */
    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        WaitUtils.waitForElementPresence(driver, By.name("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Нажимает кнопку стандартного входа в систему.
     * Ожидает, пока кнопка станет кликабельной перед выполнением действия.
     *
     * @return текущий экземпляр LoginPage для поддержки fluent-интерфейса
     */
    @Step("Нажатие кнопки 'Войти' (стандартный вход)")
    public LoginPage clickStandardLogin() {
        WaitUtils.waitForElementPresence(driver, By.cssSelector("button.bigButton__big-button--BmG4Q"));
        wait.until(ExpectedConditions.elementToBeClickable(standardLoginButton)).click();
        return this;
    }

    /**
     * Нажимает кнопку входа через Git.
     * Ожидает, пока кнопка станет кликабельной перед выполнением действия.
     *
     * @return текущий экземпляр LoginPage для поддержки fluent-интерфейса
     */
    @Step("Нажатие кнопки 'Sign in' (Git вход)")
    public LoginPage clickGitLogin() {
        WaitUtils.waitForElementPresence(driver, By.cssSelector("input.js-sign-in-button[value='Sign in']"));
        wait.until(ExpectedConditions.elementToBeClickable(gitLoginButton)).click();
        return this;
    }

    /**
     * Выполняет полный процесс стандартной авторизации в системе.
     * Последовательно вводит имя пользователя и пароль, затем подтверждает вход.
     *
     * @param username имя пользователя для авторизации
     * @param password пароль пользователя
     * @return текущий экземпляр LoginPage для поддержки fluent-интерфейса
     */
    @Step("Стандартная авторизация пользователя {username}")
    public LoginPage standardLogin(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickStandardLogin();
    }

    /**
     * Выполняет полный процесс авторизации через Git.
     * Последовательно вводит имя пользователя и пароль, затем подтверждает вход.
     *
     * @param username имя пользователя для авторизации через Git
     * @param password пароль пользователя для авторизации через Git
     * @return текущий экземпляр LoginPage для поддержки fluent-интерфейса
     */
    @Step("Авторизация через Git пользователя {username}")
    public LoginPage gitLogin(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickGitLogin();
    }

    /**
     * Проверяет успешность выполнения авторизации.
     * Определяет по наличию элемента, отображаемого после успешного входа.
     *
     * @return true если авторизация прошла успешно, false в случае ошибки
     */
    @Step("Проверка успешности авторизации")
    public boolean isLoginSuccessful() {
        return WaitUtils.waitForElementPresence(driver,
                By.cssSelector(".allLatestDropdown__value--QwA8E.allLatestDropdown__active--qisno"));
    }
}