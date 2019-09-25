package com.baizhi;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.baizhi.dao")
public class CmfzZhangpApplicationTests {
    @Autowired
    private AdminDAO adminDAO;
    @Test
    public void contextLoads() {
        Admin admin = adminDAO.login("小花花");
        System.out.println(admin);
    }

}
