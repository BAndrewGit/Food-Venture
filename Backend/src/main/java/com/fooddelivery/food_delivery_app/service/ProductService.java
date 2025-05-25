package com.fooddelivery.food_delivery_app.service;

import com.fooddelivery.food_delivery_app.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getProductsByRestaurantId(Long restaurantId);

    List<ProductDTO> getAllAvailableProducts();

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}
