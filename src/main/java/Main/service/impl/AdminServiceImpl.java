package Main.service.impl;

import Main.dto.request.order.UpdateOrderStatusRequestDto;
import Main.dto.request.product.ProductCreateRequestDto;
import Main.dto.request.product.ProductUpdateRequestDto;
import Main.dto.response.order.OrderResponseDto;
import Main.dto.response.product.AdminProductResponseDto;
import Main.dto.response.product.ProductCreateResponseDto;
import Main.dto.response.product.ProductUpdateResponseDto;
import Main.dto.response.user.UserResponseDto;
import Main.entity.Product;
import Main.repository.ProductRepository;
import Main.service.AdminService;
import Main.service.OrderService;
import Main.service.ProductService;
import Main.service.UserService;
import Main.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    private final ProductRepository productRepository;


    @Override
    public List<UserResponseDto> getUsers(String email) {
        return userService.getUsers(email);
    }

    @Override
    public void deleteUser(Long userId) {
        userService.deleteUserById(userId);
    }

    @Override
    public UserResponseDto blockUser(Long userId) {
        return userService.blockUser(userId);
    }

    @Override
    public UserResponseDto unblockUser(Long userId) {
        return userService.unblockUser(userId);
    }

    @Override
    public List<AdminProductResponseDto> getProducts(String name, String category) {
        List<Product> products;

        // Use repository method for search (handles all cases)
        products = productRepository.findByNameAndCategory(name, category);

        // Convert to DTO
        return products.stream()
                .map(this::convertToAdminProductDto)
                .toList();
    }

    private AdminProductResponseDto convertToAdminProductDto(Product product) {
        AdminProductResponseDto dto = new AdminProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setOriginalPrice(product.getOriginalPrice());
        dto.setCategory(product.getCategory());

        // Determine status based on stock
        if (product.getStock() == null || product.getStock() <= 0) {
            dto.setStatus("OUT_OF_STOCK");
        } else {
            dto.setStatus("ACTIVE");
        }

        dto.setCreatedAt(
                product.getCreatedDate() != null ?
                        DateUtils.formatDate(product.getCreatedDate()) : null);
        dto.setUpdatedAt(
                product.getUpdatedDate() != null ?
                        DateUtils.formatDate(product.getUpdatedDate()) : null);

        return dto;
    }


    @Override
    public ProductCreateResponseDto createProduct(ProductCreateRequestDto request) {
        return productService.create(request);
    }

    @Override
    public ProductUpdateResponseDto updateProduct(Long productId,
                                                  ProductUpdateRequestDto request) {
        return productService.update(productId, request);
    }

    @Override
    public void deleteProduct(Long productId) {
        productService.delete(productId);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public OrderResponseDto getOrderDetails(Long orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId,
                                              UpdateOrderStatusRequestDto request) {
        return orderService.updateOrderStatus(orderId, request);
    }

    @Override
    public List<OrderResponseDto> getOrderHistory(Long orderId) {
        return orderService.getOrderHistory(orderId);
    }

}
