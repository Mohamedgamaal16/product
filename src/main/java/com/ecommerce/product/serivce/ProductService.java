package com.ecommerce.product.serivce;

import com.ecommerce.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto);

    List<ProductDto> getProducts();
    ProductDto getProductById(String id);
    void deleteProductById(String id);
    List<ProductDto> getDeletedProducts();
    void restoreProductById(String id);
}
