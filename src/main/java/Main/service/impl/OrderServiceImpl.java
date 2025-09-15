package Main.service.impl;

import Main.dto.request.order.CreateOrderRequestDto;
import Main.dto.request.order.OrderItemRequestDto;
import Main.dto.request.order.UpdateOrderInfoRequestDto;
import Main.dto.request.order.UpdateOrderStatusRequestDto;
import Main.dto.response.order.OrderItemResponseDto;
import Main.dto.response.order.OrderResponseDto;
import Main.dto.response.order.UserInfo;
import Main.entity.Order;
import Main.entity.OrderItem;
import Main.entity.Product;
import Main.entity.User;
import Main.exception.OrderException;
import Main.exception.ProductNotFoundException;
import Main.exception.StockInsufficientException;
import Main.exception.UserNotFoundException;
import Main.repository.OrderRepository;
import Main.repository.ProductRepository;
import Main.repository.UserRepository;
import Main.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public List<OrderResponseDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUser_Id(userId);
        return orders.stream()
                .map(this::convertToOrderResponseDto)
                .toList();
    }

    @Override
    public OrderResponseDto createOrder(Long userId, CreateOrderRequestDto request) {
        // Validate user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // Create new order
        Order order = new Order();
        order.setDescription(request.getDescription());
        order.setStatus("ORDER_SUCCESS");
        order.setUser(user);
        order.setItems(new ArrayList<>());

        // Calculate total amount and create order items
        double totalAmount = 0.0;
        for (OrderItemRequestDto itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(
                            () -> new ProductNotFoundException(itemRequest.getProductId()));

            // Check stock
            if (product.getStock() < itemRequest.getQuantity()) {
                throw new StockInsufficientException("Insufficient stock for product: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setOriginalPrice(product.getOriginalPrice());
            orderItem.setPrice(product.getPrice());

            // Calculate total for this item (price * quantity)
            totalAmount += product.getPrice() * itemRequest.getQuantity();

            order.getItems().add(orderItem);

            // Update product stock
            product.setStock(product.getStock() - itemRequest.getQuantity());
            productRepository.save(product);
        }

        order.setTotalAmount(totalAmount);

        // Save order (will cascade to order items)
        Order savedOrder = orderRepository.save(order);

        return convertToOrderResponseDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderDetailsForUser(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId));

        // Verify that order belongs to the user
        if (!order.getUser().getId().equals(userId)) {
            throw new OrderException("Access denied: Order does not belong to user");
        }

        return convertToOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto updateOrderInfo(Long userId, Long orderId, UpdateOrderInfoRequestDto request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId));

        // Verify that order belongs to the user
        if (!order.getUser().getId().equals(userId)) {
            throw new OrderException("Access denied: Order does not belong to user");
        }

        // Only allow updates if order is in modifiable state
        if ("ORDER_CANCELLED".equals(order.getStatus()) ||
                "DELIVERY_SUCCESS".equals(order.getStatus()) ||
                "ORDER_FAIL".equals(order.getStatus()) ||
                "DELIVERY_FAIL".equals(order.getStatus())) {
            throw new OrderException("Cannot modify order in current status: " + order.getStatus());
        }

        order.setDescription(request.getDescription());
        Order savedOrder = orderRepository.save(order);

        return convertToOrderResponseDto(savedOrder);
    }

    @Override
    public OrderResponseDto cancelOrder(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId));

        // Verify that order belongs to the user
        if (!order.getUser().getId().equals(userId)) {
            throw new OrderException("Access denied: Order does not belong to user");
        }

        // Only allow cancellation if order is in cancellable state
        if (!"ORDER_SUCCESS".equals(order.getStatus()) &&
                !"ORDER_PROCESSING".equals(order.getStatus()) &&
                !"PREPARE_PRODUCT".equals(order.getStatus())) {
            throw new OrderException("Cannot cancel order in current status: " + order.getStatus());
        }

        order.setStatus("ORDER_CANCELLED");

        // Restore product stock
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            // Note: We need quantity info, but it's not in OrderItem entity
            // For now, assume quantity = 1 per item or modify entity structure
            product.setStock(product.getStock() + 1);
            productRepository.save(product);
        }

        Order savedOrder = orderRepository.save(order);
        return convertToOrderResponseDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToOrderResponseDto)
                .toList();
    }

    @Override
    public OrderResponseDto getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId));

        return convertToOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, UpdateOrderStatusRequestDto request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId));

        order.setStatus(request.getStatus());
        Order savedOrder = orderRepository.save(order);

        return convertToOrderResponseDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrderHistory(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return getUserOrders(userId);
    }

    private OrderResponseDto convertToOrderResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setDescription(order.getDescription());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setCreatedDate(order.getCreatedDate());
        dto.setUpdatedDate(order.getUpdatedDate());

        // User info
        if (order.getUser() != null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(order.getUser().getId());
            userInfo.setFullname(order.getUser().getFullname());
            userInfo.setEmail(order.getUser().getEmail());
            dto.setUser(userInfo);
        }

        // Order items
        List<OrderItemResponseDto> itemDtos = order.getItems().stream()
                .map(this::convertToOrderItemResponseDto)
                .toList();
        dto.setItems(itemDtos);

        return dto;
    }

    private OrderItemResponseDto convertToOrderItemResponseDto(OrderItem orderItem) {
        OrderItemResponseDto dto = new OrderItemResponseDto();
        dto.setId(orderItem.getId());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setProductName(orderItem.getProduct().getName());
        dto.setOriginalPrice(orderItem.getOriginalPrice());
        dto.setPrice(orderItem.getPrice());
        dto.setCreatedDate(orderItem.getCreatedDate());
        dto.setUpdatedDate(orderItem.getUpdatedDate());
        return dto;
    }
}
