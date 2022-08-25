package com.iris.springbootmall.model;

import java.util.Date;
import lombok.Data;

@Data
public class Order {

    private Integer orderId;
    private Integer userId;
    private Integer totalAmount;
    private Date createdDate;
    private Date lastModifiedDate;
}
