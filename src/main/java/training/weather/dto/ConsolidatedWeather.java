package training.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConsolidatedWeather {
    @JsonProperty("applicable_date")
    private LocalDate applicableDate;
    @JsonProperty("weather_state_name")
    private String weatherStateName;
}
