package training.weather.exception;

public class WeatherForecastException extends RuntimeException {
    public WeatherForecastException(final Throwable cause) {
        super(cause);
    }

    public WeatherForecastException(final String message) {
        super(message);
    }
}
