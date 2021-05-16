package training.weather.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import training.weather.dto.LocationResponse;
import training.weather.dto.SearchResponse;
import training.weather.exception.WeatherForecastException;

import java.io.IOException;
import java.util.List;

public class WeatherForecastAPI {

    private static final String BASE_URL = "https://www.metaweather.com/";
    private final WeatherForecastClient weatherForecastClient;

    public WeatherForecastAPI() {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(this.buildObjectMapper())).build();
        this.weatherForecastClient = retrofit.create(WeatherForecastClient.class);
    }


    public List<SearchResponse> retrieveSearchLocation(final String city) {
        try {
            return this.weatherForecastClient.retrieveSearchLocation(city)
                    .execute()
                    .body();
        } catch (final IOException e) {
            throw new WeatherForecastException(e);
        }
    }

    public LocationResponse retrieveLocation(final Integer woe) {
        try {
            return this.weatherForecastClient.retrieveLocation(woe)
                    .execute()
                    .body();
        } catch (final IOException e) {
            throw new WeatherForecastException(e);
        }
    }

    private ObjectMapper buildObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}
