package za.co.amakosifire.field.domain.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.device.DeviceService;
import za.co.amakosifire.field.domain.service.model.Maintenance;
import za.co.amakosifire.field.domain.service.model.ServiceAuction;
import za.co.amakosifire.field.domain.service.model.ServiceHistory;
import za.co.amakosifire.field.domain.service.model.ServiceNotification;
import za.co.amakosifire.field.domain.shared.MaintenanceMapper;
import za.co.amakosifire.field.domain.shared.ServiceAuctionMapper;
import za.co.amakosifire.field.domain.shared.ServiceHistoryMapper;
import za.co.amakosifire.field.domain.shared.ServiceNotificationMapper;
import za.co.amakosifire.field.infrastructure.service.ServiceAuctionRepository;
import za.co.amakosifire.field.infrastructure.service.ServiceHistoryRepository;
import za.co.amakosifire.field.infrastructure.service.ServiceNotificationRepository;
import za.co.amakosifire.field.infrastructure.service.MaintenanceRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaintenanceService {

    private MaintenanceRepository maintenanceRepository;
    private ServiceHistoryRepository serviceHistoryRepository;
    private ServiceAuctionRepository serviceAuctionRepository;
    private ServiceNotificationRepository serviceNotificationRepository;
    private DeviceService deviceService;

    public Maintenance save(Maintenance maintenance) {
        return MaintenanceMapper.INSTANCE.toDomain(maintenanceRepository.save(MaintenanceMapper.INSTANCE.fromDomain(maintenance)));
    }

    public ServiceAuction save(ServiceAuction serviceAuction) {
        return ServiceAuctionMapper.INSTANCE.toDomain(serviceAuctionRepository.save(ServiceAuctionMapper.INSTANCE.fromDomain(serviceAuction)));
    }

    public ServiceHistory save(ServiceHistory serviceHistory) {
        return ServiceHistoryMapper.INSTANCE.toDomain(serviceHistoryRepository.save(ServiceHistoryMapper.INSTANCE.fromDomain(serviceHistory)));
    }

    public ServiceNotification save(ServiceNotification serviceNotification) {
        return ServiceNotificationMapper.INSTANCE.toDomain(serviceNotificationRepository.save(ServiceNotificationMapper.INSTANCE.fromDomain(serviceNotification)));
    }

    public Collection<ServiceAuction> currentAuction(String userId) {
        var openItems = serviceAuctionRepository.findAllByAllocatedEquals(false);
        //get user location details then search if there is an auction within range
        var deviceId = deviceService.findByUserId(userId);
        if (Optional.ofNullable(deviceId).isPresent()) {

        }
        return null;
    }


}
