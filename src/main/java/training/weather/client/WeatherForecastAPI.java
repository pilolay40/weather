package training.weather.client;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import training.weather.dto.LocationResponse;
import training.weather.dto.SearchResponse;
import training.weather.exception.WeatherForecastException;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class WeatherForecastAPI {


    private final WeatherForecastClient weatherForecastClient;

    public List<SearchResponse> retrieveSearchLocation(final String city) {
        try {
            final List<SearchResponse> searchResponses = this.weatherForecastClient.retrieveSearchLocation(city)
                    .execute()
                    .body();
            if (ObjectUtils.isEmpty(searchResponses)) {
                throw new WeatherForecastException("Not data found");
            }
            return searchResponses;
        } catch (final IOException e) {
            throw new WeatherForecastException(e);
        }
    }

    public LocationResponse retrieveLocation(final Integer woe) {
        try {
            final LocationResponse locationResponse = this.weatherForecastClient.retrieveLocation(woe)
                    .execute()
                    .body();
            if (ObjectUtils.isEmpty(locationResponse)) {
                throw new WeatherForecastException("not data found");
            }
            return locationResponse;
        } catch (final IOException e) {
            throw new WeatherForecastException(e);
        }
    }


}
