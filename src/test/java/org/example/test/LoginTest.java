package org.example.test;

import io.qameta.allure.*;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.config.MyConfig.*;


@Epic("Авторизация")
@Feature("Функционал входа в систему")
public class LoginTest extends BaseTest {

    @Test(description = "Успешный стандартный вход с валидными данными")
    @Description("Проверка стандартного входа в систему с корректными логином и паролем")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Позитивный сценарий стандартной авторизации")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.performLogin(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "После ввода валидных учетных данных должен быть выполнен успешный вход");

    }

    @Test(description = "Неудачная попытка входа с неверным паролем")
    @Description("Проверка обработки ошибки при вводе некорректного пароля")
    @Severity(SeverityLevel.NORMAL)
    @Story("Негативный сценарий авторизации")
    public void testFailedLogin() {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.performLogin(INVALID_USERNAME, INVALID_PASSWORD);

        Assert.assertFalse(loginPage.isLoginSuccessful(),
                "При вводе неверного пароля вход не должен быть выполнен");
    }
}