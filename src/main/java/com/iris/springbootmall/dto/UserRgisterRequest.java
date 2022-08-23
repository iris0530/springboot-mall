package com.iris.springbootmall.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRgisterRequest {

    @NotBlank
    private String email;
    
    @NotBlank
    private String password;
}
