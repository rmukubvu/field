package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.amakosifire.field.domain.notifications.NotificationService;
import za.co.amakosifire.field.domain.notifications.model.DeviceMessage;
import za.co.amakosifire.field.domain.notifications.model.Fault;

@RestController
@RequestMapping("/api/v1/notification")
@AllArgsConstructor
public class NotificationController {

    private NotificationService notificationService;


    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody Fault fault) {
        notificationService.publishFaultToNearbyTechnicians(fault);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/to-single-device")
    public ResponseEntity<?> postToDevice(@Validated @RequestBody DeviceMessage deviceMessage) {
        notificationService.publishMessage(deviceMessage);
        return ResponseEntity.noContent().build();
    }
}
