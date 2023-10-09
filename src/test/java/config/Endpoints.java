package config;

import org.openqa.selenium.InvalidArgumentException;

public class Endpoints {

    public static final String BASIC_URL = "http://localhost:8080";

    public static final String POST = BASIC_URL + "/api/create";

    public static final String GET_ALL = BASIC_URL + "/api/getAll";

    public static String useMethodWithId(String method, int id) {
        String url;
        switch (method) {
            case "GET" -> url = BASIC_URL + "/api/get/" + id;
            case "PATCH" -> url = BASIC_URL + "/api/patch/" + id;
            case "DELETE" -> url = BASIC_URL + "/api/delete/" + id;
            default -> throw new InvalidArgumentException("Вы ввели не допустимое наименование метода: "
                    + method + ", допустимые значения : GET, PATCH, DELETE");
        }
        return url;
    }
}
