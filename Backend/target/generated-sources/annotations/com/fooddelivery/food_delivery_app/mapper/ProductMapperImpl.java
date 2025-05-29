package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.ProductDTO;
import com.fooddelivery.food_delivery_app.entity.Product;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-29T21:58:32+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.restaurantId( productRestaurantId( product ) );
        productDTO.id( product.getId() );
        productDTO.name( product.getName() );
        productDTO.price( product.getPrice() );
        productDTO.description( product.getDescription() );
        productDTO.available( product.isAvailable() );

        productDTO.imageBase64( product.getImageData() != null ? java.util.Base64.getEncoder().encodeToString(product.getImageData()) : null );

        return productDTO.build();
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setImageData( toByteArray( dto.getImageBase64() ) );
        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setPrice( dto.getPrice() );
        product.setDescription( dto.getDescription() );
        if ( dto.getAvailable() != null ) {
            product.setAvailable( dto.getAvailable() );
        }

        return product;
    }

    private Long productRestaurantId(Product product) {
        if ( product == null ) {
            return null;
        }
        Restaurant restaurant = product.getRestaurant();
        if ( restaurant == null ) {
            return null;
        }
        Long id = restaurant.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
