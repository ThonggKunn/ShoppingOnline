package controller;

import constant.UrlConstant;
import dto.request.order.CreateOrderRequestDto;
import dto.request.order.UpdateOrderInfoRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OrderService;


@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstant.API_BASE_V1)
public class OrderController {

    private final OrderService orderService;

    @GetMapping(UrlConstant.USER_ORDERS)
    public ResponseEntity<Object> getUserOrders() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @PostMapping(UrlConstant.ORDERS)
    public ResponseEntity<Object> createOrder(@Valid @RequestBody CreateOrderRequestDto request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(orderService.createOrder(userId, request));
    }

    @GetMapping(UrlConstant.CRUD_USER_ORDERS)
    public ResponseEntity<Object> getUserOrderDetail(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(orderService.getOrderDetailsForUser(userId, id));
    }

    @PatchMapping(UrlConstant.UPDATE_ORDER_INFO)
    public ResponseEntity<Object> updateOrderInfo(@PathVariable Long id,
                                                  @Valid @RequestBody UpdateOrderInfoRequestDto request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(orderService.updateOrderInfo(userId, id, request));
    }

    @PatchMapping(UrlConstant.CANCEL_ORDER)
    public ResponseEntity<Object> cancelOrder(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(orderService.cancelOrder(userId, id));
    }

    // Helper method to get current user ID (placeholder)
    public static Long getCurrentUserId() {
        // Placeholder implementation
        // Will be replaced with SecurityContextHolder implementation when Spring
        // Security is added
        return 2L;
    }

}
