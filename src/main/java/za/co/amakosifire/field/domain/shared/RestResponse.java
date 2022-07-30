package za.co.amakosifire.field.domain.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestResponse<T> {
    private int count;
    private boolean error;
    private String message;
    private T data;
}
