package com.iris.springbootmall.dao;

import com.iris.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
