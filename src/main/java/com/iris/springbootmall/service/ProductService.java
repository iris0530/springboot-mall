package com.iris.springbootmall.service;

import com.iris.springbootmall.dto.ProductQueryParams;
import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;
import java.util.List;

public interface ProductService {
    
    Integer countProduct(ProductQueryParams productQueryParams);
    
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(Integer productId, ProductRequestDTO productRequestDTO);
    
    void deleteProductById(Integer productId);
}
