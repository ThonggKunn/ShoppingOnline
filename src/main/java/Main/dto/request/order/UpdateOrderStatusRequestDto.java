package Main.dto.request.order;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class UpdateOrderStatusRequestDto {

    @NotBlank(message = "Product status cannot be blank")
    private String status;
}
