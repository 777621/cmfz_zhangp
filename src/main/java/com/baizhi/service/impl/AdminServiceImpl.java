package com.baizhi.service.impl;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;

import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Admin login(String name) {
        Admin admin = adminDAO.login(name);
        return admin;
    }
}
