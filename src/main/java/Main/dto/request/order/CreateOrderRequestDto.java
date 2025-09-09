package Main.dto.request.order;

import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
public class CreateOrderRequestDto {

    @Size(max = 500, message = "Order description must not exceed 500 characters")
    private String description;

    @NotEmpty(message = "Product list cannot be empty")
    @Valid
    private List<OrderItemRequestDto> items;
}
