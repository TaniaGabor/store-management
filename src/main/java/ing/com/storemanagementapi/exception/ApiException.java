package ing.com.storemanagementapi.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ApiException {
    HttpStatus httpStatus;
    LocalDateTime timestamp = LocalDateTime.now();
    String message = "Unknown error";

    public ApiException(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }
}
