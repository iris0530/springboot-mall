package com.iris.springbootmall.dao.impl;

import com.iris.springbootmall.dao.ProductDao;
import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.rowmapper.ProductRowMapper;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    @Override
    public Integer createProduct(ProductRequestDTO productRequestDTO) {
        String sql = "INSERT INTO product ( "
                + " product_name, category, image_url, "
                + " price, stock, description, created_date, "
                + " last_modified_date) VALUES (:productName,"
                + " :category, :imageUrl, :price, :stock, "
                + " :description, :createdDate, :lastModifiedDate)";
        
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequestDTO.getProductName());
        map.put("category", productRequestDTO.getCategory().toString());
        map.put("imageUrl", productRequestDTO.getImageUrl());
        map.put("price", productRequestDTO.getPrice());
        map.put("stock", productRequestDTO.getStock());
        map.put("description", productRequestDTO.getDescription());
        map.put("createdDate", LocalDateTime.now());
        map.put("lastModifiedDate", LocalDateTime.now());
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateProduct(Integer productId, ProductRequestDTO productRequestDTO) {
        String sql = "update product set product_name = :productName, "
                + " category = :category, image_url = :imageUrl, "
                + " price = :price, stock = :stock, "
                + " description = :description, last_modified_date = :lastModifiedDate "
                + " where product_id = :productId ";
        
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        
        map.put("productName", productRequestDTO.getProductName());
        map.put("category", productRequestDTO.getCategory().toString());
        map.put("imageUrl", productRequestDTO.getImageUrl());
        map.put("price", productRequestDTO.getPrice());
        map.put("stock", productRequestDTO.getStock());
        map.put("description", productRequestDTO.getDescription());
        
        map.put("lastModifiedDate", LocalDateTime.now());
        
        namedParameterJdbcTemplate.update(sql, map);
    }

}
