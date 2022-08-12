package com.iris.springbootmall.service;

import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;
import java.util.List;

public interface ProductService {
    
    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(Integer productId, ProductRequestDTO productRequestDTO);
    
    void deleteProductById(Integer productId);
}
