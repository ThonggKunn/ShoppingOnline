package Main.dto.request.order;

import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class UpdateOrderInfoRequestDto {

    @Size(max = 500, message = "Order description must not be exceed 500 characters")
    private String description;
}
