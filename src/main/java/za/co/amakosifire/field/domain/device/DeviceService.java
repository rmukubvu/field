package za.co.amakosifire.field.domain.device;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.cache.model.DeviceUser;
import za.co.amakosifire.field.domain.device.model.Device;
import za.co.amakosifire.field.domain.shared.DeviceMapper;
import za.co.amakosifire.field.domain.shared.DeviceUserMapper;
import za.co.amakosifire.field.infrastructure.cache.DeviceUserRepository;
import za.co.amakosifire.field.infrastructure.device.DeviceRepository;

@Service
@AllArgsConstructor
public class DeviceService {

    private DeviceRepository deviceRepository;
    private DeviceUserRepository deviceUserRepository;

    public Device save(final Device device) {
        cacheDeviceUser(device);
        return DeviceMapper.INSTANCE.toDomain(deviceRepository.save(DeviceMapper.INSTANCE.fromDomain(device.onSave())));
    }

    public Device findById(final String id) {
        return DeviceMapper.INSTANCE.toDomain(deviceRepository.findById(id).get());
    }

    public Device findByUserId(final String userId) {
        return DeviceMapper.INSTANCE.toDomain(deviceRepository.findByUserIdEquals(userId));
    }

    private void cacheDeviceUser(Device device) {
        //check exists in cache
        var deviceUser = getDeviceUserBy(device.getId());
        if ( deviceUser != null) {
            deviceUser.setDeviceId(device.getUserId());
            deviceUserRepository.save(DeviceUserMapper.INSTANCE.fromDomain(deviceUser));
            return;
        }
        deviceUserRepository.save
                (DeviceUserMapper.INSTANCE.fromDomain
                        (DeviceUser.builder()
                                .id(device.getId())
                                .deviceId(device.getUserId())
                                .build()));

    }

    private DeviceUser getDeviceUserBy(String deviceId) {
        var cachedLocation = deviceUserRepository.findById(deviceId);
        return cachedLocation.isPresent() ? DeviceUserMapper.INSTANCE.toDomain(cachedLocation.get()) : null;
    }

}
