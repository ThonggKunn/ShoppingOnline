package Main.exception;

public class ProductNotFoundException extends ApiException {

    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id, 404);
    }

    public ProductNotFoundException(String message) {
        super(message, 404);
    }
}
