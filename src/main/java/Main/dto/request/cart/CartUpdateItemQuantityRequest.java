package Main.dto.request.cart;

import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class CartUpdateItemQuantityRequest {

    private Long productId;

    @NotNull(message = "Quantity cannot be blank")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100, message = "Quantity must not exceed 100")
    private Integer quantity;
}
