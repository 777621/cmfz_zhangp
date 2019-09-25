package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    public HashMap<String,Object> findAll(Integer page, Integer rows);
    public HashMap<String,Object> updateStatus(User user);
    public HashMap<String,Object>  showAll(HttpServletRequest request);
}
