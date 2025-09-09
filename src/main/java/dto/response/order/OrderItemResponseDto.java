package dto.response.order;

import lombok.Data;
import java.util.Date;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long productId;
    private String productName;
    private Double originalPrice;
    private Double price;
    private Date createdDate;
    private Date updatedDate;
}
