package training.weather;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class WeatherForecastTest {

    private static final String CITY = "Madrid";

    @Test
    public void should_return_city_weather() throws IOException {
        // Given
        final WeatherForecast weatherForecast = new WeatherForecast();

        // When
        final String forecast = weatherForecast.getCityWeather(CITY, new Date());

        // Then
        assertNotNull(forecast);

    }
}