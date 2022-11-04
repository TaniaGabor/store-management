package ing.com.storemanagementapi.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiEntityNotFoundException extends RuntimeException {

    String entityName;
    long id;

    @Override
    public String getMessage() {
        return String.format("No %s was found for id %d!", entityName, id);
    }
}
