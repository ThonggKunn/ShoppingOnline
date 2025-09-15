package Main.service;

import Main.dto.request.product.ProductCreateRequestDto;
import Main.dto.request.product.ProductUpdateRequestDto;
import Main.dto.response.product.ProductCreateResponseDto;
import Main.dto.response.product.ProductResponseDto;
import Main.dto.response.product.ProductUpdateResponseDto;

import java.util.List;

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
