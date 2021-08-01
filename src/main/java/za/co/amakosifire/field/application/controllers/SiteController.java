package za.co.amakosifire.field.application.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.domain.site.SiteService;
import za.co.amakosifire.field.domain.site.model.Pump;
import za.co.amakosifire.field.domain.site.model.SiteConfiguration;
import za.co.amakosifire.field.domain.site.model.SiteUser;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/site")
@AllArgsConstructor
public class SiteController {

    private SiteService siteService;

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<?> sitesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(siteService.sitesForUser(userId));
    }

    @GetMapping("/pumps-at-site/{siteId}")
    public ResponseEntity<?> pumpsAtSite(@PathVariable String siteId) {
        return ResponseEntity.ok().body(siteService.pumpsAtSite(siteId));
    }

    @PostMapping("/pump")
    public ResponseEntity<?> addPump(@Validated @RequestBody Pump pump) throws URISyntaxException {
        var result = siteService.savePump(pump);
        return ResponseEntity.created(new URI("/api/v1/site/" + result.getId())).body(result);
    }

    @PostMapping("/site-user")
    public ResponseEntity<?> addSiteUser(@Validated @RequestBody SiteUser siteUser) throws URISyntaxException {
        var result = siteService.saveSiteUser(siteUser);
        return ResponseEntity.created(new URI("/api/v1/site/" + result.getId())).body(result);
    }

    @PostMapping("/site-configuration")
    public ResponseEntity<?> addSiteConfiguration(@Validated @RequestBody SiteConfiguration siteConfiguration) throws URISyntaxException {
        var result = siteService.saveSiteConfiguration(siteConfiguration);
        return ResponseEntity.created(new URI("/api/v1/site/" + result.getId())).body(result);
    }

}
