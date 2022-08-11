package com.iris.springbootmall.service.impl;

import com.iris.springbootmall.dao.ProductDao;
import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    @Transactional
    public Integer createProduct(ProductRequestDTO productRequestDTO) {
        return productDao.createProduct(productRequestDTO);
    }

    @Override
    @Transactional
    public void updateProduct(Integer productId, ProductRequestDTO productRequestDTO) {
        productDao.updateProduct(productId, productRequestDTO);  
    }

}
