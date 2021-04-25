package za.co.amakosifire.field.domain.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.cache.DeviceUserService;
import za.co.amakosifire.field.domain.device.model.Device;
import za.co.amakosifire.field.domain.shared.DeviceMapper;
import za.co.amakosifire.field.infrastructure.device.DeviceRepository;

import java.time.LocalDateTime;

@Service
public class DeviceService {
    private DeviceRepository deviceRepository;
    private DeviceUserService deviceUserService;

    @Autowired
    public DeviceService(final DeviceRepository deviceRepository, final DeviceUserService deviceUserService) {
        this.deviceRepository = deviceRepository;
        this.deviceUserService = deviceUserService;
    }

    public Device save(final Device device) {
        deviceUserService.cacheDeviceUser(device);
        return DeviceMapper.INSTANCE.toDomain(deviceRepository.save(DeviceMapper.INSTANCE.fromDomain(device.onSave())));
    }

    public Device findById(final String id) {
        return DeviceMapper.INSTANCE.toDomain(deviceRepository.findById(id).get());
    }

    public Device findByUserId(final String userId) {
        return DeviceMapper.INSTANCE.toDomain(deviceRepository.findByUserIdEquals(userId));
    }

}
