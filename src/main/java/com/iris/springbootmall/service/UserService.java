package com.iris.springbootmall.service;

import com.iris.springbootmall.dto.UserLoginRequest;
import com.iris.springbootmall.dto.UserRegisterRequest;
import com.iris.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRgisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}
