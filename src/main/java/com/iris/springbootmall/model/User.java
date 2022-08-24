package com.iris.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.Data;

@Data
public class User {

    private Integer userId;
    
//    @JsonProperty("e_mail")
    private String email;
    
    @JsonIgnore
    private String password;
    
    private Date createdDate;
    private Date lastModifiedDate;
}
