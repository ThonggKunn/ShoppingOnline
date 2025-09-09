package Main.dto.response.order;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private String description;
    private String status;
    private Double totalAmount;
    private Date createdDate;
    private Date updatedDate;
    private UserInfo user;
    private List<OrderItemResponseDto> items;
}
