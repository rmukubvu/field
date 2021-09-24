package za.co.amakosifire.field.domain.location.model;

import lombok.Builder;
import lombok.Data;

@Data(staticConstructor = "of")
@Builder
public class LastKnownLocation {
    private String id;
    private String deviceId;
    private double latitude;
    private double longitude;
    private String lastKnownTime;
}

//todo - a mapper
/* pull by last know location of user then compute the radius of auctions if any

 */
