package za.co.amakosifire.field.domain.image;

import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.co.amakosifire.field.domain.image.mapper.PhotoMapper;
import za.co.amakosifire.field.domain.image.model.Photo;
import za.co.amakosifire.field.infrastructure.user.PhotoRepository;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PhotoService {


    private final PhotoRepository photoRepository;

    public String addPhoto(String userId, String title,  MultipartFile file) throws IOException {
        Photo photo = Photo.builder()
                .title(title)
                .userId(userId)
                .image(new Binary(BsonBinarySubType.BINARY, file.getBytes())).build();
        var insertedPhoto = photoRepository.insert(PhotoMapper.INSTANCE.fromDomain(photo));
        return PhotoMapper.INSTANCE.toDomain(insertedPhoto).getId();
    }

    public Photo getPhotoByUserId(String id) {
        var photo = photoRepository.findPhotoByUserIdEquals(id);
        return photo.isPresent() ? PhotoMapper.INSTANCE.toDomain(photo.get()) : null;
    }

}
