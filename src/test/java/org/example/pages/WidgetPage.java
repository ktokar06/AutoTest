package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Класс страницы управления виджетами в Report Portal.
 * Предоставляет методы для создания и управления виджетами на дашбордах.
 */
public class WidgetPage extends HomePage {

    @FindBy(css = ".dashboardItemPage__buttons-block--QoL50 button.ghostButton__ghost-button--r7c9T")
    private WebElement buttonAddNewWidget;

    @FindBy(xpath = "//label[./input[@name='widget-type' and @value='passingRatePerLaunch']]")
    private WebElement buttonPassingRatePerLaunch;

    @FindBy(xpath = "//button[.//span[contains(text(), 'Next step')]]")
    private WebElement buttonNext;

    @FindBy(className = "singleAutocomplete__input--UgN6e")
    private WebElement launchNameField;

    @FindBy(className = "inputTextArea__input-text-area--N0goa")
    private WebElement descriptionField;

    @FindBy(css = "button.bigButton__big-button--BmG4Q")
    private WebElement addWidgetButton;

    @FindBy(css = ".widgetHeader__widget-name-block--AOAHS")
    private WebElement createdWidgetName;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[1]/aside/div[2]/div[1]/div/div/a")
    protected WebElement buttonDashboard;

    @FindBy(css = ".widgetHeader__type--yZiVg")
    private WebElement widgetType;

    /**
     * Конструктор класса WidgetPage.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public WidgetPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Нажимает кнопку перехода в раздел дашбордов.
     *
     * @return текущий экземпляр WidgetPage для цепочки вызовов
     */
    @Step("Переход в раздел дашбордов")
    public WidgetPage clickDashboardButton() {
        buttonDashboard.click();
        return this;
    }

    /**
     * Выбирает дашборд по указанному имени.
     *
     * @param dashboardName имя дашборда для выбора
     * @return текущий экземпляр WidgetPage для цепочки вызовов
     */
    @Step("Выбор дашборда '{dashboardName}'")
    public WidgetPage selectDashboardByName(String dashboardName) {
        String xpath = String.format("//a[contains(@class, 'dashboardTable__name') and contains(text(), '%s')]", dashboardName);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    /**
     * Начинает процесс создания нового виджета, нажимая соответствующую кнопку.
     *
     * @return текущий экземпляр WidgetPage для цепочки вызовов
     */
    @Step("Начало создания нового виджета")
    public WidgetPage clickAddNewWidget() {
        buttonAddNewWidget.click();
        return this;
    }

    /**
     * Выбирает тип виджета 'Passing Rate Per Launch'.
     *
     * @return текущий экземпляр WidgetPage для цепочки вызовов
     */
    @Step("Выбор типа виджета 'Passing Rate Per Launch'")
    public WidgetPage selectPassingRateWidget() {
        buttonPassingRatePerLaunch.click();
        return this;
    }

    /**
     * Переходит к следующему шагу в процессе создания виджета.
     *
     * @return текущий экземпляр WidgetPage для цепочки вызовов
     */
    @Step("Переход к следующему шагу создания виджета")
    public WidgetPage clickNextButton() {
        buttonNext.click();
        return this;
    }

    /**
     * Вводит название запуска для виджета.
     *
     * @param launchName название запуска
     * @return текущий экземпляр WidgetPage для цепочки вызовов
     */
    @Step("Ввод названия запуска: {launchName}")
    public WidgetPage enterLaunchName(String launchName) {
        launchNameField.sendKeys(launchName);
        return this;
    }

    /**
     * Вводит описание для создаваемого виджета.
     *
     * @param description текст описания виджета
     * @return текущий экземпляр WidgetPage для цепочки вызовов
     */
    @Step("Ввод описания виджета: {description}")
    public WidgetPage enterDescription(String description) {
        descriptionField.sendKeys(description);
        return this;
    }

    /**
     * Завершает процесс создания виджета, нажимая кнопку добавления.
     */
    @Step("Завершение создания виджета")
    public void clickAddButton() {
        addWidgetButton.click();
    }

    /**
     * Проверяет, отображается ли созданный виджет на странице.
     * Использует WaitUtils для ожидания появления элементов.
     *
     * @return true если виджет и его тип отображаются, false в противном случае
     */
    @Step("Проверка отображения созданного виджета")
    public boolean isWidgetDisplayed() {
        try {
            return WaitUtils.waitForElementPresence(driver, By.cssSelector(".widgetHeader__widget-name-block--AOAHS")) &&
                    WaitUtils.waitForElementPresence(driver, By.cssSelector(".widgetHeader__type--yZiVg"));
        } catch (TimeoutException e) {
            return false;
        }
    }
}