package com.fooddelivery.food_delivery_app.mapper;

import com.fooddelivery.food_delivery_app.dto.ProductDTO;
import com.fooddelivery.food_delivery_app.entity.Product;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "imageBase64", expression = "java(product.getImageData() != null ? java.util.Base64.getEncoder().encodeToString(product.getImageData()) : null)")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    ProductDTO toDTO(Product product);

    @Mapping(target = "imageData", source = "imageBase64", qualifiedByName = "toByteArray")
    @Mapping(target = "restaurant", ignore = true)
    Product toEntity(ProductDTO dto);

    @Named("toByteArray")
    default byte[] toByteArray(String base64) {
        if (base64 == null || base64.isBlank()) return null;
        return java.util.Base64.getDecoder().decode(base64);
    }

    default Restaurant mapIdToRestaurant(Long id) {
        if (id == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        return restaurant;
    }
}