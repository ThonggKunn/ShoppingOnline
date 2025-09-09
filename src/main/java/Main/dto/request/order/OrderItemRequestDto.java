package Main.dto.request.order;

import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class OrderItemRequestDto {

    @NotNull(message = "ID product cannot be blank")
    @Positive(message = "ID product must be a positive number")
    private Long productId;

    @NotNull(message = "Quantity cannot be black")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100, message = "Quantity cannot be exceed 100")
    private Integer quantity;
}
