package org.example.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.preemptive;
import static org.example.config.MyConfig.VALID_PASSWORD;
import static org.example.config.MyConfig.VALID_USERNAME;

public class Specifications {

    public static RequestSpecification authRequestSpec(String baseUrl, String token) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .setAuth(preemptive().basic(VALID_USERNAME, VALID_PASSWORD))
                .setConfig(RestAssuredConfig.config().sslConfig(
                        new SSLConfig().relaxedHTTPSValidation()))
                .build();
    }
}