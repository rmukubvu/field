package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.domain.device.model.Device;
import za.co.amakosifire.field.domain.fleet.FleetService;
import za.co.amakosifire.field.domain.fleet.model.Fleet;
import za.co.amakosifire.field.domain.fleet.model.FleetUser;
import za.co.amakosifire.field.domain.fleet.model.Trip;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/fleet")
@AllArgsConstructor
public class FleetController {

    private FleetService fleetService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> byUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(fleetService.fleetByUserId(userId));
    }


    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody Fleet fleet) throws URISyntaxException {
        var result = fleetService.saveFleet(fleet);
        return ResponseEntity.created(new URI("/api/v1/fleet/" + result.getId())).body(result);
    }

    @PostMapping("/trip")
    public ResponseEntity<?> postTrip(@Validated @RequestBody Trip trip) throws URISyntaxException {
        var result = fleetService.saveTrip(trip);
        return ResponseEntity.created(new URI("/api/v1/fleet/" + result.getId())).body(result);
    }

    @PostMapping("/user")
    public ResponseEntity<?> postFleetUser(@Validated @RequestBody FleetUser fleetUser) throws URISyntaxException {
        var result = fleetService.saveFleetUser(fleetUser);
        return ResponseEntity.created(new URI("/api/v1/fleet/" + result.getId())).body(result);
    }
}
