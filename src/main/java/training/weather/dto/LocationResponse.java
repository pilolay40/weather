package training.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {
    @JsonProperty("consolidated_weather")
    private List<ConsolidatedWeather> consolidatedWeather;

}