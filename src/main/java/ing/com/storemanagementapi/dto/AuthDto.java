package ing.com.storemanagementapi.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;

@Value
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthDto {

    @Size(min = 2, message = "Username should be at least 2 characters!")
    String username;

    @Size(min = 2, message = "Password should be at least 2 characters!")
    String password;
}
