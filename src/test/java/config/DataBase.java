package config;

import io.qameta.allure.Step;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public static final String DB_LINK = "jdbc:postgresql://localhost:5432/test";
    public static final String DB_USER = "test";
    public static final String DB_PASSWORD = "test";

    @SneakyThrows
    @Step("Сброс базы данных")
    public static void resetDB(String DBLink, String user, String password) {
        try (Connection con = DriverManager.getConnection(DBLink,
                user, password)) {
            Statement stm = con.createStatement();
            // Удаление БД
            stm.executeUpdate("DROP TABLE IF EXISTS entities CASCADE");
            stm.executeUpdate("DROP TABLE IF EXISTS additions CASCADE");
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
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
