package org.example.test;

import io.qameta.allure.*;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.WidgetPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.config.MyConfig.*;

@Epic("Управление виджетами")
@Feature("Создание виджетов")
public class WidgetCreationTest extends BaseTest {

    @Test(description = "Проверка создания виджета 'Процент прохождения тестов'")
    @Description("Позитивный тест создания виджета типа 'Passing Rate Per Launch' с валидными параметрами")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Создание стандартного виджета")
    public void testCreatePassingRateWidget() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .standardLogin(VALID_USERNAME, VALID_PASSWORD)
                .gitLogin(VALID_USERNAME_GIT, VALID_PASSWORD_GIT);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage
                .clickDashboardButton()
                .createNewDashboard("My New Dashboard", "Test Description");

        WidgetPage widgetPage = new WidgetPage(getDriver());
        widgetPage
                .clickFiltersButton()
                .clickDashboardButton()
                .clickDashboardName()
                .clickAddNewWidget()
                .selectPassingRateWidget()
                .clickNextButton()
                .enterLaunchName("Task Progress Widget")
                .clickNextButton()
                .enterDescription("Отслеживание прогресса задач")
                .clickAddButton();

        Assert.assertTrue(
                widgetPage.isWidgetDisplayed(),
                "Виджет не отображается на странице"
        );
    }

    @Test(description = "Попытка создания виджета без обязательных параметров")
    @Description("Негативный тест: проверка создания виджета без указания имени запуска")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Негативные сценарии создания виджета")
    public void testCreateWidgetWithoutRequiredFields() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .standardLogin(VALID_USERNAME, VALID_PASSWORD)
                .gitLogin(VALID_USERNAME_GIT, VALID_PASSWORD_GIT);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage
                .clickDashboardButton()
                .createNewDashboard("Negative Test Dashboard", "For widget creation negative tests");

        WidgetPage widgetPage = new WidgetPage(getDriver());
        widgetPage
                .clickFiltersButton()
                .clickDashboardButton()
                .clickDashboardName()
                .clickAddNewWidget()
                .selectPassingRateWidget()
                .clickNextButton()
                .clickNextButton();

        Assert.assertFalse(
                widgetPage.isWidgetDisplayed(),
                "Виджет не должен отображаться при незаполненных обязательных полях"
        );
    }
}