package org.example.test;

import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.example.config.MyConfig.*;
import static org.example.api.ApiDashboard.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class DashboardTest {

    private String createdDashboardId;

    @BeforeMethod
    public void setUp() {
        Map<String, Object> dashboardData = new HashMap<>();
        String dashboardName = "TestDashboard_" + System.currentTimeMillis();
        dashboardData.put("name", dashboardName);
        dashboardData.put("description", "Test description");
        dashboardData.put("share", true);

        Response createResponse = createDashboard(dashboardData, API_TOKEN);
        assertEquals(createResponse.statusCode(), 201, "Не удалось создать дашборд перед тестом");
        createdDashboardId = createResponse.jsonPath().getString("id");
    }

    @AfterMethod
    public void tearDown() {
        if (createdDashboardId != null) {
            Response deleteResponse = deleteDashboard(createdDashboardId, API_TOKEN);
            assertThat("Не удалось удалить дашборд после теста",
                    deleteResponse.statusCode(), is(oneOf(200, 204, 404)));
        }
    }

    @Test(description = "Создание нового dashboard с валидными данными")
    public void testCreateDashboard() {
        Map<String, Object> dashboardData = new HashMap<>();
        String dashboardName = "NewDashboard_" + System.currentTimeMillis();
        dashboardData.put("name", dashboardName);
        dashboardData.put("description", "Test description");
        dashboardData.put("share", true);

        Response response = createDashboard(dashboardData, API_TOKEN);

        assertEquals(response.statusCode(), 201, "Неверный статус код при создании дашборда");
        assertThat(response.jsonPath().getString("id"), not(emptyOrNullString()));
    }

    @Test(description = "Получение информации о созданном dashboard")
    public void testGetDashboard() {
        Response response = getDashboard(createdDashboardId, API_TOKEN);

        assertEquals(response.statusCode(), 200, "Неверный статус код при получении дашборда");
        assertThat(response.jsonPath().getString("id"), equalTo(createdDashboardId));
        assertThat(response.jsonPath().getString("name"), not(emptyOrNullString()));
    }

    @Test(description = "Проверка наличия dashboard в списке")
    public void testDashboardExistsInList() {
        Response getResponse = getDashboard(createdDashboardId, API_TOKEN);
        String dashboardName = getResponse.jsonPath().getString("name");

        Response listResponse = getAllDashboards(API_TOKEN);

        assertEquals(listResponse.statusCode(), 200, "Неверный статус код при получении списка дашбордов");
        assertThat(listResponse.jsonPath().getList("content.name"), hasItem(dashboardName));
    }

    @Test(description = "Попытка создания dashboard без обязательного поля 'name'")
    public void testCreateDashboardWithoutRequiredField() {
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("description", "Dashboard without name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Неверный статус код при создании без имени");
    }

    @Test(description = "Попытка создания dashboard с невалидным токеном авторизации")
    public void testCreateDashboardWithInvalidAuth() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("name", "Unauthorized test dashboard");

        Response response = createDashboard(dashboardData, "invalid_token");

        assertEquals(response.statusCode(), 401, "Неверный статус код при невалидном токене");
    }

    @Test(description = "Проверка получения несуществующего dashboard")
    public void testGetNonExistentDashboard() {
        String fakeId = "999999999";

        Response response = getDashboard(fakeId, API_TOKEN);
        assertEquals(response.statusCode(), 404, "Неверный статус код для несуществующего дашборда");
    }

    @Test(description = "Попытка создания dashboard с пустым именем")
    public void testCreateDashboardWithEmptyName() {
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", "");
        invalidData.put("description", "Dashboard with empty name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Неверный статус код при пустом имени");
    }

    @Test(description = "Попытка создания dashboard с слишком длинным именем")
    public void testCreateDashboardWithLongName() {
        String longName = String.join("", Collections.nCopies(256, "a"));
        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", longName);
        invalidData.put("description", "Dashboard with too long name");

        Response response = createDashboard(invalidData, API_TOKEN);

        assertEquals(response.statusCode(), 400, "Неверный статус код при слишком длинном имени");
    }
}