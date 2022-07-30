package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.application.dto.ChangePasswordRequest;
import za.co.amakosifire.field.application.dto.ForgotPasswordRequest;

import za.co.amakosifire.field.application.dto.ForgotPasswordResponse;
import za.co.amakosifire.field.application.dto.UserRequest;
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
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return new ResponseEntity<>("User Password Changed", OK);
    }

    @PostMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody UserRequest request) {
        userService.updateUser(request);
        return new ResponseEntity<>("User Updated Successfully", OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> users() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}
