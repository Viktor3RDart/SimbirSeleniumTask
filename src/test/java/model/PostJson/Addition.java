package model.PostJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "additional_info",
        "additional_number"
})

public class Addition {

    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("additional_number")
    private Integer additionalNumber;
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("additional_info")
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @JsonProperty("additional_info")
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @JsonProperty("additional_number")
    public Integer getAdditionalNumber() {
        return additionalNumber;
    }

    @JsonProperty("additional_number")
    public void setAdditionalNumber(Integer additionalNumber) {
        this.additionalNumber = additionalNumber;
    }

    public Addition() {
    }

    public Addition(String additionalInfo, Integer additionalNumber, Integer id) {
        this.id = id;
        this.additionalInfo = additionalInfo;
        this.additionalNumber = additionalNumber;
    }

    public Addition(String additionalInfo, Integer additionalNumber) {
        this.additionalInfo = additionalInfo;
        this.additionalNumber = additionalNumber;
    }
}