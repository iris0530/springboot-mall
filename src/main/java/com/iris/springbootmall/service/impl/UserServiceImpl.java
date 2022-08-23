package com.iris.springbootmall.service.impl;

import com.iris.springbootmall.dao.UserDao;
import com.iris.springbootmall.dto.UserRgisterRequest;
import com.iris.springbootmall.model.User;
import com.iris.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Transactional
    @Override
    public Integer register(UserRgisterRequest userRgisterRequest) {
        return userDao.createUser(userRgisterRequest);
    }
    
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
