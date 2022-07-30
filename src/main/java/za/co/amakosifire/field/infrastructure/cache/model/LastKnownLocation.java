package za.co.amakosifire.field.infrastructure.cache.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("LastKnownLocation")
public class LastKnownLocation {
    private String id;
    private double latitude;
    private double longitude;
    private String lastKnownTime;
}

//todo - a mapper
/* pull by last know location of user then compute the radius of auctions if any

 */
