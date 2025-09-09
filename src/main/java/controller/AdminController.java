package controller;

import constant.UrlConstant;
import dto.request.order.UpdateOrderStatusRequestDto;
import dto.request.product.ProductCreateRequestDto;
import dto.request.product.ProductUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AdminService;


@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstant.API_BASE_V1)
public class AdminController {

    private final AdminService adminService;

    // ============================= USER
    // MANAGEMENT========================================

    @GetMapping(UrlConstant.ADMIN_USERS)
    public ResponseEntity<Object> getUsers(@RequestParam(required = false) String email) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.getUsers(email));
    }

    @DeleteMapping(UrlConstant.CRUD_ADMIN_USERS)
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        checkAdminAccess();
        adminService.deleteUser(id);
        return ResponseEntity.ok("Delete user successfully");
    }

    @PatchMapping(UrlConstant.BLOCK_USERS)
    public ResponseEntity<Object> blockUser(@PathVariable Long id) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.blockUser(id));
    }

    @PatchMapping(UrlConstant.UNBLOCK_USERS)
    public ResponseEntity<Object> unblockUser(@PathVariable Long id) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.unblockUser(id));
    }

    // ============================= PRODUCT
    // MANAGEMENT========================================

    @GetMapping(UrlConstant.ADMIN_PRODUCTS)
    public ResponseEntity<Object> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.getProducts(name, category));
    }

    @PostMapping(UrlConstant.ADMIN_PRODUCTS)
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductCreateRequestDto request) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.createProduct(request));
    }

    @PutMapping(UrlConstant.CRUD_ADMIN_PRODUCTS)
    public ResponseEntity<Object> updateProduct(@PathVariable Long id,
                                                @Valid @RequestBody ProductUpdateRequestDto request) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.updateProduct(id, request));
    }

    @DeleteMapping(UrlConstant.CRUD_ADMIN_PRODUCTS)
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        checkAdminAccess();
        adminService.deleteProduct(id);
        return ResponseEntity.ok(String.format(
                "Delete product with id %d successfully",
                id));
    }

    // ============================= ORDER
    // MANAGEMENT========================================

    @GetMapping(UrlConstant.ADMIN_ORDERS)
    public ResponseEntity<Object> getAllOrders() {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.getAllOrders());
    }

    @GetMapping(UrlConstant.CRUD_ADMIN_ORDERS)
    public ResponseEntity<Object> getOrderDetails(@PathVariable Long id) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.getOrderDetails(id));
    }

    @PatchMapping(UrlConstant.UPDATE_ORDER_DETAIL)
    public ResponseEntity<Object> updateOrderStatus(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateOrderStatusRequestDto request) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.updateOrderStatus(id, request));
    }

    @GetMapping(UrlConstant.GET_ORDER_HISTORY)
    public ResponseEntity<Object> getOrderHistory(@PathVariable("id") Long userId) {
        checkAdminAccess();
        return ResponseEntity.ok(adminService.getOrderHistory(userId));
    }

    // Helper method to check admin access (placeholder)
    private void checkAdminAccess() {
        // Placeholder implementation
        // Will be replaced with proper admin role check when Spring Security is added
        // Could throw AccessDeniedException if not admin
    }

}