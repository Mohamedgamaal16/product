package com.ecommerce.product.dto;

import com.ecommerce.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    // تحويل من DTO إلى Entity
    public static Product toEntity( ProductDto productDto) {
        return Product.builder()
                .id(productDto.id)
                .name(productDto.name)
                .description(productDto.description)
                .price(productDto.price)
                .build();
    }
        public static ProductDto fromEntity(Product product) {
            return ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
        }

}
