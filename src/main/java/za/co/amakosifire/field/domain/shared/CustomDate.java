package za.co.amakosifire.field.domain.shared;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDate {

    public static String valueOf(LocalDateTime localDateTime) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
