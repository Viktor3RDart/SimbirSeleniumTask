package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import steps.Steps;

import static config.Endpoints.*;
import static io.qameta.allure.SeverityLevel.*;
import static model.PostJson.TestDataJson.*;

@DisplayName("Тест-кейсы Api")
public class ApiTests extends Steps {

    @BeforeEach
    @Step("Подготовка тестового окружения, обнуление(удаление->создание) БД")
    public void setUp() {
        resetDB();
    }

    @Test
    @DisplayName("Тест №01 - Создание сущности")
    @Description(value = "Создать сущность методом POST - create")
    @Severity(BLOCKER)
    void createSomeTest() throws JsonProcessingException {
        // Создаем сущность и проверяем
        createSomeWithValues(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS, TITLE, VERIFIED)
                .statusCode(200);
    }

    @Test
    @DisplayName("Тест №02 - Удаление сущности")
    @Description(value = "Удалить сущность, проверить что сущность удалена методом DELETE - delete/{id}")
    @Severity(BLOCKER)
    void deleteSomeTest() throws JsonProcessingException {
        // Создаем сущность, проверяем код ответа
        createSomeWithStaticTestData().statusCode(200);
        //Удаляем сущность
        deleteSome(ID_SOME).statusCode(204);
        //Проверяем методом GET с использованием ID что сущность была удалена
        RestAssured.given()
                .baseUri(useMethodWithId("GET", ID_SOME))
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
        // Создаем сущности, проверяем код ответа
        createSomeWithValues(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS, TITLE, VERIFIED)
                .statusCode(200);
        createSomeWithValues(ADDITIONAL_INFO2, ADDITIONAL_NUMBER2, IMPORTANT_NUMBERS2, TITLE2, VERIFIED2)
                .statusCode(200);
        //Проверяем метод getAll с использованием ID что сущности были созданы
        RestAssured.given()
                .baseUri(GET_ALL)
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //Проверяем первую сущность в массиве
                .body("entity[0].addition.additional_info", Matchers.equalTo(ADDITIONAL_INFO))
                .body("entity[0].addition.additional_number", Matchers.equalTo(ADDITIONAL_NUMBER))
                .body("entity[0].id", Matchers.equalTo(ID_SOME))
                .body("entity[0].important_numbers", Matchers.equalTo(IMPORTANT_NUMBERS))
                .body("entity[0].title", Matchers.equalTo(TITLE))
                .body("entity[0].verified", Matchers.equalTo(VERIFIED))
                .body("entity[1].addition.additional_info", Matchers.equalTo(ADDITIONAL_INFO2))
                .body("entity[1].addition.additional_number", Matchers.equalTo(ADDITIONAL_NUMBER2))
                .body("entity[1].id", Matchers.equalTo(ID_SOME2))
                .body("entity[1].important_numbers", Matchers.equalTo(IMPORTANT_NUMBERS2))
                .body("entity[1].title", Matchers.equalTo(TITLE2))
                .body("entity[1].verified", Matchers.equalTo(VERIFIED2));
    }

    @Test
    @DisplayName("Тест №04 - Получение сущности")
    @Description(value = "Создать сущность, проверить получение сущности методом GET - get/{id}")
    @Severity(BLOCKER)
    void getSomeTest() throws JsonProcessingException {
        // Создаем сущность, проверяем код ответа
        createSomeWithStaticTestData().statusCode(200);
        //Проверяем методом GET с использованием ID что сущность была создана
        RestAssured.given()
                .baseUri(useMethodWithId("GET", ID_SOME))
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("addition.additional_info", Matchers.equalTo(ADDITIONAL_INFO))
                .body("addition.additional_number", Matchers.equalTo(ADDITIONAL_NUMBER))
                .body("id", Matchers.equalTo(ID_SOME))
                .body("important_numbers", Matchers.equalTo(IMPORTANT_NUMBERS))
                .body("title", Matchers.equalTo(TITLE))
                .body("verified", Matchers.equalTo(VERIFIED));
    }

    @Test
    @DisplayName("Тест №05 - Обновление сущности")
    @Description(value = "Обновить созданную сущность методом PATCH - patch/{id}")
    @Severity(CRITICAL)
    void updateSomeTest() throws JsonProcessingException {
        // Создаем сущность, проверяем код ответа
        createSomeWithValues(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS, TITLE, VERIFIED)
                .statusCode(200);
        // Обновляем сущность
        patchSomeWithValues(ADDITIONAL_INFO2, ADDITIONAL_NUMBER2, IMPORTANT_NUMBERS2, TITLE2, VERIFIED2, ID_SOME)
                .statusCode(204);
        // Проверяем методом GET с использованием ID из создания сущности, что сущность была обновлена
        RestAssured.given()
                .baseUri(useMethodWithId("GET", ID_SOME))
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("addition.additional_info", Matchers.equalTo(ADDITIONAL_INFO2))
                .body("addition.additional_number", Matchers.equalTo(ADDITIONAL_NUMBER2))
                .body("id", Matchers.equalTo(ID_SOME))
                .body("important_numbers", Matchers.equalTo(IMPORTANT_NUMBERS2))
                .body("title", Matchers.equalTo(TITLE2))
                .body("verified", Matchers.equalTo(VERIFIED2));
    }

    @AfterEach
    @Step("Обнуление(удаление->создание) тестовой базы")
    public void tearDown() {
        resetDB();
    }
}
