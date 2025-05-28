package org.example.test;

import io.qameta.allure.*;
import org.example.pages.LoginPage;
import org.example.pages.WidgetPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.config.MyConfig.*;


@Epic("Управление виджетами")
@Feature("Создание виджетов")
public class WidgetCreationTest extends BaseTest {

    private static final String DASHBOARD_NAME = "DEMO DASHBOARD";
    private static final String LAUNCH_NAME = "Task Progress Widget";
    private static final String WIDGET_DESCRIPTION = "Отслеживание прогресса задач";

    @Test(description = "Проверка создания виджета 'Процент прохождения тестов'")
    @Description("Позитивный тест создания виджета типа 'Passing Rate Per Launch' с валидными параметрами")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Создание стандартного виджета")
     void testCreatePassingRateWidget() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.performLogin(VALID_USERNAME, VALID_PASSWORD);

        WidgetPage widgetPage = new WidgetPage(getDriver());
        widgetPage.clickDashboardButton()
                  .selectDashboardByName(DASHBOARD_NAME)
                  .clickAddNewWidget()
                  .selectPassingRateWidget()
                  .clickNextButton()
                  .enterLaunchName(LAUNCH_NAME)
                  .clickNextButton()
                  .enterDescription(WIDGET_DESCRIPTION)
                  .clickAddButton();

        Assert.assertTrue(
                widgetPage.isWidgetDisplayed(),
                "Виджет не отображается на странице"
        );
    }
}