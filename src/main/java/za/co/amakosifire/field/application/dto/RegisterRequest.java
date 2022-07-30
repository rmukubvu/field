package za.co.amakosifire.field.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String contactNumber;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
