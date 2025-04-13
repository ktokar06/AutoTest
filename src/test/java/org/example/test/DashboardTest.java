package org.example.test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.example.config.MyConfig.*;
import static org.example.service.DashboardService.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class DashboardTest extends BaseTest {

    @Test(description = "Создание нового dashboard с валидными данными")
    public void testCreateDashboard() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("name", TEST_DASHBOARD_NAME);
        dashboardData.put("description", "Test description");
        dashboardData.put("share", true);

        Response response = createDashboard(dashboardData, API_TOKEN);

        assertEquals(response.statusCode(), 201, "Статус код должен быть 201 (Created)");
        assertThat(response.jsonPath().getString("name"), equalTo(TEST_DASHBOARD_NAME));
        assertThat(response.jsonPath().getString("id"), not(emptyOrNullString()));

        createdDashboardId = response.jsonPath().getString("id");
    }

    @Test(description = "Получение информации о созданном dashboard")
    public void testGetDashboard() {
        Response response = getDashboard(createdDashboardId, API_TOKEN);

        assertEquals(response.statusCode(), 200, "Статус код должен быть 200 (OK)");
        assertThat(response.jsonPath().getString("id"), equalTo(createdDashboardId));
        assertThat(response.jsonPath().getString("name"), equalTo(TEST_DASHBOARD_NAME));
    }

    @Test(description = "Проверка наличия созданного dashboard в общем списке")
    public void testDashboardExistsInList() {
        Response response = getAllDashboards(API_TOKEN);

        assertEquals(response.statusCode(), 200, "Статус код должен быть 200 (OK)");
        assertThat(response.jsonPath().getList("name"), hasItem(TEST_DASHBOARD_NAME));
    }

    @Test(description = "Попытка создания dashboard без обязательного поля 'name'")
    public void testCreateDashboardWithoutRequiredField() {
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("description", "Dashboard without name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Статус код должен быть 400 (Bad Request)");
    }

    @Test(description = "Попытка создания dashboard с невалидным токеном авторизации")
    public void testCreateDashboardWithInvalidAuth() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("name", "Unauthorized test dashboard");

        Response response = createDashboard(dashboardData, "invalid_token");

        assertEquals(response.statusCode(), 401, "Статус код должен быть 401 (Unauthorized)");
    }

    @Test(description = "Попытка получения информации о несуществующем dashboard")
    public void testGetNonExistentDashboard() {
        Response response = getDashboard("non-existent-id", API_TOKEN);

        assertEquals(response.statusCode(), 404, "Статус код должен быть 404 (Not Found)");
    }

    @Test(description = "Попытка создания dashboard с пустым именем")
    public void testCreateDashboardWithEmptyName() {
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", "");
        invalidData.put("description", "Dashboard with empty name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Статус код должен быть 400 (Bad Request)");
    }

    @Test(description = "Попытка создания dashboard с слишком длинным именем")
    public void testCreateDashboardWithLongName() {
        String longName = new String(new char[256]).replace('\0', 'a');
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", longName);
        invalidData.put("description", "Dashboard with too long name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Статус код должен быть 400 (Bad Request)");
    }
}