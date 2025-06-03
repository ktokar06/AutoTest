package org.example.api;

import io.restassured.response.Response;
import org.example.specification.Specifications;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.config.MyConfig.*;

/**
 * Класс для работы с API дашбордов Report Portal.
 * Предоставляет методы для создания, получения, удаления и списка дашбордов.
 */
public class ApiDashboard {

    /**
     * Создает новый дашборд.
     *
     * @param body тело запроса в формате JSON
     * @param token Bearer-токен для авторизации
     * @return ответ от сервера
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
     * Получает дашборд по его ID.
     *
     * @param id идентификатор дашборда
     * @param token Bearer-токен для авторизации
     * @return ответ от сервера
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
     * @param token Bearer-токен для авторизации
     * @return ответ от сервера
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
     * Удаляет дашборд по ID.
     *
     * @param id идентификатор дашборда
     * @param token Bearer-токен для авторизации
     * @return ответ от сервера
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