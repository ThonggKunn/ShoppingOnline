package service;

import dto.request.cart.CartAdditemRequestDto;
import dto.request.cart.CartUpdateItemQuantityRequest;
import dto.response.cart.CartResponseDto;

public interface CartService {

    CartResponseDto getCartItems(Long userId);

    CartResponseDto updateItemQuantity(Long userId,
                                       Long cartItemId,
                                       CartUpdateItemQuantityRequest request);

    void removeItem(Long userId,
                    Long cartItemId);

    void clearCart(Long userId);

    void addToCart(Long userId, CartAdditemRequestDto request);
}
