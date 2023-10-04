package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.Step;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResponse {

    @JsonProperty("response")
    private int messageCode;

    @Step("Получить код ответа")
    public int getMessageCode() { return messageCode; }
}
