package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.domain.callout.CalloutService;
import za.co.amakosifire.field.domain.callout.model.AcceptedCallout;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/callout")
@AllArgsConstructor
public class CalloutController {
    private CalloutService calloutService;

    @GetMapping("/{reference}")
    public ResponseEntity<?> byName(@PathVariable String reference) {
        return ResponseEntity.ok().body(calloutService.calloutBy(reference));
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody AcceptedCallout model) throws URISyntaxException {
        var result = calloutService.createCallout(model);
        return ResponseEntity.created(new URI("/api/v1/callout/" + result.getReference())).body(result);
    }

}
