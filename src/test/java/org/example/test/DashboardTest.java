package org.example.test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.example.config.MyConfig.*;
import static org.example.api.ApiDashboard.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class DashboardTest{

    private String createdDashboardId;
    private static final String TEST_DASHBOARD_NAME = "My name is project" + System.currentTimeMillis();

    @Test(description = "Создание нового dashboard с валидными данными")
    void testCreateDashboard() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("name", TEST_DASHBOARD_NAME);
        dashboardData.put("description", "Test description");
        dashboardData.put("share", true);

        Response createResponse = createDashboard(dashboardData, API_TOKEN);
        System.out.println("Create Response: " + createResponse.getBody().asString());

        assertEquals(createResponse.statusCode(), 201);
        String id = createResponse.jsonPath().getString("id");
        assertThat(id, not(emptyOrNullString()));

        Response getResponse = getDashboard(id, API_TOKEN);
        System.out.println("Get Response: " + getResponse.getBody().asString());

        assertEquals(getResponse.statusCode(), 200);
        assertThat(getResponse.jsonPath().getString("name"), equalTo(TEST_DASHBOARD_NAME));

        createdDashboardId = id;
    }

    @Test(description = "Получение информации о созданном dashboard")
    void testGetDashboard() {
        Response response = getDashboard(createdDashboardId, API_TOKEN);

        assertEquals(response.statusCode(), 200, "Статус код должен быть 200 (OK)");
        assertThat(response.jsonPath().getString("id"), equalTo(createdDashboardId));
        assertThat(response.jsonPath().getString("name"), equalTo(TEST_DASHBOARD_NAME));
    }

    @Test(description = "Получение информации о несуществующем dashboard")
    void testDashboardExistsInList() {
        Response response = getAllDashboards(API_TOKEN);

        assertEquals(response.statusCode(), 200, "Статус код должен быть 200 (OK)");
        assertThat(response.jsonPath().getList("content.name"), hasItem(TEST_DASHBOARD_NAME));
    }

    @Test(description = "Попытка создания dashboard без обязательного поля 'name'")
    void testCreateDashboardWithoutRequiredField() {
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("description", "Dashboard without name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Статус код должен быть 400 (Bad Request)");
    }

    @Test(description = "Попытка создания dashboard с невалидным токеном авторизации")
    void testCreateDashboardWithInvalidAuth() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("name", "Unauthorized test dashboard");

        Response response = createDashboard(dashboardData, "invalid_token");

        assertEquals(response.statusCode(), 401, "Статус код должен быть 401 (Unauthorized)");
    }

    @Test(description = "Проверка получения несуществующего dashboard")
    void testGetNonExistentDashboard() {
        String fakeId = "999999999";

        Response response = getDashboard(fakeId, API_TOKEN);
        assertEquals(response.statusCode(), 404, "Статус код должен быть 404 (Not Found)");
    }


    @Test(description = "Попытка создания dashboard с пустым именем")
    void testCreateDashboardWithEmptyName() {
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", "");
        invalidData.put("description", "Dashboard with empty name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Статус код должен быть 400 (Bad Request)");
    }

    @Test(description = "Попытка создания dashboard с слишком длинным именем")
    void testCreateDashboardWithLongName() {
        String longName = String.join("", Collections.nCopies(256, "a"));
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", longName);
        invalidData.put("description", "Dashboard with too long name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Статус код должен быть 400 (Bad Request)");
    }
}