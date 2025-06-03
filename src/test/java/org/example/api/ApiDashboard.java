package org.example.api;

import io.restassured.response.Response;
import org.example.specification.Specifications;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.config.MyConfig.*;

/**
 * Класс для работы с API дашбордов Report Portal.
 */
public class ApiDashboard {

    /**
     * Создает новый дашборд.
     *
     * @param body тело запроса (JSON), содержащее ключи: "name", "description", "share"
     * @param token Bearer-токен авторизации
     * @return ответ сервера в формате {@link Response}
     */
    public static Response createDashboard(Map<String, Object> body, String token) {
        return given()
                .spec(Specifications.authRequestSpec(URL_PORTAL_DEMO_API, token))
                .body(body)
                .when()
                .post(DASHBOARDS_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Получает информацию о дашборде по его идентификатору.
     *
     * @param id идентификатор дашборда
     * @param token Bearer-токен авторизации
     * @return ответ сервера в формате {@link Response}
     */
    public static Response getDashboard(String id, String token) {
        return given()
                .spec(Specifications.authRequestSpec(URL_PORTAL_DEMO_API, token))
                .when()
                .get(DASHBOARDS_ENDPOINT + "/" + id)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Получает список всех доступных дашбордов.
     *
     * @param token Bearer-токен авторизации
     * @return ответ сервера с пагинированным списком дашбордов в формате {@link Response}
     */
    public static Response getAllDashboards(String token) {
        return given()
                .spec(Specifications.authRequestSpec(URL_PORTAL_DEMO_API, token))
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get(DASHBOARDS_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Удаляет дашборд по его идентификатору.
     *
     * @param id идентификатор дашборда, который необходимо удалить
     * @param token Bearer-токен авторизации
     * @return ответ сервера в формате {@link Response}, код 200 или 204 при успешном удалении
     */
    public static Response deleteDashboard(String id, String token) {
        return given()
                .spec(Specifications.authRequestSpec(URL_PORTAL_DEMO_API, token))
                .when()
                .delete(DASHBOARDS_ENDPOINT + "/" + id)
                .then()
                .log().all()
                .extract()
                .response();
    }
}