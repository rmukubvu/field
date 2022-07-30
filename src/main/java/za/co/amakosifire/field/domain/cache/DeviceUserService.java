package za.co.amakosifire.field.domain.cache;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.cache.model.DeviceUser;
import za.co.amakosifire.field.domain.device.model.Device;
import za.co.amakosifire.field.domain.shared.DeviceUserMapper;
import za.co.amakosifire.field.infrastructure.cache.DeviceUserRepository;


@Service
@AllArgsConstructor
public class DeviceUserService {

    private DeviceUserRepository deviceUserRepository;

    public void cacheDeviceUser(Device device) {
        //check exists in cache
        var cachedLocationForDevice = getDeviceUserByDeviceId(device.getId());
        if ( cachedLocationForDevice != null) {
            cachedLocationForDevice.setDeviceId(device.getUserId());
            deviceUserRepository.save(DeviceUserMapper.INSTANCE.fromDomain(cachedLocationForDevice));
            return;
        }
        deviceUserRepository.save
                        (DeviceUserMapper.INSTANCE.fromDomain
                                (DeviceUser.builder()
                                        .id(device.getId())
                                        .deviceId(device.getUserId())
                                        .build()));

    }

    public DeviceUser getDeviceUserByDeviceId(String deviceId) {
        var cachedLocation = deviceUserRepository.findById(deviceId);
        return cachedLocation.isPresent() ? DeviceUserMapper.INSTANCE.toDomain(cachedLocation.get()) : null;
    }
}
