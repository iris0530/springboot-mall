package com.iris.springbootmall.dao.impl;

import com.iris.springbootmall.dao.UserDao;
import com.iris.springbootmall.dto.UserRegisterRequest;
import com.iris.springbootmall.model.User;
import com.iris.springbootmall.rowmapper.UserRowMapper;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = " INSERT INTO user(email, password, created_date, last_modified_date) "
                + " VALUES (:email, :password, :createdDate, :lastModifiedDate) ";
        
        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        
        map.put("createdDate", LocalDateTime.now());
        map.put("lastModifiedDate", LocalDateTime.now());
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        
        return keyHolder.getKey().intValue();
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date "
                + " FROM user "
                + " WHERE user.user_id = :userId ";
        
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date "
                + " FROM user "
                + " WHERE email = :email ";
        
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }
}
