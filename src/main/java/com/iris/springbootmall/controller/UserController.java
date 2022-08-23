package com.iris.springbootmall.controller;

import com.iris.springbootmall.dto.UserRgisterRequest;
import com.iris.springbootmall.model.User;
import com.iris.springbootmall.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRgisterRequest userRgisterRequest) {
        Integer userId = userService.register(userRgisterRequest);
        
        User user = userService.getUserById(userId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
