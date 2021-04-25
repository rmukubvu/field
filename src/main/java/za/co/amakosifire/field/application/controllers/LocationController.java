package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.amakosifire.field.domain.location.LocationService;
import za.co.amakosifire.field.domain.location.model.Location;

@RestController
@RequestMapping("/api/v1/location")
@AllArgsConstructor
public class LocationController {
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody Location location) {
        locationService.saveLocation(location);
        return ResponseEntity.noContent().build();
    }

}
