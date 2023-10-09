package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.PostJson.FullJson;

import java.util.List;

import static config.Endpoints.*;
import static io.restassured.RestAssured.*;

public class Steps extends FullJson {

    @Step("Создать сущность со статичными данными для общих тестов")
    public static ValidatableResponse createSomeWithStaticTestData() throws JsonProcessingException {
        return given()
                .baseUri(POST)
                .contentType(ContentType.JSON)
                .body(jsonBodyForCommonTests())
                .when().post()
                .then();
    }

    @Step("Создать сущность с данными предаваемыми в метод")
    public static ValidatableResponse createSomeWithValues(String additionalInfo, Integer additionalNumber,
                                                           List<Integer> importantNumbers, String title,
                                                           Boolean verified) throws JsonProcessingException {
        return given()
                .baseUri(POST)
                .contentType(ContentType.JSON)
                .body(jsonBodyWithValue(additionalInfo, additionalNumber, importantNumbers, title, verified))
                .when().post()
                .then();
    }

    @Step("Обновить сущность с данными предаваемыми в метод")
    public static ValidatableResponse patchSomeWithValues(String additionalInfo, Integer additionalNumber,
                                                          List<Integer> importantNumbers, String title,
                                                          Boolean verified, int id) throws JsonProcessingException {
        return given()
                .baseUri(useMethodWithId("PATCH", id))
                .contentType(ContentType.JSON)
                .body(jsonBodyWithValue(additionalInfo, additionalNumber, importantNumbers, title, verified))
                .when().patch()
                .then();
    }

    @Step("Удалить сущность")
    public static ValidatableResponse deleteSome(int id) {
        return given()
                .baseUri(useMethodWithId("DELETE", id))
                .contentType(ContentType.JSON)
                .delete()
                .then();
    }
}


