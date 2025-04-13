# Описание проекта
Автоматизированное тестирование функционала ReportPortal с использованием:
- **Selenium WebDriver** для UI-тестов
- **TestNG** как фреймворк для тестирования
- **Allure** для генерации отчетов
- **Page Object Model** как основной паттерн

## Требования
- Java 17+
- Maven 3.8+
- Chrome 100+
- Docker  (для локального запуска ReportPortal)

## Закаментируй чтоб прошли тесты api

```java
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(URL_PORTAL_DEMO);
    }
```

## Запуск Allure отчет
1. Сборка HTML-отчета (результаты в target/site/allure-maven-plugin)
mvn allure:report

2. Запуск локального сервера с отчётом (автоматически открывает браузер)
mvn allure:serve

## Так же есть проекты по автоматизации тестирования (посмотрите)
Названия: UI, API-DB

