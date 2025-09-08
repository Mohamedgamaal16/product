package com.ecommerce.product.serivce;


import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.error.ProductApiException;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repos.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;


    @Override
    public void createProduct(ProductDto productDto){
        try {
            if (productDto == null ){
                throw new ProductApiException("please provide all data");

            }
            Product product = Product.builder()
                    .name(productDto.getName())
                    .description(productDto.getDescription())
                    .price(productDto.getPrice())

                    .build();
            productRepo.save(product);
            log.info("product created succesfully");
        } catch (Exception e) {
            throw new ProductApiException("Failed to create Product"+e.getMessage());
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        try {
            List<Product> products = productRepo.findAll()
                    .stream()
                    .filter(product -> !product.isDeleted()) // 👈 exclude deleted products
                    .toList();

            if (products.isEmpty()) {
                throw new ProductApiException("Products list is empty");
            }

            List<ProductDto> productDtos = products.stream()
                    .map(ProductDto::fromEntity)
                    .toList();

            log.info("Products retrieved successfully, count: {}", productDtos.size());
            return productDtos;

        } catch (Exception e) {
            throw new ProductApiException("No Products found");
        }
    }


    @Override
    public ProductDto getProductById(String id) {
        try {
            Product product = productRepo.findById(id)
                    .orElseThrow(() -> new ProductApiException("No product found with id: " + id));

            if (product.isDeleted()) {
                throw new ProductApiException("Product with id " + id + " has been deleted");
            }

            ProductDto productDto = ProductDto.fromEntity(product);
            log.info("Product with id {} retrieved successfully", id);

            return productDto;

        } catch (ProductApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while retrieving product with id {}: {}", id, e.getMessage(), e);
            throw new ProductApiException("An unexpected error occurred while fetching the product");
        }
    }

    @Override
    public void deleteProductById(String id) {
        try {
            Product product = productRepo.findById(id)
                    .orElseThrow(() -> new ProductApiException("No product found with id: " + id));

            if (product.isDeleted()) {
                throw new ProductApiException("Product with id " + id + " is already deleted");
            }

            product.setDeleted(true);
            productRepo.save(product);

            log.info("Product with id {} marked as deleted (soft delete)", id);

        } catch (ProductApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while soft deleting product with id {}: {}", id, e.getMessage(), e);
            throw new ProductApiException("An unexpected error occurred while soft deleting the product");
        }
    }
@Override
    public void restoreProductById(String id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductApiException("No product found with id: " + id));

        if (!product.isDeleted()) {
            throw new ProductApiException("Product with id " + id + " is not deleted");
        }

        product.setDeleted(false);
        productRepo.save(product);

        log.info("Product with id {} has been restored", id);
    }
    @Override
    public List<ProductDto> getDeletedProducts() {
        try {
            List<Product> deletedProducts = productRepo.findByDeletedTrue();

            // ⚡ Instead of throwing exception, just return empty list
            if (deletedProducts.isEmpty()) {
                log.info("No deleted products found");
                return List.of(); // return empty list
            }

            return deletedProducts.stream()
                    .map(ProductDto::fromEntity)
                    .toList();

        } catch (Exception e) {
            log.error("Unexpected error while retrieving deleted products: {}", e.getMessage(), e);
            throw new ProductApiException("An unexpected error occurred while fetching deleted products");
        }
    }


}
