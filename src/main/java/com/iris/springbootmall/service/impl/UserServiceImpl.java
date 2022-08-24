package com.iris.springbootmall.service.impl;

import com.iris.springbootmall.dao.UserDao;
import com.iris.springbootmall.dto.UserLoginRequest;
import com.iris.springbootmall.dto.UserRgisterRequest;
import com.iris.springbootmall.model.User;
import com.iris.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {
    
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    
    @Transactional
    @Override
    public Integer register(UserRgisterRequest userRgisterRequest) {
        // 檢查註冊的 email
        User user = userDao.getUserByEmail(userRgisterRequest.getEmail());
        
        if(null != user) {
            log.warn("該 email {} 已經被註冊", userRgisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        // 創建帳號
        return userDao.createUser(userRgisterRequest);
    }
    
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
       User user = userDao.getUserByEmail(userLoginRequest.getEmail());
       
       if(null == user) {
           log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
       
       if(user.getPassword().equals(userLoginRequest.getPassword())) {
           return user;
       } else {
           log.warn("該 email {} 的密碼不正確", userLoginRequest.getEmail());
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
    }
}
