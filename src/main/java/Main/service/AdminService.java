package Main.service;

import Main.dto.request.order.UpdateOrderStatusRequestDto;
import Main.dto.request.product.ProductCreateRequestDto;
import Main.dto.request.product.ProductUpdateRequestDto;
import Main.dto.response.order.OrderResponseDto;
import Main.dto.response.product.AdminProductResponseDto;
import Main.dto.response.product.ProductCreateResponseDto;
import Main.dto.response.product.ProductUpdateResponseDto;
import Main.dto.response.user.UserResponseDto;

import java.util.List;

public interface AdminService {

    // User management function
    List<UserResponseDto> getUsers(String email);

    void deleteUser(Long userId);

    UserResponseDto blockUser(Long userId);

    UserResponseDto unblockUser(Long userId);

    // Product management function
    List<AdminProductResponseDto> getProducts(String category, String name);

    ProductCreateResponseDto createProduct(ProductCreateRequestDto request);

    ProductUpdateResponseDto updateProduct(Long productId,
                                           ProductUpdateRequestDto request);

    void deleteProduct(Long productId);

    // Order management function
    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderDetails(Long orderId);

    OrderResponseDto updateOrderStatus(Long orderId,
                                       UpdateOrderStatusRequestDto request);

    List<OrderResponseDto> getOrderHistory(Long userId);

}
