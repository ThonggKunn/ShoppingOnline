package Main.exception;

public class CartNotFoundException extends ApiException {
    public CartNotFoundException(Long userId) {
        super("Cart not found for user ID: " + userId, 404);
    }
}