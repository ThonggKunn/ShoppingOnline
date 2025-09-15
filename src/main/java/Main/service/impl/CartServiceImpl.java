package Main.service.impl;

import Main.dto.request.cart.CartAdditemRequestDto;
import Main.dto.request.cart.CartUpdateItemQuantityRequest;
import Main.dto.response.cart.CartItemResponseDto;
import Main.dto.response.cart.CartResponseDto;
import Main.dto.response.product.ProductResponseDto;
import Main.entity.Cart;
import Main.entity.CartItem;
import Main.entity.Product;
import Main.exception.CartItemNotFoundException;
import Main.exception.CartNotFoundException;
import Main.exception.ProductNotFoundException;
import Main.repository.CartItemRepository;
import Main.repository.CartRepository;
import Main.repository.ProductRepository;
import Main.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public CartResponseDto getCartItems(Long userId) {

        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new CartNotFoundException(userId));

        return convertToCartResponseDto(cart);
    }

    @Override
    public CartResponseDto updateItemQuantity(
            Long userId,
            Long cartItemId,
            CartUpdateItemQuantityRequest request) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CartNotFoundException(userId));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException(cartItemId));

        // Verify that the cart item belongs to the user's cart
        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new CartItemNotFoundException(
                    cart.getId(),
                    cartItemId);
        }

        // Update quantity
        cartItem.setQuantity(request.getQuantity());
        cartItemRepository.save(cartItem);

        return convertToCartResponseDto(cart);
    }

    @Override
    public void removeItem(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CartNotFoundException(userId));

        CartItem itemToRemove = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException(cartItemId));

        // Verify that the cart item belongs to the user's cart
        if (!itemToRemove.getCart().getId().equals(cart.getId())) {
            throw new CartItemNotFoundException(
                    cart.getId(),
                    cartItemId);
        }

        cart.getItems().remove(itemToRemove);
        cartItemRepository.delete(itemToRemove);
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CartNotFoundException(userId));

        // Delete all cart items
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public void addToCart(Long userId, CartAdditemRequestDto request) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CartNotFoundException(userId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(request.getProductId()));

        CartItem item = cartItemRepository.findByCart_IdAndProduct_Id(cart.getId(), product.getId())
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + request.getQuantity());
        cartItemRepository.save(item);
    }

    private CartItemResponseDto converToCartItemResponseDto(CartItem item) {

        CartItemResponseDto dto = new CartItemResponseDto();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        //
        ProductResponseDto product = new ProductResponseDto();
        product.setId(item.getProduct().getId());
        product.setName(item.getProduct().getName());
        product.setDescription(item.getProduct().getDescription());
        product.setStock(item.getProduct().getStock());
        product.setPrice(item.getProduct().getPrice());
        product.setCategory(item.getProduct().getCategory());
        //
        dto.setProduct(product);

        return dto;
    }

    private CartResponseDto convertToCartResponseDto(Cart cart) {

        CartResponseDto dto = new CartResponseDto();
        dto.setCartId(cart.getId());
        //
        List<CartItemResponseDto> items = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            CartItemResponseDto cartItem = converToCartItemResponseDto(item);
            items.add(cartItem);
        }
        dto.setItems(items);

        return dto;
    }
}
