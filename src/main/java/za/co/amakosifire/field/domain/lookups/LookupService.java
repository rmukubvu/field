package za.co.amakosifire.field.domain.lookups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.lookups.model.City;
import za.co.amakosifire.field.domain.lookups.model.FleetModel;
import za.co.amakosifire.field.domain.lookups.model.Province;
import za.co.amakosifire.field.domain.lookups.model.SearchDistance;
import za.co.amakosifire.field.domain.shared.CityMapper;
import za.co.amakosifire.field.domain.shared.FleetModelMapper;
import za.co.amakosifire.field.domain.shared.ProvinceMapper;
import za.co.amakosifire.field.domain.shared.SearchDistanceMapper;
import za.co.amakosifire.field.infrastructure.lookups.CityRepository;
import za.co.amakosifire.field.infrastructure.lookups.FleetModelRepository;
import za.co.amakosifire.field.infrastructure.lookups.ProvinceRepository;
import za.co.amakosifire.field.infrastructure.lookups.SearchDistanceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LookupService {
    private CityRepository cityRepository;
    private ProvinceRepository provinceRepository;
    private FleetModelRepository fleetModelRepository;
    private SearchDistanceRepository searchDistanceRepository;

    @Autowired
    public LookupService(final CityRepository cityRepository,
                         final ProvinceRepository provinceRepository,
                         final FleetModelRepository fleetModelRepository,
                         final SearchDistanceRepository searchDistanceRepository) {
        this.cityRepository = cityRepository;
        this.provinceRepository = provinceRepository;
        this.fleetModelRepository = fleetModelRepository;
        this.searchDistanceRepository = searchDistanceRepository;
    }

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

    public SearchDistance getDistance() {
        return SearchDistanceMapper.INSTANCE
                .toDomain(searchDistanceRepository.findAll().get(0));
    }

}
