package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.PostJson.FullJson;
import org.junit.jupiter.api.*;
import steps.Steps;

import static config.DataBase.*;
import static config.Endpoints.*;
import static io.qameta.allure.SeverityLevel.*;
import static model.PostJson.TestDataJson.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("Позитивное тестирование сервиса API - Entity, по созданию сущностей")
@DisplayName("Тест-кейсы Api - POST, GET, PUT, DELETE, GET(getAll)")
public class ApiTests extends Steps {

    @BeforeAll
    @Step("Подготовка тестового окружения, обнуление(удаление->создание) БД")
    public static void setUp() {
        resetDB(DB_LINK, DB_USER, DB_PASSWORD);
    }

    @Test
    @DisplayName("Тест №01 - Создание сущности")
    @Description(value = "Создать сущность методом POST - create")
    @Severity(BLOCKER)
    void createSomeTest() throws JsonProcessingException {
        // Создаем сущность, проверяем код ответа.
        createSomeWithValues(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS, TITLE, VERIFIED)
                .statusCode(200);
    }

    @Test
    @DisplayName("Тест №02 - Удаление сущности")
    @Description(value = "Удалить сущность, методом DELETE - delete/{id}, проверить что сущность удалена")
    @Severity(BLOCKER)
    void deleteSomeTest() throws JsonProcessingException {
        // Создаем сущность, проверяем код ответа, берем id сущности
        int entityId = Integer.parseInt(createSomeWithStaticTestData().statusCode(200).extract().asString());
        //Удаляем сущность
        deleteSome(entityId).statusCode(204);
        //Проверяем методом GET с использованием ID что сущность была удалена
        RestAssured.given()
                .baseUri(useMethodWithId("GET", entityId))
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Тест №03 - Получение всех сущностей")
    @Description(value = "Создать несколько сущностей, проверить получение сущностей методом GET - getAll")
    @Severity(CRITICAL)
    void getAllSomeTest() throws JsonProcessingException {
        //Подготовка тестового окружения, обнуление(удаление->создание) БД
        resetDB(DB_LINK, DB_USER, DB_PASSWORD);
        // Создаем сущности, проверяем код ответа, берем id сущностей
        int entityId = Integer.parseInt(createSomeWithValues(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS,
                TITLE, VERIFIED)
                .statusCode(200).extract().asString());
        int entityId2 = Integer.parseInt(createSomeWithValues(ADDITIONAL_INFO2, ADDITIONAL_NUMBER2, IMPORTANT_NUMBERS2,
                TITLE2, VERIFIED2)
                .statusCode(200).extract().asString());
        //Проверяем метод getAll с использованием ID что сущности были созданы
        FullJson jsonResponse = RestAssured.given()
                .baseUri(GET_ALL)
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(FullJson.class);
        //Проверяем сущности в массиве
        assertAll(
                () -> Assertions.assertEquals(jsonForAssertionToString(ADDITIONAL_INFO, ADDITIONAL_NUMBER,
                                IMPORTANT_NUMBERS, TITLE, VERIFIED, entityId),
                        jsonEntityFromResponseToString(jsonResponse.getEntity().get(0)),
                        "Данные в response не совпадают c ожидаемыми данными"),
                () -> Assertions.assertEquals(jsonForAssertionToString(ADDITIONAL_INFO2, ADDITIONAL_NUMBER2,
                                IMPORTANT_NUMBERS2, TITLE2, VERIFIED2, entityId2),
                        jsonEntityFromResponseToString(jsonResponse.getEntity().get(1)),
                        "Данные в response не совпадают c ожидаемыми данными"));
    }

    @Test
    @DisplayName("Тест №04 - Получение сущности")
    @Description(value = "Проверить получение сущности методом GET - get/{id}")
    @Severity(BLOCKER)
    void getSomeTest() throws JsonProcessingException {
        // Создаем сущность, проверяем код ответа, берем id сущности
        int entityId = Integer.parseInt(createSomeWithStaticTestData().statusCode(200).extract().asString());
        //Проверяем методом GET с использованием ID что сущность была создана
        FullJson jsonResponse = RestAssured.given()
                .baseUri(useMethodWithId("GET", entityId))
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(FullJson.class);
        Assertions.assertEquals(jsonForAssertionToString(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS,
                        TITLE, VERIFIED, entityId), jsonFromResponseToString(jsonResponse),
                "Данные в response не совпадают c ожидаемыми данными");
    }

    @Test
    @DisplayName("Тест №05 - Обновление сущности")
    @Description(value = "Обновить созданную сущность методом PATCH - patch/{id}")
    @Severity(CRITICAL)
    void updateSomeTest() throws JsonProcessingException {
        // Создаем сущность, проверяем код ответа, берем id сущности
        int entityId = Integer.parseInt(createSomeWithValues(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS,
                TITLE, VERIFIED)
                .statusCode(200).extract().asString());
        // Обновляем сущность
        patchSomeWithValues(ADDITIONAL_INFO2, ADDITIONAL_NUMBER2, IMPORTANT_NUMBERS2, TITLE2, VERIFIED2, entityId)
                .statusCode(204);
        // Проверяем методом GET с использованием ID из создания сущности, что сущность была обновлена
        FullJson jsonResponse = RestAssured.given()
                .baseUri(useMethodWithId("GET", entityId))
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().body().as(FullJson.class);
        Assertions.assertEquals(jsonForAssertionToString(ADDITIONAL_INFO2, ADDITIONAL_NUMBER2, IMPORTANT_NUMBERS2,
                        TITLE2, VERIFIED2, entityId), jsonFromResponseToString(jsonResponse),
                "Данные в response не совпадают c ожидаемыми данными");
    }

    @AfterAll
    @Step("Обнуление(удаление->создание) тестовой базы")
    public static void tearDown() {
        resetDB(DB_LINK, DB_USER, DB_PASSWORD);
    }
}
