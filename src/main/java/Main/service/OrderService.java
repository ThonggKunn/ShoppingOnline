package Main.service;

import Main.dto.request.order.CreateOrderRequestDto;
import Main.dto.request.order.UpdateOrderInfoRequestDto;
import Main.dto.request.order.UpdateOrderStatusRequestDto;
import Main.dto.response.order.OrderResponseDto;

import java.util.List;

public interface OrderService {

    // User functions
    OrderResponseDto createOrder(Long userId,
                                 CreateOrderRequestDto request);

    List<OrderResponseDto> getUserOrders(Long userId);

    OrderResponseDto getOrderDetailsForUser(Long userId,
                                            Long orderId);

    OrderResponseDto updateOrderInfo(Long userId,
                                     Long orderId,
                                     UpdateOrderInfoRequestDto request);

    OrderResponseDto cancelOrder(Long userId,
                                 Long orderId);

    // Admin functions
    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderDetails(Long orderId);

    OrderResponseDto updateOrderStatus(Long orderId,
                                       UpdateOrderStatusRequestDto request);

    List<OrderResponseDto> getOrderHistory(Long userId);


}