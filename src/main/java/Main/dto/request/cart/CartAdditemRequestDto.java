package Main.dto.request.cart;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartAdditemRequestDto {

    @NotNull(message = "ID product cannot be blank")
    @Positive(message = "ID product must be positive number")
    private Long productId;

    @NotNull(message = "Quantity cannot be blank")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100, message = "Quantity must not exceed 100")
    private Integer quantity;
}
