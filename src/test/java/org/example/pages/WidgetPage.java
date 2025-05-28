package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница управления виджетами в Report Portal.
 * Содержит методы для создания, настройки и проверки виджетов.
 */
public class WidgetPage extends HomePage {

    @FindBy(css = ".dashboardItemPage__buttons-block--QoL50 button.ghostButton__ghost-button--r7c9T")
    private WebElement buttonAddNewWidget;

    @FindBy(css = "label > input[name='widget-type'][value='passingRatePerLaunch']")
    private WebElement buttonPassingRatePerLaunch;

    @FindBy(css = "button span:contains('Next step')")
    private WebElement buttonNext;

    @FindBy(css = ".singleAutocomplete__input--UgN6e")
    private WebElement launchNameField;

    @FindBy(css = ".inputTextArea__input-text-area--N0goa")
    private WebElement descriptionField;

    @FindBy(css = "button.bigButton__big-button--BmG4Q")
    private WebElement addWidgetButton;

    @FindBy(css = ".widgetHeader__widget-name-block--AOAHS")
    private WebElement createdWidgetName;

    @FindBy(css = ".widgetHeader__type--yZiVg")
    private WebElement widgetType;

    @FindBy(css = "aside div:nth-child(2) div:nth-child(1) div div a")
    private WebElement buttonDashboard;

    @FindBy(css = ".singleAutocomplete__option--UgN6e")
    private WebElement firstAutocompleteOption;

    /**
     * Создаёт новый экземпляр страницы управления виджетами.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public WidgetPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Переходит в раздел дашбордов.
     *
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Переход в раздел дашбордов")
    public WidgetPage clickDashboardButton() {
        buttonDashboard.click();
        return this;
    }

    /**
     * Выбирает дашборд по его названию.
     *
     * @param dashboardName имя дашборда для выбора
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Выбор дашборда '{dashboardName}'")
    public WidgetPage selectDashboardByName(String dashboardName) {
        String dashboardXPath = String.format("//a[contains(@class, 'dashboardTable__name') and contains(text(), '%s')]", dashboardName);
        getDriver().findElement(By.xpath(dashboardXPath)).click();
        return this;
    }

    /**
     * Начинает процесс создания нового виджета.
     *
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Начало создания нового виджета")
    public WidgetPage clickAddNewWidget() {
        buttonAddNewWidget.click();
        return this;
    }

    /**
     * Выбирает тип виджета "Passing Rate Per Launch".
     *
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Выбор типа виджета 'Passing Rate Per Launch'")
    public WidgetPage selectPassingRateWidget() {
        buttonPassingRatePerLaunch.click();
        return this;
    }

    /**
     * Переходит к следующему шагу создания виджета.
     *
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
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
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Ввод названия запуска: {launchName}")
    public WidgetPage enterLaunchName(String launchName) {
        launchNameField.clear();
        launchNameField.sendKeys(launchName);
        return this;
    }

    /**
     * Выбирает первый вариант из автозаполнения списка названий запусков.
     *
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Выбор первого варианта из автокомплита")
    public WidgetPage selectFirstLaunchNameAutocompleteOption() {
        firstAutocompleteOption.click();
        return this;
    }

    /**
     * Вводит описание виджета.
     *
     * @param description описание виджета
     * @return текущий объект {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Ввод описания виджета: {description}")
    public WidgetPage enterDescription(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);
        return this;
    }

    /**
     * Завершает создание виджета, нажимая кнопку "Add".
     */
    @Step("Завершение создания виджета")
    public void clickAddButton() {
        addWidgetButton.click();
    }

    /**
     * Проверяет, что виджет отображается на странице после создания.
     *
     * @return {@code true}, если виджет и его тип видны, иначе {@code false}
     */
    @Step("Проверка отображения созданного виджета")
    public boolean isWidgetDisplayed() {
        try {
            return WaitUtils.waitForElementPresence(getDriver(), createdWidgetName) &&
                    WaitUtils.waitForElementPresence(getDriver(), widgetType);
        } catch (Exception e) {
            return false;
        }
    }
}