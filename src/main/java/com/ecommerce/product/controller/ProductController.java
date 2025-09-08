package com.ecommerce.product.controller;


import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public String createProduct(@RequestBody ProductDto productDto){
productDto.setId("ss");
            productService.createProduct(productDto);

        return "Product Created Successfully";
    }

    @GetMapping
    public List<ProductDto> getProduct(){
return         productService.getProducts();

    }

    // ✅ Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ✅ Soft delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // ✅ Restore product
    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreProduct(@PathVariable String id) {
        productService.restoreProductById(id);
        return ResponseEntity.ok().build(); // 200 OK
    }

    @GetMapping("/deleted-products")
    public ResponseEntity<List<ProductDto>> getDeletedProducts() {
        return ResponseEntity.ok(productService.getDeletedProducts());
    }


}
