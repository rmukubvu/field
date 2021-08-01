package za.co.amakosifire.field.domain.auth.model;

import lombok.Builder;
import lombok.Data;
import za.co.amakosifire.field.domain.shared.PhoneNumber;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class User {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private String contactNumber;
    private Date creationDate;

    public User onSave(String encodedPassword, String countryCode) {
        this.creationDate = new Date();
        this.enabled = true;
        this.contactNumber = PhoneNumber.getFormat(contactNumber, countryCode);
        this.password = encodedPassword;
        return this;
    }
}
