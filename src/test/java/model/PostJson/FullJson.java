package model.PostJson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;

import java.util.List;

import static model.PostJson.TestDataJson.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "addition",
        "important_numbers",
        "title",
        "verified"
})

public class FullJson {

    @JsonProperty("addition")
    private Addition addition;
    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;
    @JsonProperty("title")
    private String title;
    @JsonProperty("verified")
    private Boolean verified;


    @JsonProperty("addition")
    public Addition getAddition() {
        return addition;
    }

    @JsonProperty("addition")
    public void setAddition(Addition addition) {
        this.addition = addition;
    }

    @JsonProperty("important_numbers")
    public List<Integer> getImportantNumbers() {
        return importantNumbers;
    }

    @JsonProperty("important_numbers")
    public void setImportantNumbers(List<Integer> importantNumbers) {
        this.importantNumbers = importantNumbers;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public FullJson() {
    }

    public FullJson(String additionalInfo, Integer additionalNumber, List<Integer> importantNumbers, String title,
                    Boolean verified) {
        this.addition = new Addition(additionalInfo, additionalNumber);
        this.importantNumbers = importantNumbers;
        this.title = title;
        this.verified = verified;
    }

    @Step("Создать статическое Body-Json для метода POST")
    public static String jsonBodyForCommonTests() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FullJson data = new FullJson(ADDITIONAL_INFO, ADDITIONAL_NUMBER, IMPORTANT_NUMBERS, TITLE, VERIFIED);
        return objectMapper.writeValueAsString(data);
    }

    @Step("Создать Body-Json с данными предаваемыми в метод, для метода POST")
    public static String jsonBodyWithValue(String additionalInfo, Integer additionalNumber,
                                           List<Integer> importantNumbers, String title, Boolean verified)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FullJson data = new FullJson(additionalInfo, additionalNumber, importantNumbers, title, verified);
        return objectMapper.writeValueAsString(data);
    }
}


