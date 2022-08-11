package com.iris.springbootmall.controller;

import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
