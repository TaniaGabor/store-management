package ing.com.storemanagementapi.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    long id;

    @NotEmpty(message = "Name cannot be empty!")
    String name;

    @Min(value = 0, message = "Price cannot be negative!")
    @NonNull
    float price;

    @Min(value = 0, message = "Quantity cannot be negative!")
    @NonNull
    int quantity;
}
