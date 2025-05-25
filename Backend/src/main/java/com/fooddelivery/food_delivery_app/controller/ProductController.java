package com.fooddelivery.food_delivery_app.controller;

import com.fooddelivery.food_delivery_app.dto.ProductDTO;
import com.fooddelivery.food_delivery_app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Creare produs nou (admin)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return ResponseEntity.ok(created);
    }

    // Afișare produs după id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    // Afișare produse după restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ProductDTO>> getProductsByRestaurant(@PathVariable Long restaurantId) {
        List<ProductDTO> products = productService.getProductsByRestaurantId(restaurantId);
        return ResponseEntity.ok(products);
    }

    // Afișare toate produsele disponibile
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAvailableProducts() {
        List<ProductDTO> products = productService.getAllAvailableProducts();
        return ResponseEntity.ok(products);
    }

    // Actualizare produs existent (admin)
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
                                                    @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updated);
    }

    // Ștergere produs după id (admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
