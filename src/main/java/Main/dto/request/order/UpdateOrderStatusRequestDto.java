package Main.dto.request.order;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class UpdateOrderStatusRequestDto {

    @NotBlank(message = "Trạng thái đơn hàng không được để trống")
    private String status;
}
