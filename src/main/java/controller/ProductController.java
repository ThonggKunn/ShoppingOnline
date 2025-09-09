package controller;

import constant.UrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;


@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstant.API_BASE_V1)
public class ProductController {

    private final ProductService productService;

    @GetMapping(UrlConstant.PRODUCTS)
    public ResponseEntity<Object> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(productService.getProducts(name, category));
    }

    @GetMapping(UrlConstant.CRUD_PRODUCT)
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
