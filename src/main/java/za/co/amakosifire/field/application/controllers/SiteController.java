package za.co.amakosifire.field.application.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import za.co.amakosifire.field.application.dto.Geometry;
import za.co.amakosifire.field.application.dto.GooglePlaces;
import za.co.amakosifire.field.domain.site.SiteService;
import za.co.amakosifire.field.domain.site.model.Pump;
import za.co.amakosifire.field.domain.site.model.SiteConfiguration;
import za.co.amakosifire.field.domain.site.model.SiteUser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/site")
@AllArgsConstructor
public class SiteController {

    private SiteService siteService;

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<?> sitesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(siteService.sitesForUser(userId));
    }

    @GetMapping("/by-place-id/{placeId}")
    public ResponseEntity<?> locationByPlaceId(@PathVariable String placeId) {
        var result = getLocationFor(placeId);
        return ResponseEntity.ok().body(result.isPresent() ? result.get() : new Geometry());
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

    private Optional<Geometry> getLocationFor(String placeId) {
        WebClient client = WebClient.create();
        String url = String.format("https://maps.googleapis.com/maps/api/place/details/json?place_id=%s&key=AIzaSyDU7AbtPwtbDzejE3WloI3oFmPoCfCOE10",placeId);
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(url)
                .retrieve();
        GooglePlaces googlePlaces = responseSpec.bodyToMono(GooglePlaces.class).block();
        return Optional.ofNullable(googlePlaces).stream().map( e-> e.result.geometry).findFirst();
    }

}
