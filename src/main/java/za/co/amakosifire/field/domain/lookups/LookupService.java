package za.co.amakosifire.field.domain.lookups;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.lookups.model.*;
import za.co.amakosifire.field.domain.shared.*;
import za.co.amakosifire.field.infrastructure.lookups.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LookupService {
    private CityRepository cityRepository;
    private ProvinceRepository provinceRepository;
    private FleetModelRepository fleetModelRepository;
    private SearchDistanceRepository searchDistanceRepository;
    private PumpTypeRepository pumpTypeRepository;


    public City saveCity(City city) {
        return CityMapper.INSTANCE.toDomain(
                cityRepository.save(CityMapper.INSTANCE.fromDomain(city))
        );
    }

    public Province saveProvince(Province province) {
        return ProvinceMapper.INSTANCE.toDomain(
                provinceRepository.save(ProvinceMapper.INSTANCE.fromDomain(province))
        );
    }

    public FleetModel saveFleetModel(FleetModel fleetModel) {
        return FleetModelMapper.INSTANCE.toDomain(
                fleetModelRepository.save(FleetModelMapper.INSTANCE.fromDomain(fleetModel))
        );
    }

    public SearchDistance saveSearchDistance(SearchDistance searchDistance) {
        return SearchDistanceMapper.INSTANCE.toDomain(
                searchDistanceRepository.save(SearchDistanceMapper.INSTANCE.fromDomain(searchDistance))
        );
    }

    public PumpType savePumpType(PumpType pumpType) {
        return PumpTypeMapper.INSTANCE.toDomain(
                pumpTypeRepository.save(PumpTypeMapper.INSTANCE.fromDomain(pumpType))
        );
    }

    public List<City> getCities() {
        return cityRepository.findAll().stream().map(CityMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public List<Province> getProvinces() {
        return provinceRepository.findAll().stream().map(ProvinceMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public List<FleetModel> getFleetModelByMake(String make) {
        return fleetModelRepository.findAllByMakeEquals(make).stream().map(
                FleetModelMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public List<PumpType> getPumpTypes() {
        return pumpTypeRepository.findAll().stream().map(PumpTypeMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public SearchDistance getDistance() {
        return SearchDistanceMapper.INSTANCE
                .toDomain(searchDistanceRepository.findAll().get(0));
    }

}
