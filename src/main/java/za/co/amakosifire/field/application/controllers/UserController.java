package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.amakosifire.field.domain.user.UserService;
import za.co.amakosifire.field.domain.user.model.Photo;
import za.co.amakosifire.field.domain.user.model.Role;
import za.co.amakosifire.field.domain.user.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/{name}")
//    public ResponseEntity<?> byName(@PathVariable String name) throws Exception {
//        return ResponseEntity.ok().body(userService.findUserByName(name));
//    }

    @GetMapping("/roles")
    public ResponseEntity<?> roles() {
        return ResponseEntity.ok().body(userService.getAllRoles());
    }

    @GetMapping("/users")
    public ResponseEntity<?> users() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

//    @GetMapping("/validate/{username}/{password}")
//    public ResponseEntity<?> validateCredentials(@PathVariable String username,@PathVariable String password) throws Exception {
//        return ResponseEntity.ok().body(userService.authenticate(username, password));
//    }

    @GetMapping("/photos/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = userService.getPhotoByUserId(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "photos";
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody User user) throws URISyntaxException {
        var result = userService.save(user);
        return ResponseEntity.created(new URI("/api/v1/user/" + result.getUserName())).body(result);
    }

    @PostMapping("/role")
    public ResponseEntity<?> addRole(@Validated @RequestBody Role role) throws URISyntaxException {
        var result = userService.saveRole(role);
        return ResponseEntity.created(new URI("/api/v1/user/role/" + result.getId())).body(result);
    }

    @PostMapping("/photos/add")
    public ResponseEntity<?> addPhoto(@RequestParam("userId") String userId,
                           @RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image,Model model)
            throws URISyntaxException, IOException {
        String response = userService.addPhoto(userId , title, image);
        return ResponseEntity.created(new URI("/api/v1/user/photo/" + response)).body(response);
    }

}
