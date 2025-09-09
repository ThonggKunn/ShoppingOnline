package dto.request.order;

import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class UpdateOrderInfoRequestDto {

    @Size(max = 500, message = "Mô tả đơn hàng không được vượt quá 500 ký tự")
    private String description;
}
