package org.example.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class Specifications {

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