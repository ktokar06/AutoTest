# Описание проекта

Автоматизированное тестирование функционала **ReportPortal** с использованием:
- **Selenium WebDriver** для UI-тестов
- **TestNG** как фреймворк для тестирования
- **Allure** для генерации отчетов
- **Page Object Model** как основной паттерн

## Требования
- **Java** 17+
- **Maven** 3.8+
- **Chrome** 100+
- **Docker** (для локального запуска ReportPortal)

## Закаментируй код для прохождения API тестов

```java
@BeforeMethod
public void setUp() {
    // driver = new FirefoxDriver();
    // driver.manage().window().maximize();
    // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    // driver.get(URL_PORTAL_DEMO);
}
```

## Запуск Allure отчетов
1. **Сборка HTML-отчета** (результаты в `target/site/allure-maven-plugin`):
   ```bash
   mvn allure:report
   ```

2. **Запуск локального сервера с отчётом** (автоматически открывает браузер):
   ```bash
   mvn allure:serve
   ```

### 1. UI Тест (Selenium WebDriver)
**Тест 1: Создание нового Widget**
- Войти в систему Report Portal (логин: `default`; пароль: `1q2w3e`).
- Перейти на существующий Dashboard.
- Добавить новый Widget типа "Task Progress".
- Проверить, что Widget успешно добавлен и отображается на Dashboard.

### 2. API Тесты (RestAssured)
**Тест 2: Создание нового Dashboard**
- Выполнить POST запрос к эндпоинту `/api/v1/dashboards` с необходимыми данными (см. документацию в разделе Ресурсы).
- Проверить, что Dashboard успешно создан (статус код 201) и присутствует в списке Dashboard'ов.

**Тест 3: Создание Dashboard с недостаточными параметрами (Негативный тест)**
- Выполнить POST запрос к эндпоинту `/api/v1/dashboards` с пропущенными обязательными параметрами (см. документацию в разделе Ресурсы).
- Проверить, что Dashboard не создан (например, статус код 400).
- Убедиться, что Dashboard с неполными параметрами не существует в списке Dashboard'ов.
