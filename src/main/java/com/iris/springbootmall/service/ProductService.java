package com.iris.springbootmall.service;

import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(Integer productId, ProductRequestDTO productRequestDTO);
}
