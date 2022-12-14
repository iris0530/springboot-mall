package com.iris.springbootmall.dao;

import com.iris.springbootmall.dto.ProductQueryParams;
import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;
import java.util.List;

public interface ProductDao {
    
    Integer countProduct(ProductQueryParams productQueryParams);
    
    List<Product> getProducts(ProductQueryParams productQueryParams);
    
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(Integer productId, ProductRequestDTO productRequestDTO);

    void deleteProductById(Integer productId);

    void updateStock(Integer productId, Integer stock);
}
