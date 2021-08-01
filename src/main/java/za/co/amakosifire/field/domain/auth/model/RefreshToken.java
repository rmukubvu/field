package za.co.amakosifire.field.domain.auth.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class RefreshToken {
    private String id;
    private String token;
    private String username;
    private Date creationDate;
}
