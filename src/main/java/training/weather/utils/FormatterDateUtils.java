package training.weather.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

@UtilityClass
public class FormatterDateUtils {

    public static Date buildDefaultDateIfNull(final Date datetime) {
        if (ObjectUtils.isEmpty(datetime)) {
            return new Date();
        }
        return datetime;
    }
}
