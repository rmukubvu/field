package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.application.dto.RegisterRequest;
import za.co.amakosifire.field.domain.auth.RegistrationService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        registrationService.register(registerRequest);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
