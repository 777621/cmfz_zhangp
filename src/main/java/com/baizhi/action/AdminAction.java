package com.baizhi.action;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    @ResponseBody
    public HashMap<String,Object> login(String code, String name, String password, HttpServletRequest request){
        //获取验证码
        String imageCode = (String) request.getSession().getAttribute("imageCode");
        HashMap<String,Object> map = new HashMap<>();
        //验证码比对
        Admin admin = adminService.login(name);
        if(imageCode.equals(code)){
            if(admin!=null){
                if(password.equals(admin.getPassword())){
                    request.getSession().setAttribute("admin",admin);
                    map.put("success","200");
                    map.put("message","登录成功");
                }else{
                    map.put("success","400");
                    map.put("message","密码错误");
                }
            }else{
                map.put("success","400");
                map.put("message","用户不存在");
            }
        }else{
            map.put("success","400");
            map.put("message","验证码错误");
        }

        return map;
    }
    @RequestMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response){
        //获取验证码的字符
        String code = ImageCodeUtil.getSecurityCode();
        //存储字符
        request.getSession().setAttribute("imageCode",code);
        //验证码字符生成图片
        BufferedImage image = ImageCodeUtil.createImage(code);
        //设置响应类型
        response.setContentType("image/png");
        //响应到页面
        try {
            ImageIO.write(image,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }
}
