# Автоматизация тестирования Report Portal

Проект включает UI и API тесты для проверки функционала Report Portal с использованием современных инструментов и паттернов.

## 🛠 Технологический стек
- **Язык**: Java 17+
- **Фреймворки**:
    - Selenium WebDriver (UI тесты)
    - RestAssured (API тесты)
    - TestNG (управление тестами)
- **Отчетность**: Allure
- **Паттерны**:
    - Page Object Model
    - Chain of Invocations
- **Сборка**: Maven 3.8+

## 📊 Генерация отчетов Allure

1. Установите Allure:
```bash
mvn allure:install
```

2. Соберите отчет:
```bash
mvn allure:report
```

3. Запустите сервер с отчетом:
```bash
mvn allure:serve
```


