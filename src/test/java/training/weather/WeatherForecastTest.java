package training.weather;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Before;
import org.junit.Test;
import training.weather.client.WeatherForecastAPI;
import training.weather.service.WeatherForecastService;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WeatherForecastTest {

    private static final String CITY = "Madrid";
    private WeatherForecast weatherForecast;

    @Before
    public void setUp() {
        final WeatherForecastAPI weatherForecastApi = new WeatherForecastAPI();
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
        // Given

        // When
        final String forecast = this.weatherForecast.getCityWeather("NODFOUND", this.buildDate(0));

        // Then
        assertTrue(ObjectUtils.isEmpty(forecast));

    }

    @Test
    public void should_empty_city_weather_is_datetime_has_more_than_six_days() {

        // When
        final String forecast = this.weatherForecast.getCityWeather("NODFOUND", this.buildDate(7));

        // Then
        assertTrue(ObjectUtils.isEmpty(forecast));

    }

    private Date buildDate(final int amountOfDays) {
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, amountOfDays);
        return c.getTime();
    }
}