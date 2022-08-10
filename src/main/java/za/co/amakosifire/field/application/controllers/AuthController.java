package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.application.dto.AuthenticationResponse;
import za.co.amakosifire.field.application.dto.GenericResponse;
import za.co.amakosifire.field.application.dto.LoginRequest;
import za.co.amakosifire.field.application.dto.RefreshTokenRequest;

import za.co.amakosifire.field.domain.auth.AuthService;
import za.co.amakosifire.field.domain.auth.RefreshTokenService;
import za.co.amakosifire.field.domain.auth.model.Role;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/roles")
    public ResponseEntity<?> roles() {
        return ResponseEntity.ok().body(authService.getAllRoles());
    }


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/role")
    public ResponseEntity<?> addRole(@Validated @RequestBody Role role) throws URISyntaxException {
        var result = authService.saveRole(role);
        return ResponseEntity.created(new URI("/api/v1/auth/role/" + result.getId())).body(result);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<GenericResponse> logout(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        var response = refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body(response);
    }

}
