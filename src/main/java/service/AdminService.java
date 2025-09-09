package service;

import dto.request.order.UpdateOrderStatusRequestDto;
import dto.request.product.ProductCreateRequestDto;
import dto.request.product.ProductUpdateRequestDto;
import dto.response.order.OrderResponseDto;
import dto.response.product.ProductCreateResponseDto;
import dto.response.product.ProductUpdateResponseDto;
import dto.response.user.UserResponseDto;

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
