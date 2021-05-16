package training.weather.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import training.weather.client.WeatherForecastAPI;
import training.weather.dto.ConsolidatedWeather;
import training.weather.dto.LocationResponse;
import training.weather.dto.SearchResponse;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static training.weather.utils.FormatterDateUtils.buildDefaultDateIfNull;
import static training.weather.utils.FormatterDateUtils.convertToLocalDate;

@AllArgsConstructor
public class WeatherForecastService {
    public static final int NEXT_DAYS = 6;
    private final WeatherForecastAPI weatherForecastAPI;

    public String getCityWeather(final String city, final Date datetime) {
        final LocalDateTime localDateTime = convertToLocalDate(buildDefaultDateIfNull(datetime));

        if (this.isDateTimeBeforeNextSixDays(datetime)) {
            return this.findWeatherStateName(city, localDateTime);
        }
        return "";
    }

    private String findWeatherStateName(final String city, final LocalDateTime localDateTime) {
        final List<SearchResponse> searchResponses = this.weatherForecastAPI.retrieveSearchLocation(city);
        if (ObjectUtils.isNotEmpty(searchResponses)) {
            final LocationResponse locationResponse = this.weatherForecastAPI.retrieveLocation(searchResponses.get(0).getWoeid());
            final Optional<ConsolidatedWeather> consolidatedWeatherOptional = locationResponse.getConsolidatedWeather().stream()
                    .filter(consolidatedWeather -> consolidatedWeather.getApplicableDate().isEqual(localDateTime.toLocalDate()))
                    .findFirst();
            if (consolidatedWeatherOptional.isPresent()) {
                return consolidatedWeatherOptional.get().getWeatherStateName();
            }

        }
        return "";
    }

    private boolean isDateTimeBeforeNextSixDays(final Date datetime) {
        final LocalDateTime localDateOrigen = convertToLocalDate(datetime);
        final LocalDateTime dateTimeNextSixDays = LocalDateTime.now().plusDays(NEXT_DAYS);
        return localDateOrigen.isBefore(dateTimeNextSixDays);
    }
}
