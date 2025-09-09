package service;

public interface ProductService {

    // User function
    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getProducts(String name,
                                         String category);

    // Admin function
    ProductCreateResponseDto create(ProductCreateRequestDto request);

    ProductUpdateResponseDto update(Long id,
                                    ProductUpdateRequestDto request);

    void delete(Long id);



}
