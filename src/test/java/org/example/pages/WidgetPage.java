package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
     * Конструктор инициализирует страницу виджетов.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public WidgetPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Нажимает на название дашборда для перехода к его редактированию.
     *
     * @return текущий экземпляр {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Нажатие на название дашборда")
    public WidgetPage clickDashboardName() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardName)).click();
        return this;
    }

    /**
     * Инициирует процесс добавления нового виджета на дашборд.
     *
     * @return текущий экземпляр {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Нажатие кнопки 'Добавить новый виджет'")
    public WidgetPage clickAddNewWidget() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddNewWidget)).click();
        return this;
    }

    /**
     * Выбирает тип виджета "Процент прохождения тестов по запускам".
     *
     * @return текущий экземпляр {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Выбор типа виджета 'Процент прохождения тестов по запускам'")
    public WidgetPage selectPassingRateWidget() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPassingRatePerLaunch)).click();
        return this;
    }

    /**
     * Переходит к следующему шагу в процессе создания виджета.
     *
     * @return текущий экземпляр {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Нажатие кнопки 'Далее'")
    public WidgetPage clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonNext)).click();
        return this;
    }

    /**
     * Вводит название запуска для настройки виджета.
     *
     * @param launchName название тестового запуска
     * @return текущий экземпляр {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Ввод названия запуска: {launchName}")
    public WidgetPage enterLaunchName(String launchName) {
        wait.until(ExpectedConditions.elementToBeClickable(launchNameField)).sendKeys(launchName);
        return this;
    }

    /**
     * Вводит описание создаваемого виджета.
     *
     * @param description текстовое описание виджета
     * @return текущий экземпляр {@link WidgetPage} для поддержки цепочки вызовов
     */
    @Step("Ввод описания виджета: {description}")
    public WidgetPage enterDescription(String description) {
        wait.until(ExpectedConditions.elementToBeClickable(descriptionField)).sendKeys(description);
        return this;
    }

    /**
     * Завершает процесс создания виджета.
     */
    @Step("Подтверждение создания виджета")
    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addWidgetButton)).click();
    }

    /**
     * Проверяет, что виджет отображается на странице.
     * Проверка осуществляется по наличию имени виджета и его типа.
     *
     * @return {@code true} если виджет отображается, {@code false} в случае исключения таймаута
     */
    @Step("Проверка отображения виджета")
    public boolean isWidgetDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(createdWidgetName)).isDisplayed() &&
                    wait.until(ExpectedConditions.visibilityOf(widgetType)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Нажимает кнопку "Фильтры" в интерфейсе Report Portal.
     *
     * @return новый экземпляр {@link WidgetPage} для поддержки fluent-интерфейса
     * @throws TimeoutException если кнопка не становится кликабельной в течение заданного времени ожидания
     */
    @Step("Нажатие кнопки 'Фильтры'")
    public WidgetPage clickFiltersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonFilters)).click();
        return new WidgetPage(driver);
    }

    /**
     * Переходит на страницу дашбордов через навигационное меню.
     *
     * @return текущий экземпляр {@link WidgetPage} для поддержки fluent-интерфейса
     * @throws TimeoutException если кнопка навигации не стала кликабельной в течение времени ожидания
     */
    @Step("Нажатие кнопки 'Дашборд' в навигационном меню")
    public WidgetPage clickDashboardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonDashboard)).click();
        return this;
    }
}