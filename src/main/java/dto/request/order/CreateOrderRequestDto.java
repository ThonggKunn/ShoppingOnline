package dto.request.order;

import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
public class CreateOrderRequestDto {

    @Size(max = 500, message = "Mô tả đơn hàng không được vượt quá 500 ký tự")
    private String description;

    @NotEmpty(message = "Danh sách sản phẩm không được để trống")
    @Valid
    private List<OrderItemRequestDto> items;
}
