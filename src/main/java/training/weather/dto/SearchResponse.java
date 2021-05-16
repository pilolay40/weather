package training.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchResponse {
    private String title;
    @JsonProperty("location_type")
    private String locationType;
    private Integer woeid;
    @JsonProperty("latt_long")
    private String lattLong;
}