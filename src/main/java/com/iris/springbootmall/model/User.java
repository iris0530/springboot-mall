package com.iris.springbootmall.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {

    private Integer userId;
    private String email;
    private String password;
    private Date createdDate;
    private Date lastModifiedDate;
}
