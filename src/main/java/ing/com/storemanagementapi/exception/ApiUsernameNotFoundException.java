package ing.com.storemanagementapi.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiUsernameNotFoundException extends RuntimeException {

    String username;

    @Override
    public String getMessage() {
        return String.format("Invalid username: %s", username);
    }
}
