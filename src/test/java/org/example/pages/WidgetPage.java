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

    @FindBy(css = "a.dashboardTable__name--t2a89")
    private WebElement dashboardName;

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

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[1]/aside/div[2]/div[3]/div/div/a")
    protected WebElement buttonFilters;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[1]/aside/div[2]/div[1]/div/div/a")
    protected WebElement buttonDashboard;

    @FindBy(css = ".widgetHeader__type--yZiVg")
    private WebElement widgetType;

    /**
     * Конструктор класса WidgetPage
     * @param driver WebDriver экземпляр драйвера
     */
    public WidgetPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Переход в раздел дашбордов
     * @return текущий экземпляр WidgetPage
     */
    @Step("Переход в раздел дашбордов")
    public WidgetPage clickDashboardButton() {
        if (WaitUtils.waitForElementPresence(driver, By.xpath("/html/body/div[1]/div/div/div/div/div[1]/aside/div[2]/div[1]/div/div/a"))) {
            buttonDashboard.click();
        }
        return this;
    }

    /**
     * Выбор дашборда по имени
     * @param dashboardName название дашборда для выбора
     * @return текущий экземпляр WidgetPage
     */
    @Step("Выбор дашборда '{dashboardName}'")
    public WidgetPage selectDashboardByName(String dashboardName) {
        String xpath = String.format("//a[contains(@class, 'dashboardTable__name') and contains(text(), '%s')]", dashboardName);
        if (WaitUtils.waitForElementPresence(driver, By.xpath(xpath))) {
            driver.findElement(By.xpath(xpath)).click();
        }
        return this;
    }

    /**
     * Начало процесса создания нового виджета
     * @return текущий экземпляр WidgetPage
     */
    @Step("Начало создания нового виджета")
    public WidgetPage clickAddNewWidget() {
        if (WaitUtils.waitForElementPresence(driver, By.cssSelector(".dashboardItemPage__buttons-block--QoL50 button.ghostButton__ghost-button--r7c9T"))) {
            buttonAddNewWidget.click();
        }
        return this;
    }

    /**
     * Выбор типа виджета 'Passing Rate Per Launch'
     * @return текущий экземпляр WidgetPage
     */
    @Step("Выбор типа виджета 'Passing Rate Per Launch'")
    public WidgetPage selectPassingRateWidget() {
        if (WaitUtils.waitForElementPresence(driver, By.xpath("//label[./input[@name='widget-type' and @value='passingRatePerLaunch']]"))) {
            buttonPassingRatePerLaunch.click();
        }
        return this;
    }

    /**
     * Переход к следующему шагу мастера создания виджета
     * @return текущий экземпляр WidgetPage
     */
    @Step("Переход к следующему шагу создания виджета")
    public WidgetPage clickNextButton() {
        if (WaitUtils.waitForElementPresence(driver, By.xpath("//button[.//span[contains(text(), 'Next step')]]"))) {
            buttonNext.click();
        }
        return this;
    }

    /**
     * Ввод названия запуска для виджета
     * @param launchName название запуска
     * @return текущий экземпляр WidgetPage
     */
    @Step("Ввод названия запуска: {launchName}")
    public WidgetPage enterLaunchName(String launchName) {
        if (WaitUtils.waitForElementPresence(driver, By.className("singleAutocomplete__input--UgN6e"))) {
            launchNameField.sendKeys(launchName);
        }
        return this;
    }

    /**
     * Ввод описания виджета
     * @param description текст описания
     * @return текущий экземпляр WidgetPage
     */
    @Step("Ввод описания виджета: {description}")
    public WidgetPage enterDescription(String description) {
        if (WaitUtils.waitForElementPresence(driver, By.className("inputTextArea__input-text-area--N0goa"))) {
            descriptionField.sendKeys(description);
        }
        return this;
    }

    /**
     * Завершение процесса создания виджета
     */
    @Step("Завершение создания виджета")
    public void clickAddButton() {
        if (WaitUtils.waitForElementPresence(driver, By.cssSelector("button.bigButton__big-button--BmG4Q"))) {
            addWidgetButton.click();
        }
    }

    /**
     * Проверка отображения созданного виджета
     * @return true если виджет отображается, false в противном случае
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