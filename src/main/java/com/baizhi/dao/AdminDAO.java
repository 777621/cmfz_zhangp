package com.baizhi.dao;

import com.baizhi.entity.Admin;


public interface AdminDAO {
    /**
     * @param name  后台管理员登录
     * @return
     */
    public Admin login(String name);
}
