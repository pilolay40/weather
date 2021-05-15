package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static training.weather.utils.FormatterDateUtils.buildDefaultDateIfNull;
import static training.weather.utils.FormatterDateUtils.convertToLocalDate;

public class WeatherForecast {

    public static final int NEXT_DAYS = 6;

    public String getCityWeather(final String city, Date datetime) throws IOException {
        datetime = buildDefaultDateIfNull(datetime);

        if (this.isDateTimeBeforeNextSixDays(datetime)) {
            HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
            HttpRequest req = rf
                    .buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/search/?query=" + city));
            String r = req.execute().parseAsString();
            final JSONArray array = new JSONArray(r);
            final String woe = array.getJSONObject(0).get("woeid").toString();
            rf = new NetHttpTransport().createRequestFactory();
            req = rf.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/" + woe));
            r = req.execute().parseAsString();
            final JSONArray results = new JSONObject(r).getJSONArray("consolidated_weather");
            for (int i = 0; i < results.length(); i++) {
                if (new SimpleDateFormat("yyyy-MM-dd").format(datetime)
                        .equals(results.getJSONObject(i).get("applicable_date").toString())) {
                    return results.getJSONObject(i).get("weather_state_name").toString();
                }
            }
        }
        return "";
    }

    private boolean isDateTimeBeforeNextSixDays(final Date datetime) {
        final LocalDateTime localDateOrigen = convertToLocalDate(datetime);
        final LocalDateTime dateTimeNextSixDays = LocalDateTime.now().plusDays(NEXT_DAYS);
        return localDateOrigen.isBefore(dateTimeNextSixDays);
    }
}
