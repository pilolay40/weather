package training.weather;

import lombok.AllArgsConstructor;
import training.weather.service.WeatherForecastService;

import java.util.Date;

@AllArgsConstructor
public class WeatherForecast {

    private final WeatherForecastService weatherForecastService;

    public String getCityWeather(final String city, final Date datetime) {
        return this.weatherForecastService.getCityWeather(city, datetime);

    }
}
