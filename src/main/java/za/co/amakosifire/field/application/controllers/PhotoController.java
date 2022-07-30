package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.amakosifire.field.domain.image.PhotoService;
import za.co.amakosifire.field.domain.image.model.Photo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/photo")
@AllArgsConstructor
public class PhotoController {

    private final PhotoService photoService;


    @GetMapping("/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhotoByUserId(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "photos";
    }

    @PostMapping
    public ResponseEntity<?> addPhoto(@RequestParam("userId") String userId,
                           @RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image,Model model)
            throws URISyntaxException, IOException {
        String response = photoService.addPhoto(userId , title, image);
        return ResponseEntity.created(new URI("/api/v1/photo/" + response)).body(response);
    }

}
