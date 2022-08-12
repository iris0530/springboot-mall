package com.iris.springbootmall.controller;

import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.service.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        
        return ObjectUtils.isEmpty(product) ? 
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() : 
                    ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(
            @RequestBody @Valid ProductRequestDTO productRequestDTO){
        Integer productId = productService.createProduct(productRequestDTO);
        
        Product product = productService.getProductById(productId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, 
                                                 @RequestBody @Valid ProductRequestDTO productRequestDTO){
        Product product = productService.getProductById(productId);
        
        // 檢查 product 是否存在
        if(ObjectUtils.isEmpty(product)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        // 修改商品的數據
        productService.updateProduct(productId, productRequestDTO);
        
        Product updateProduct = productService.getProductById(productId);
        
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }
    
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
