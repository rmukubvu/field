package za.co.amakosifire.field.infrastructure.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.service.model.Maintenance;

import java.util.Collection;

public interface MaintenanceRepository extends MongoRepository<Maintenance,String> {
    Collection<Maintenance> findAllByNextServiceYearEqualsAndAndNextServiceMonthEquals(Integer year, Integer month);
}
