package com.iris.springbootmall.dao;

import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(Integer productId, ProductRequestDTO productRequestDTO);
}
