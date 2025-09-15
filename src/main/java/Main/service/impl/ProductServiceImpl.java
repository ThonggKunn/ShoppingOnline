package Main.service.impl;

import Main.dto.request.product.ProductCreateRequestDto;
import Main.dto.request.product.ProductUpdateRequestDto;
import Main.dto.response.product.ProductCreateResponseDto;
import Main.dto.response.product.ProductResponseDto;
import Main.dto.response.product.ProductUpdateResponseDto;
import Main.entity.Product;
import Main.exception.ProductNotFoundException;
import Main.repository.ProductRepository;
import Main.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return convertToProductDto(product);
    }

    @Override
    public List<ProductResponseDto> getProducts(String name, String category) {

        List<Product> products;

        products = productRepository.findByNameAndCategory(name, category);

        // Only return products with stock > 0 for users
        products = products.stream()
                .filter(product -> product.getStock() != null && product.getStock() > 0)
                .toList();

        return products.stream()
                .map(this::convertToProductDto)
                .toList();
    }

    private ProductResponseDto convertToProductDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());

        return dto;
    }

    @Override
    public ProductCreateResponseDto create(ProductCreateRequestDto request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setCategory(request.getCategory());

        Product savedProduct = productRepository.save(product);

        ProductCreateResponseDto response = new ProductCreateResponseDto();
        BeanUtils.copyProperties(savedProduct, response);
        response.setMessage("Product created successfully");

        return response;
    }

    @Override
    public ProductUpdateResponseDto update(Long id, ProductUpdateRequestDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        // Update fields
        if (request.getName() != null)
            product.setName(request.getName());
        if (request.getDescription() != null)
            product.setDescription(request.getDescription());
        if (request.getStock() != null)
            product.setStock(request.getStock());
        if (request.getPrice() != null)
            product.setPrice(request.getPrice());
        if (request.getOriginalPrice() != null)
            product.setOriginalPrice(request.getOriginalPrice());
        if (request.getCategory() != null)
            product.setCategory(request.getCategory());

        Product updatedProduct = productRepository.save(product);

        ProductUpdateResponseDto response = new ProductUpdateResponseDto();
        BeanUtils.copyProperties(updatedProduct, response);
        response.setMessage("Product updated successfully");

        return response;
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }

}