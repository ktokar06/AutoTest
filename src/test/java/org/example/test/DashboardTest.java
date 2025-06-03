package org.example.test;

import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.example.api.ApiDashboard.*;
import static org.example.config.MyConfig.API_TOKEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class DashboardTest {

    private String createdDashboardId;

    private String createDashboardAndReturnId(String name) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("description", "Auto created");
        data.put("share", false);

        Response response = createDashboard(data, API_TOKEN);
        assertEquals(response.statusCode(), 201, "Dashboard creation should return 201");

        createdDashboardId = response.jsonPath().getString("id");
        return createdDashboardId;
    }

    private void deleteDashboardIfExists() {
        if (createdDashboardId != null) {
            Response response = deleteDashboard(createdDashboardId, API_TOKEN);
            assertThat("Dashboard deletion failed", response.statusCode(), is(oneOf(200, 204, 404)));
            createdDashboardId = null;
        }
    }

    @AfterMethod
    void tearDown() {
        deleteDashboardIfExists();
    }

    @Test(description = "Создание нового dashboard с валидными данными")
    void testCreateDashboard() {
        String name = "Dashboard_" + System.currentTimeMillis();
        String id = createDashboardAndReturnId(name);

        assertThat(id, not(emptyOrNullString()));
    }

    @Test(description = "Получение информации о созданном dashboard")
    void testGetDashboard() {
        String name = "Dashboard_" + System.currentTimeMillis();
        String id = createDashboardAndReturnId(name);

        Response response = getDashboard(id, API_TOKEN);
        assertEquals(response.statusCode(), 200);
        assertThat(response.jsonPath().getString("name"), equalTo(name));
    }

    @Test(description = "Проверка наличия созданного dashboard в списке")
    void testDashboardExistsInList() {
        String name = "Dashboard_" + System.currentTimeMillis();
        createDashboardAndReturnId(name);

        Response response = getAllDashboards(API_TOKEN);
        assertEquals(response.statusCode(), 200);
        assertThat(response.jsonPath().getList("content.name"), hasItem(name));
    }

    @Test(description = "Создание dashboard без обязательного поля 'name'")
    void testCreateDashboardWithoutRequiredField() {
        Map<String, Object> data = new HashMap<>();
        data.put("description", "No name");

        Response response = createDashboard(data, API_TOKEN);
        assertEquals(response.statusCode(), 400);
    }

    @Test(description = "Создание dashboard с невалидным токеном")
    void testCreateDashboardWithInvalidAuth() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Unauthorized");

        Response response = createDashboard(data, "invalid_token");
        assertEquals(response.statusCode(), 401);
    }

    @Test(description = "Получение несуществующего dashboard")
    void testGetNonExistentDashboard() {
        Response response = getDashboard("999999999", API_TOKEN);
        assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Создание dashboard с пустым именем")
    void testCreateDashboardWithEmptyName() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "");
        data.put("description", "Empty name");

        Response response = createDashboard(data, API_TOKEN);
        assertEquals(response.statusCode(), 400);
    }

    @Test(description = "Создание dashboard с слишком длинным именем")
    void testCreateDashboardWithLongName() {
        String longName = String.join("", Collections.nCopies(256, "a"));

        Map<String, Object> data = new HashMap<>();
        data.put("name", longName);
        data.put("description", "Too long");

        Response response = createDashboard(data, API_TOKEN);
        assertEquals(response.statusCode(), 400);
    }
}