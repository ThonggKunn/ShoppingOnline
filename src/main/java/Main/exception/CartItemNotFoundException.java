package Main.exception;

public class CartItemNotFoundException extends ApiException {
    public CartItemNotFoundException(Long cartItemId) {
        super("Not found CartItem with ID: " + cartItemId, 404);
    }

    public CartItemNotFoundException(
            Long cartId,
            Long cartItemId) {
        super("Cart with ID " + cartId + " does not have cart item with ID: " + cartItemId, 404);
    }
}
