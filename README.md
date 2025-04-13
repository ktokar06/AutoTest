# Заметка
По этой ссылке https://demo.reportportal.io/  не смог сделать запрашивал мои личные данные GitHub. Создал личный сервер

# Установка и запуск ReportPortal

## Описание проекта
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

## Шаг 1: Настройка и развертывание ReportPortal

Руководство по развертыванию на Linux/Mac/Windows.

### 1. Загрузка и настройка(уже в проекте есть)
1. Скачайте последнюю версию Docker compose файла:
   ```bash
   curl -LO https://raw.githubusercontent.com/reportportal/reportportal/master/docker-compose.yml
   ```

### 2. Запуск приложения
```bash
docker compose -p reportportal up -d --force-recreate
```

**Параметры команды:**
- `-p reportportal` - префикс проекта для контейнеров
- `up` - создание и запуск контейнеров
- `-d` - демонизированный режим
- `--force-recreate` - принудительное пересоздание контейнеров

## Шаг 2: Запуск ReportPortal

### Доступ к интерфейсу
- Для локального развертывания:  
  `http://localhost:8080`

### Учетные данные по умолчанию
| Роль       | Логин      | Пароль  |
|------------|------------|---------|
| Пользователь | `default`  | `1q2w3e` |
| Администратор | `superadmin` | `erebus` |


### Генерация отчета Allure

```bash
allure install
```

```bash
allure report
```

```bash
allure serve allure-results
```

## Примеры тестов

### 1. Тест: Логин

![Логин тест](/project-root/photo/LoginTest.png)

---

### 2. Тест: DataProvider

![DataProvider тест](/project-root/photo/DataTest.png)

---

### 3. Тест: Widget

![Widget тест](/photo/widget_test.png)

---


Export the certificate from your local server:
bash
Copy

openssl s_client -connect localhost:8080 -showcerts </dev/null | openssl x509 -outform PEM > localhost.pem