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

### Установка и запуск отчета

Для начала установки Allure выполните команду:

```bash
mvn allure:install
```

Затем соберите отчет командой:

```bash
mvn allure:report
```

И запустите сервер с отчетом следующим образом:

```bash
mvn allure:serve
```

---

### Настройка тестов

Перед проведением тестирования добавьте в файл `MyConfig` следующие данные:

- Логин и пароль для входа в систему.
- Адрес портала (`URL_PORTAL_DEMO`), пример: `https://demo.reportportal.io/ui/`
- Адрес API (`URL_PORTAL_DEMO_API`), формируется добавлением `/api`, пример: `https://demo.reportportal.io/api`
- Токен API (`API_TOKEN`) — генерируется через профиль аккаунта по ссылке `Profile → API Keys → Generate API Key`.

---

### Документация

Ссылка на техническое задание доступна по адресу:

- [Документация](https://docs.google.com/document/d/1M5Ith8l06sIq2L2BzIx_xXipKUbZWoHQ8Y0Qq2ccn54/edit?tab=t.0)