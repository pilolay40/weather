package training.weather;

import org.junit.Test;
import training.weather.service.WeatherForecastService;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class WeatherForecastTest {

    private static final String CITY = "Madrid";

    @Test
    public void should_return_city_weather() throws IOException {
        // Given
        final WeatherForecastService weatherForecastService = new WeatherForecastService();
        final WeatherForecast weatherForecast = new WeatherForecast(weatherForecastService);

        // When
        final String forecast = weatherForecast.getCityWeather(CITY, new Date());

        // Then
        assertNotNull(forecast);

    }
}