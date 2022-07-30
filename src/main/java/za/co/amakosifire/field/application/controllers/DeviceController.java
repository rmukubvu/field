package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.domain.cache.DeviceUserService;
import za.co.amakosifire.field.domain.device.DeviceService;
import za.co.amakosifire.field.domain.device.model.Device;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/device")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceUserService deviceUserService;


    @GetMapping("/{id}")
    public ResponseEntity<?> deviceById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(deviceService.findById(id));
    }

    @GetMapping("/for-user/{id}")
    public ResponseEntity<?> deviceForUserById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(deviceService.findByUserId(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> deviceUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(deviceUserService.getDeviceUserByDeviceId(id));
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody Device device) throws URISyntaxException {
        var result = deviceService.save(device);
        return ResponseEntity.created(new URI("/api/v1/device/" + result.getId())).body(result);
    }


}
