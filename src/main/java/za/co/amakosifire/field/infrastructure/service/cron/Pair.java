package za.co.amakosifire.field.infrastructure.service.cron;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Data
public class Pair {
    private int year;
    private int month;
    private LocalDateTime nextMonth;
}
