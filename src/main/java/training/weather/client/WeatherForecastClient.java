package training.weather.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import training.weather.dto.LocationResponse;
import training.weather.dto.SearchResponse;

import java.util.List;

public interface WeatherForecastClient {

    @GET("/api/location/search/")
    Call<List<SearchResponse>> retrieveSearchLocation(@Query("query") String city);

    @GET("/api/location/{woe}")
    Call<LocationResponse> retrieveLocation(@Path("woe") Integer woe);

}
