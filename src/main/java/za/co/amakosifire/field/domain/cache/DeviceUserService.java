package za.co.amakosifire.field.domain.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.cache.model.DeviceUser;
import za.co.amakosifire.field.domain.device.model.Device;
import za.co.amakosifire.field.domain.shared.DeviceUserMapper;
import za.co.amakosifire.field.infrastructure.cache.DeviceUserRepository;


@Service
public class DeviceUserService {
    private DeviceUserRepository deviceUserRepository;

    @Autowired
    public DeviceUserService(final DeviceUserRepository deviceUserRepository) {
        this.deviceUserRepository = deviceUserRepository;
    }

    public void cacheDeviceUser(Device device) {
        //check exists in cache
        var cachedLocationForDevice = getDeviceUserByDeviceId(device.getId());
        if ( cachedLocationForDevice != null) {
            cachedLocationForDevice.setUserId(device.getUserId());
            deviceUserRepository.save(DeviceUserMapper.INSTANCE.fromDomain(cachedLocationForDevice));
            return;
        }
        deviceUserRepository.save
                        (DeviceUserMapper.INSTANCE.fromDomain
                                (DeviceUser.builder()
                                        .id(device.getId())
                                        .userId(device.getUserId())
                                        .build()));

    }

    public DeviceUser getDeviceUserByDeviceId(String deviceId) {
        var cachedLocation = deviceUserRepository.findById(deviceId);
        return cachedLocation.isPresent() ? DeviceUserMapper.INSTANCE.toDomain(cachedLocation.get()) : null;
    }
}
