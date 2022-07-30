package za.co.amakosifire.field.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String contactNumber;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
