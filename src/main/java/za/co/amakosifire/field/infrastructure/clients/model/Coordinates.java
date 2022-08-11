package za.co.amakosifire.field.infrastructure.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private double longitude;
    private double latitude;
}
