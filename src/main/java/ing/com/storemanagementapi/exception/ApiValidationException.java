package ing.com.storemanagementapi.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiValidationException extends ApiException {

    @Getter
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class ApiValidationSubException {
        String field;
        String message;
        Object value;
    }

    final List<ApiValidationSubException> exceptionList = new ArrayList<>();

    public ApiValidationException(HttpStatus status, String message) {
        super(status, message);
    }

    public void addException(String field, String message, Object value) {
        exceptionList.add(new ApiValidationSubException(field, message, value));
    }
}
