package Main.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Double originalPrice;
    private String category;
    private String status; // ACTIVE, INACTIVE, OUT_OF_STOCK
    private String createdAt;
    private String updatedAt;

}
