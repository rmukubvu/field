package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import za.co.amakosifire.field.domain.lookups.LookupService;
import za.co.amakosifire.field.domain.lookups.model.City;
import za.co.amakosifire.field.domain.lookups.model.FleetModel;
import za.co.amakosifire.field.domain.lookups.model.Province;
import za.co.amakosifire.field.domain.lookups.model.SearchDistance;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/lookup")
@AllArgsConstructor
public class LookupController {
    private final LookupService lookupService;

    @GetMapping("/city")
    public ResponseEntity<?> cities() {
        return ResponseEntity.ok().body(lookupService.getCities());
    }

    @GetMapping("/province")
    public ResponseEntity<?> provinces() {
        return ResponseEntity.ok().body(lookupService.getProvinces());
    }

    @GetMapping("/fleet-by-make/{make}")
    public ResponseEntity<?> fleetByMake(@PathVariable String make) {
        return ResponseEntity.ok().body(lookupService.getFleetModelByMake(make));
    }

    @PostMapping("/city")
    public ResponseEntity<?> addCity(@Validated @RequestBody City model) throws URISyntaxException {
        var result = lookupService.saveCity(model);
        return ResponseEntity.created(new URI("/api/v1/lookup/city/" + result.getId())).body(result);
    }

    @PostMapping("/province")
    public ResponseEntity<?> addProvince(@Validated @RequestBody Province model) throws URISyntaxException {
        var result = lookupService.saveProvince(model);
        return ResponseEntity.created(new URI("/api/v1/lookup/province/" + result.getId())).body(result);
    }

    @PostMapping("/models")
    public ResponseEntity<?> addFleetModels(@Validated @RequestBody FleetModel model) throws URISyntaxException {
        var result = lookupService.saveFleetModel(model);
        return ResponseEntity.created(new URI("/api/v1/lookup/fleet/model/" + result.getId())).body(result);
    }

    @PostMapping("/distance")
    public ResponseEntity<?> addSearchDistance(@Validated @RequestBody SearchDistance model) throws URISyntaxException {
        var result = lookupService.saveSearchDistance(model);
        return ResponseEntity.created(new URI("/api/v1/lookup/search/distance" + result.getId())).body(result);
    }
}
