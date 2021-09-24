package za.co.amakosifire.field.domain.location;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.location.model.LastKnownLocation;
import za.co.amakosifire.field.domain.location.model.Location;
import za.co.amakosifire.field.domain.shared.CustomDate;
import za.co.amakosifire.field.domain.shared.LastKnownLocationMapper;
import za.co.amakosifire.field.domain.shared.LocationMapper;
import za.co.amakosifire.field.infrastructure.cache.LastKnownLocationRepository;
import za.co.amakosifire.field.infrastructure.location.LocationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationService {

    @Value("${search.radius}")
    private Integer MAXIMUM_DISTANCE;

    private LocationRepository locationRepository;
    private LastKnownLocationRepository lastKnownLocationRepository;


    public void saveLocation(Location location){
       locationRepository.save(LocationMapper.INSTANCE.fromDomain(location.onSave()));
       cacheLastKnownLocation(location);
    }

    public List<Location> getDevicesNearFault(double longitude, double latitude) {
        var locationPoint = new Point(longitude, latitude);
        var distance = new Distance(MAXIMUM_DISTANCE, Metrics.KILOMETERS);
        var locations = locationRepository.findByLocationNear(locationPoint, distance);
        return CollectionUtils.emptyIfNull(locations).stream().map(LocationMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }


    private void cacheLastKnownLocation(Location location) {
        var coordinates = location.getPoint();
        var lastLocation = LastKnownLocation.builder()
                .lastKnownTime(CustomDate.valueOf(LocalDateTime.now()))
                .latitude(coordinates.getLatitude())
                .longitude(coordinates.getLongitude())
                .deviceId(location.getDeviceId())
                .build();
        lastKnownLocationRepository.save(LastKnownLocationMapper.INSTANCE.fromDomain(lastLocation));
    }

}
