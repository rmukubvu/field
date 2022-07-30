package za.co.amakosifire.field.domain.fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.fleet.model.Fleet;
import za.co.amakosifire.field.domain.fleet.model.FleetUser;
import za.co.amakosifire.field.domain.fleet.model.Trip;
import za.co.amakosifire.field.domain.lookups.model.FleetModel;
import za.co.amakosifire.field.domain.shared.FleetMapper;
import za.co.amakosifire.field.domain.shared.FleetModelMapper;
import za.co.amakosifire.field.domain.shared.FleetUserMapper;
import za.co.amakosifire.field.domain.shared.TripMapper;
import za.co.amakosifire.field.infrastructure.fleet.FleetRepository;

import za.co.amakosifire.field.infrastructure.fleet.FleetUserRepository;
import za.co.amakosifire.field.infrastructure.lookups.FleetModelRepository;
import za.co.amakosifire.field.infrastructure.trip.TripRepository;

import java.util.Optional;

@Service
public class FleetService {
    private FleetRepository fleetRepository;
    private FleetModelRepository fleetModelRepository;
    private FleetUserRepository fleetUserRepository;
    private TripRepository tripRepository;

    @Autowired
    public FleetService(final FleetRepository fleetRepository,
                        final FleetModelRepository fleetModelRepository,
                        final FleetUserRepository fleetUserRepository,
                        final TripRepository tripRepository) {
        this.fleetRepository = fleetRepository;
        this.fleetModelRepository = fleetModelRepository;
        this.fleetUserRepository = fleetUserRepository;
        this.tripRepository = tripRepository;
    }

    public Fleet saveFleet(Fleet fleet) {
       return FleetMapper.INSTANCE.toDomain(
               fleetRepository.save(FleetMapper.INSTANCE.fromDomain(fleet.onSave())));
    }

    public Trip saveTrip(Trip trip) {
        return TripMapper.INSTANCE.toDomain(
                tripRepository.save(TripMapper.INSTANCE.fromDomain(trip.onSave())));
    }

    public FleetUser saveFleetUser(FleetUser fleetUser) {
        return FleetUserMapper.INSTANCE.toDomain(
                fleetUserRepository.save(FleetUserMapper.INSTANCE.fromDomain(fleetUser)));
    }

    public Fleet fleetByUserId(final String userId) {
        var fleetId = getFleetIdForUser(userId);
        if (fleetId == null) return null;
        return FleetMapper.INSTANCE.toDomain(
                fleetRepository.findById(fleetId).get());
    }

    public FleetModel fleetModelById(String id) {
        return FleetModelMapper.INSTANCE.toDomain(
                fleetModelRepository.findById(id).get()
        );
    }

    private String getFleetIdForUser(String userId) {
        var result = FleetUserMapper.INSTANCE.toDomain(fleetUserRepository.findByFleetIdEquals(userId));
        if (Optional.ofNullable(result).isPresent()) {
            return result.getFleetId();
        }
        return null;
    }
}
