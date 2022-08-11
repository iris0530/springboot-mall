package com.iris.springbootmall.dto;

import com.iris.springbootmall.constant.ProductCategory;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    
    @NotNull
    private String productName;
    
    @NotNull
    private ProductCategory category;
    
    @NotNull
    private String imageUrl;
    
    @NotNull
    private Integer price;
    
    @NotNull
    private Integer stock;
    
    private String description;
}
