package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Класс страницы управления дашбордами в Report Portal.
 * Предоставляет методы для взаимодействия с элементами страницы дашбордов.
 */
public class DashboardPage extends HomePage {

    // Элементы страницы виджетов
    @FindBy(css = ".ghostButton__color-topaz--Z_OvX")
    private WebElement addNewDashboardButton;

    @FindBy(css = ".modalLayout__modal-window--jrhO6 input[type='text']")
    private WebElement dashboardNameInput;

    @FindBy(css = ".modalLayout__modal-window--jrhO6 textarea")
    private WebElement dashboardDescriptionTextarea;

    @FindBy(css = ".bigButton__color-booger--EpRlL")
    private WebElement addButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[1]/aside/div[2]/div[1]/div/div/a")
    protected WebElement buttonDashboard;


    /**
     * Конструктор класса DashboardPage.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Нажимает кнопку добавления нового дашборда.
     *
     * @return текущий экземпляр DashboardPage для fluent-интерфейса
     * @throws org.openqa.selenium.TimeoutException если элемент не стал кликабельным в течение времени ожидания
     */
    @Step("Нажать кнопку 'Добавить новую панель'")
    public DashboardPage clickAddNewDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewDashboardButton)).click();
        return this;
    }

    /**
     * Вводит название для нового дашборда.
     *
     * @param name название дашборда (должно быть не null и не пустым)
     * @return текущий экземпляр DashboardPage для fluent-интерфейса
     * @throws org.openqa.selenium.TimeoutException если поле ввода не стало видимым в течение времени ожидания
     */
    @Step("Ввести название панели: {name}")
    public DashboardPage setDashboardName(String name) {
        wait.until(ExpectedConditions.visibilityOf(dashboardNameInput));
        dashboardNameInput.clear();
        dashboardNameInput.sendKeys(name);
        return this;
    }

    /**
     * Вводит описание для нового дашборда.
     *
     * @param description описание дашборда (может быть null)
     * @return текущий экземпляр DashboardPage для fluent-интерфейса
     * @throws org.openqa.selenium.TimeoutException если поле ввода не стало видимым в течение времени ожидания
     */
    @Step("Ввести описание панели: {description}")
    public DashboardPage setDashboardDescription(String description) {
        wait.until(ExpectedConditions.visibilityOf(dashboardDescriptionTextarea));
        dashboardDescriptionTextarea.clear();
        dashboardDescriptionTextarea.sendKeys(description);
        return this;
    }

    /**
     * Подтверждает создание нового дашборда.
     *
     * @return текущий экземпляр DashboardPage для fluent-интерфейса
     * @throws org.openqa.selenium.TimeoutException если кнопка не стала кликабельной в течение времени ожидания
     */
    @Step("Подтвердить создание панели")
    public DashboardPage clickAddDashboardConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        return this;
    }

    /**
     * Переходит на страницу дашбордов через навигационное меню.
     *
     * @return текущий экземпляр DashboardPage для fluent-интерфейса
     * @throws org.openqa.selenium.TimeoutException если кнопка навигации не стала кликабельной в течение времени ожидания
     */
    @Step("Нажатие кнопки 'Дашборд' в навигационном меню")
    public DashboardPage clickDashboardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonDashboard)).click();
        return this;
    }

    /**
     * Создает новый дашборд с указанными параметрами.
     *
     * @param name название дашборда (должно быть не null и не пустым)
     * @param description описание дашборда (может быть null)
     * @return текущий экземпляр DashboardPage для fluent-интерфейса
     * @throws org.openqa.selenium.TimeoutException если любой из элементов не стал доступным для взаимодействия
     */
    @Step("Создать новую панель с именем: {name} и описанием: {description}")
    public DashboardPage createNewDashboard(String name, String description) {
        return clickAddNewDashboard()
                .setDashboardName(name)
                .setDashboardDescription(description)
                .clickAddDashboardConfirm();
    }
}