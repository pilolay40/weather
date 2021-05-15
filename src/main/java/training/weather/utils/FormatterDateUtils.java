package training.weather.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class FormatterDateUtils {

    public static Date buildDefaultDateIfNull(final Date datetime) {
        if (ObjectUtils.isEmpty(datetime)) {
            return new Date();
        }
        return datetime;
    }

    public static LocalDateTime convertToLocalDate(final Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
