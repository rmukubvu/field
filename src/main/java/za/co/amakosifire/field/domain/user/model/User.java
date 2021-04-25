package za.co.amakosifire.field.domain.user.model;

import lombok.Builder;
import lombok.Data;
import za.co.amakosifire.field.domain.shared.PhoneNumber;

import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean enabled;
    private String contactNumber;
    private LocalDateTime creationDate;

    public User onSave(String encodedPassword, String countryCode) {
        this.userName = PhoneNumber.getFormat(contactNumber, countryCode);
        this.creationDate = LocalDateTime.now();
        this.contactNumber = PhoneNumber.getFormat(contactNumber, countryCode);
        this.password = encodedPassword;
        return this;
    }
}
