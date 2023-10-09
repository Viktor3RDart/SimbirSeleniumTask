package model.PostJson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "addition",
        "important_numbers",
        "title",
        "verified",
        "id",
})

public class Entity {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("addition")
    private Addition addition;
    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;


    public Entity() {
    }

    public Entity(Integer id, String title, Boolean verified, Addition addition, List<Integer> importantNumbers) {
        super();
        this.id = id;
        this.title = title;
        this.verified = verified;
        this.addition = addition;
        this.importantNumbers = importantNumbers;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
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
}
