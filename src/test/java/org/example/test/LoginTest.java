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
    public void testSuccessfulStandardLogin() {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage
                .standardLogin(VALID_USERNAME, VALID_PASSWORD)
                .gitLogin(VALID_USERNAME_GIT, VALID_PASSWORD_GIT);

        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Проверка, что стандартный вход выполнен успешно");
    }
}