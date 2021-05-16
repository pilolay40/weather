package training.weather.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import training.weather.exception.WeatherForecastException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WeatherForecastAPITest {

    public static final String CITY = "citynotfound";
    public static final int WOE = -1498786;

    WeatherForecastAPI weatherForecastAPI;

    @BeforeEach
    void setUp() {
        this.weatherForecastAPI = new WeatherForecastAPI(WeatherForecastClientGenerator.createService(WeatherForecastClient.class));
    }

    @Test
    void retrieveSearchLocation_throw_WeatherForecastException() {

        assertThrows(WeatherForecastException.class, () -> this.weatherForecastAPI.retrieveSearchLocation(CITY));
    }

    @Test
    void retrieveLocation_throw_WeatherForecastException() {
        assertThrows(WeatherForecastException.class, () -> this.weatherForecastAPI.retrieveLocation(WOE));
    }
}