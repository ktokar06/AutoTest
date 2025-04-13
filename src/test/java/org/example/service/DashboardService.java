package org.example.service;

import io.restassured.response.Response;
import org.example.specification.Specifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.config.MyConfig.*;

public class DashboardService {

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
}