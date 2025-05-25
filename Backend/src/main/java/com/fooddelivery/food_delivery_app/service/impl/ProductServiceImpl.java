package com.fooddelivery.food_delivery_app.service.impl;

import com.fooddelivery.food_delivery_app.dto.ProductDTO;
import com.fooddelivery.food_delivery_app.entity.Product;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import com.fooddelivery.food_delivery_app.mapper.ProductMapper;
import com.fooddelivery.food_delivery_app.repository.ProductRepository;
import com.fooddelivery.food_delivery_app.service.ProductService;
import com.fooddelivery.food_delivery_app.service.domain.ProductDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductDomainService productDomainService;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        if (product.getId() != null && product.getId() == 0L) {
            product.setId(null);
        }

        if (productDTO.getRestaurantId() != null) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(productDTO.getRestaurantId());
            product.setRestaurant(restaurant);
        }

        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produsul nu există."));
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getProductsByRestaurantId(Long restaurantId) {
        List<Product> products = productRepository.findByRestaurantId(restaurantId);
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllAvailableProducts() {
        return productRepository.findByAvailableTrue()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produsul nu există."));
        Product updatedProduct = productMapper.toEntity(productDTO);
        updatedProduct.setId(existing.getId());

        // !!! Asociază restaurantul NOU dacă e prezent în DTO, altfel păstrează-l pe cel vechi
        if (productDTO.getRestaurantId() != null) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(productDTO.getRestaurantId());
            updatedProduct.setRestaurant(restaurant);
        } else {
            updatedProduct.setRestaurant(existing.getRestaurant());
        }

        productDomainService.validateProduct(updatedProduct);

        Product saved = productRepository.save(updatedProduct);

        ProductDTO result = productMapper.toDTO(saved);
        if (saved.getRestaurant() != null) {
            result.setRestaurantId(saved.getRestaurant().getId());
        }
        return result;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Produsul nu există.");
        }
        productRepository.deleteById(id);
    }
}