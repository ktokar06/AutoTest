package org.example.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Класс для создания спецификаций запросов RestAssured.
 * Позволяет централизованно настраивать общие параметры запросов,
 * такие как базовый URL, заголовки, контент типы и SSL настройки.
 */
public class Specifications {

    /**
     * Создает спецификацию запроса с аутентификацией по Bearer-токену.
     *
     * @param baseUrl базовый URL для запросов
     * @param token   Bearer-токен для авторизации
     * @return спецификация запроса с заданными параметрами
     */
    public static RequestSpecification authRequestSpec(String baseUrl, String token) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .setConfig(RestAssuredConfig.config().sslConfig(
                        new SSLConfig().relaxedHTTPSValidation()))
                .build();
    }
}