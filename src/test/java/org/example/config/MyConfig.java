package org.example.config;

public class MyConfig {

    /**
     * Report Portal URL.
     */
    public static final String URL_PORTAL_DEMO = "https://demo.reportportal.io/";

    /**
     * Валидный логин пользователя.
     */
    public static final String VALID_USERNAME = "default";

    /**
     * Валидный пароль пользователя.
     */
    public static final String VALID_PASSWORD = "1q2w3e";

    /**
     * Валидный пароль пользователя.
     */
    public static final String VALID_PASSWORD_GIT = "-------";

    /**
     * Валидный логин пользователя.
     */
    public static final String VALID_USERNAME_GIT = "----";

    /**
     * Тестовое название дашборда с временной меткой для обеспечения уникальности
     */
    public static final String TEST_DASHBOARD_NAME = "Test Dashboard";

    /**
     * URL-адрес Report Portal.
     */
    public static final String URL_PORTAL_DEMO_API = "https://demo.reportportal.io/api";

    /**
     * Название проекта.
     */
    public static final String PROJECT_NAME = "default";

    /**
     * Конечные точки API.
     * Эндпоинт для работы с дашбордами формируется с учётом имени проекта.
     */
    public static final String DASHBOARDS_ENDPOINT = "/v1/" + PROJECT_NAME + "/dashboard";

    /**
     * Токен аутентификации для доступа к API.
     */
    public static final String API_TOKEN = "test_qNWMYjq3Q72oz5on9ZMAcp25d4vvDZuCXQ35HChHgVMvVv-gjvSHIk_p1O7Ea2wH";
}
