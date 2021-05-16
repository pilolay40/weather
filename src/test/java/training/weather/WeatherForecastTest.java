package training.weather;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.weather.client.WeatherForecastAPI;
import training.weather.client.WeatherForecastClient;
import training.weather.client.WeatherForecastClientGenerator;
import training.weather.exception.WeatherForecastException;
import training.weather.service.WeatherForecastService;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WeatherForecastTest {

    public static final String CITY_NOT_FOUND = "NODFOUND";
    private static final String CITY = "Madrid";
    private final WeatherForecastClient weatherForecastClient = WeatherForecastClientGenerator.createService(WeatherForecastClient.class);
    private WeatherForecast weatherForecast;

    @BeforeEach
    public void setUp() {
        final WeatherForecastAPI weatherForecastApi = new WeatherForecastAPI(this.weatherForecastClient);
        final WeatherForecastService weatherForecastService = new WeatherForecastService(weatherForecastApi);
        this.weatherForecast = new WeatherForecast(weatherForecastService);
    }

    @Test
    public void should_return_city_weather() {
        // Given

        // When
        final String forecast = this.weatherForecast.getCityWeather(CITY, new Date());

        // Then
        assertNotNull(forecast);

    }

    @Test
    public void should_empty_city_weather() {
        assertThrows(WeatherForecastException.class, () -> this.weatherForecast.getCityWeather(CITY_NOT_FOUND, this.buildDate(0)));

    }

    @Test
    public void should_empty_city_weather_is_datetime_has_more_than_six_days() {

        // When
        final String forecast = this.weatherForecast.getCityWeather(CITY_NOT_FOUND, this.buildDate(7));

        // Then
        assertTrue(ObjectUtils.isEmpty(forecast));

    }

    private Date buildDate(final int amountOfDays) {
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, amountOfDays);
        return c.getTime();
    }
}