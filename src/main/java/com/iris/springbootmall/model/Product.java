package com.iris.springbootmall.model;

import java.util.Date;
import lombok.Data;


@Data
public class Product {

    private Integer productId;
    private String productName;
    private String category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createdTime;
    private Date lastModifiiedDate;
}
