package com.iris.springbootmall.dao.impl;

import com.iris.springbootmall.dao.ProductDao;
import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.rowmapper.ProductRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ProductDaoImpl implements ProductDao {
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "select product_id, product_name, "
                + " category, image_url, price, stock, "
                + " description, created_date, "
                + " last_modified_date "
                + " from product "
                + " where product_id = :productId ";
        
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        
        return CollectionUtils.isEmpty(productList) ? null : productList.get(0);
    }

}
