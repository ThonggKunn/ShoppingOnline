package controller;

import constant.UrlConstant;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(UrlConstant.API_BASE_V1)
public class ProductController {

    @GetMapping(UrlConstant.PRODUCTS)
    public Object getProducts(@RequestParam(required = false) String category,
                              @RequestParam(required = false) String name) {
        Map<String, Object> response = new HashMap<>();
        response.put("category", category);
        response.put("name", name);
        return response; // Return request parameters
    }

    @GetMapping(UrlConstant.CRUD_PRODUCT)
    public Object getProductById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        return response; // Return path variable
    }
}
