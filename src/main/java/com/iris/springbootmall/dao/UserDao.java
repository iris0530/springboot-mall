package com.iris.springbootmall.dao;

import com.iris.springbootmall.dto.UserRegisterRequest;
import com.iris.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
