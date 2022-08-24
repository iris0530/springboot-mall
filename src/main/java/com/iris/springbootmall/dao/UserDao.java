package com.iris.springbootmall.dao;

import com.iris.springbootmall.dto.UserRgisterRequest;
import com.iris.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRgisterRequest userRgisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
