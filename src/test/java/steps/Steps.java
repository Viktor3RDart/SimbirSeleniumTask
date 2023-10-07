package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.SneakyThrows;
import model.PostJson.FullJson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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

    @SneakyThrows
    @Step("Сброс базы данных")
    public static void resetDB() {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",
                "test", "test");
        Statement stm = con.createStatement();
        // Удаление БД
        stm.executeUpdate("DROP TABLE entities CASCADE");
        stm.executeUpdate("DROP TABLE additions CASCADE");
        // Создание БД
        stm.executeUpdate("CREATE TABLE entities(" +
                " id SERIAL PRIMARY KEY," +
                " title VARCHAR(255) NOT NULL," +
                " verified BOOLEAN NOT NULL," +
                " important_numbers integer[]," +
                " addition_id SERIAL UNIQUE)");
        stm.executeUpdate("CREATE TABLE additions(" +
                " id INTEGER PRIMARY KEY NOT NULL," +
                " additional_info VARCHAR(255)," +
                " additional_number integer," +
                " FOREIGN KEY (id) REFERENCES entities (addition_id) ON DELETE CASCADE)");
        con.close();
    }
}


