package com.iris.springbootmall.service;

import com.iris.springbootmall.dto.UserLoginRequest;
import com.iris.springbootmall.dto.UserRgisterRequest;
import com.iris.springbootmall.model.User;

public interface UserService {

    Integer register(UserRgisterRequest userRgisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}
