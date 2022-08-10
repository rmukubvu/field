package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.application.dto.*;

import za.co.amakosifire.field.domain.auth.UserService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/reset-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        var result = userService.resetPassword(request.getUserName());
        return new ResponseEntity<>(result, OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<GenericResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        var response  = userService.changePassword(request);
        return new ResponseEntity<>(response, OK);
    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse> updateUser(@RequestBody UserRequest request) {
        var response = userService.updateUser(request);
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> users() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/re-cache")
    public ResponseEntity<?> reloadUsersInCache() {
        return ResponseEntity.ok().body(userService.reloadInCache());
    }

}
