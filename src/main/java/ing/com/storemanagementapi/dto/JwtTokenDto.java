package ing.com.storemanagementapi.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Builder
@Value
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenDto {
    String token;
}
