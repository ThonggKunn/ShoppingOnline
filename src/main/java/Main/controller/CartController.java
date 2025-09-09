package Main.controller;

import Main.constant.UrlConstant;
import Main.dto.request.cart.CartAdditemRequestDto;
import Main.dto.request.cart.CartUpdateItemQuantityRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Main.service.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstant.API_BASE_V1)
public class CartController {

    private final CartService cartService;

    // API view cart by userId
    @GetMapping(UrlConstant.USER_CARTS)
    public ResponseEntity<Object> viewCart() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    // API add to cart
    @PostMapping(UrlConstant.CART_ITEMS)
    public ResponseEntity<Object> addToCart(@Valid @RequestBody CartAdditemRequestDto request) {
        Long userId = getCurrentUserId();
        cartService.addToCart(userId, request);
        return ResponseEntity.ok("Added to cart successfully");
    }

    @PatchMapping(UrlConstant.UPDATE_CART_ITEMS_QUANTITY)
    public ResponseEntity<Object> updateCartItemQuantity(
            @PathVariable("id") Long cartItemId,
            @Valid @RequestBody CartUpdateItemQuantityRequest request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, cartItemId, request));
    }

    @DeleteMapping(UrlConstant.CRUD_CART_ITEMS)
    public ResponseEntity<Object> removeFromCart(@PathVariable("id") Long cartItemId) {
        Long userId = getCurrentUserId();
        cartService.removeItem(userId, cartItemId);
        return ResponseEntity.ok("Item removed");
    }

    @DeleteMapping(UrlConstant.CARTS)
    public ResponseEntity<Object> clearCart() {
        Long userId = getCurrentUserId();
        cartService.clearCart(userId);
        return ResponseEntity.ok(null);
    }

    // Helper method to get current user ID (placeholder)
    public static Long getCurrentUserId() {
        // Placeholder implementation
        // Will be replaced with SecurityContextHolder implementation when Spring
        // Security is added
        return 2L;
    }

}
