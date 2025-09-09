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

    @Size(min = 1, max = 200, message = "Tên sản phẩm phải từ 1 đến 200 ký tự")
    private String name;

    @Size(max = 1000, message = "Mô tả sản phẩm không được vượt quá 1000 ký tự")
    private String description;


    @Min(value = 0, message = "Số lượng không được âm")
    @Max(value = 999999, message = "Số lượng không được vượt quá 999999")
    private Integer stock;

    @DecimalMin(value = "0.01", message = "Giá sản phẩm phải lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Giá sản phẩm không đúng định dạng")
    private Double price;

    @DecimalMin(value = "0.01", message = "Giá gốc phải lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Giá gốc không đúng định dạng")
    private Double originalPrice;

    @Size(max = 50, message = "Danh mục không được vượt quá 50 ký tự")
    private String category;

}
