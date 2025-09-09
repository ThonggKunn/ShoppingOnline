package dto.response.cart;
import dto.response.product.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDto {

    private Long id;

    ProductResponseDto product;

    private int quantity;

}
