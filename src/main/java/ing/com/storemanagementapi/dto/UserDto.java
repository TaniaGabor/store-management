package ing.com.storemanagementapi.dto;

import ing.com.storemanagementapi.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    long id;

    @Size(min = 2, message = "Name should have at least 2 characters!")
    String name;

    @Size(min = 2, message = "Username should have at least 2 characters!")
    String username;

    @Email
    String email;

    @Size(min = 2, message = "Password should have at least 2 characters!")
    String password;

    Set<Role> roles;
}
