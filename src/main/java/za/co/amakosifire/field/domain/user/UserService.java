package za.co.amakosifire.field.domain.user;

import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.co.amakosifire.field.domain.user.mapper.PhotoMapper;
import za.co.amakosifire.field.domain.user.mapper.RoleMapper;
import za.co.amakosifire.field.domain.user.mapper.UserMapper;
import za.co.amakosifire.field.domain.user.model.Photo;
import za.co.amakosifire.field.domain.user.model.Role;
import za.co.amakosifire.field.domain.user.model.User;
import za.co.amakosifire.field.infrastructure.user.PhotoRepository;
import za.co.amakosifire.field.infrastructure.user.RoleRepository;
import za.co.amakosifire.field.infrastructure.user.UserRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Value("${phone.country.code}")
    private String COUNTRY_CODE;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final RoleRepository roleRepository;


    public User save(User user) {
        var model = userRepository.save(UserMapper.INSTANCE.fromDomain(user.onSave(COUNTRY_CODE)));
        return UserMapper.INSTANCE.toDomain(model);
    }

    public Role saveRole(Role role) {
        return RoleMapper.INSTANCE.toDomain(
                roleRepository.save(RoleMapper.INSTANCE.fromDomain(role)));
    }

//    public User authenticate(String userName,String password) throws Exception {
//        try {
//            var user = loadUserByUsername(userName);
//            if (user.isValidPassword(password)) {
//                return user;
//            }
//            throw new Exception(userName + " user/password combination invalid");
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll().stream().map(RoleMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        var userOptional = UserMapper.INSTANCE.toDomain(userRepository.findUserByUserNameEquals(username).get());
        var user = Optional.ofNullable(userOptional).orElseThrow(() -> new UsernameNotFoundException("No user " +
                "Found with username : " + username));

        return new org.springframework.security
                .core.userdetails.User(user.getUserName(), user.getPassword(),
                user.getEnabled(), true, true,
                true, getAuthorities("USER"));
    }

    public User findUserById(String id) {
        var user = userRepository.findById(id);
        return user.isPresent() ? UserMapper.INSTANCE.toDomain(user.get()) : null;
    }

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

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
