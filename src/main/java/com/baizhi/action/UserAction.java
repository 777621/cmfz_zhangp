package com.baizhi.action;


import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.PhoneMsgUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@RequestMapping("user")
public class UserAction {
    @Resource
    private UserService userService;
    @RequestMapping("findAll")
    public HashMap<String,Object> findAll(Integer page,Integer rows){
        HashMap<String, Object> map = userService.findAll(page, rows);
        return map;
    }
    @RequestMapping("updateStatus")
    public HashMap<String,Object> updateStatus(User user){
        HashMap<String, Object> map = userService.updateStatus(user);
        return map;
    }
    @RequestMapping("showAll")
    public HashMap<String,Object> showAll(HttpServletRequest request){
        System.out.println("111111111111111111111111111111111111");
        HashMap<String, Object> map = userService.showAll(request);
        return map;
    }
    /*
    @RequestMapping("phone")
    public void phone(String phone){
        String random = PhoneMsgUtil.getRandom(6);
        PhoneMsgUtil.getPhone(phone,random);
    }*/
}
