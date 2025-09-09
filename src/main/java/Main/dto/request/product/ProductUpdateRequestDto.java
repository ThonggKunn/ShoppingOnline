package Main.dto.request.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequestDto {

    @Size(min = 1, max = 200, message = "Product name must be between 1 and 200 characters")
    private String name;

    @Size(max = 1000, message = "Product description must not exceed 1000 characters")
    private String description;


    @Min(value = 0, message = "Quantity must not be negative")
    @Max(value = 999999, message = "Quantity cannot exceed 999999")
    private Integer stock;

    @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Product price is not in correct format")
    private Double price;

    @DecimalMin(value = "0.01", message = "Original price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Original price is not in correct format")
    private Double originalPrice;

    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;

}
