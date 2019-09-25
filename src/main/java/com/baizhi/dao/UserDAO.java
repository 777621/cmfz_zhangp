package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> findAll(Integer page, Integer rows);
    public Integer totalcounts();
    public void update(User user);
    public List<User> showAll();
}
