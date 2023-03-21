package com.yf.security.dao;

import com.yf.security.pojo.User;


public interface UserDao {
    User selectByUsername(String username);
}
