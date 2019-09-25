package com.baizhi.action;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerAction {
    @Resource
    private BannerService bannerService;
    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page, Integer rows){
        HashMap<String, Object> map = bannerService.findAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public String edit(Banner banner, String oper){
        String id = null;
        if("add".equals(oper)){
            id = bannerService.add(banner);
            System.out.println("1111111111111"+id+"1111111111111111111111");
        }else if("del".equals(oper)){
            bannerService.delete(banner.getId());
        }else if("edit".equals(oper)){
            bannerService.updateStatus(banner);
        }
        return id;
    }
    @RequestMapping("bannerUpload")
    public void bannerUpload(MultipartFile img_path, String id, HttpServletRequest request){
        System.out.println("22222222222222222"+id+"22222222222222222222");
        //根据相对路径获取结对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo/");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取文件名
        String filename = img_path.getOriginalFilename();
        //文件上传
        //给文件加时间戳
        String name = new Date().getTime()+"-"+filename;
        try {
            img_path.transferTo(new File(realPath,name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改文件路径
        Banner banner = new Banner();
        banner.setId(id);
        banner.setImg_path(name);
        bannerService.updateStatus(banner);

    }
    @RequestMapping("updateStatus")
    public HashMap<String,Object> updateStatus(Banner banner){
        HashMap<String, Object> map = bannerService.updateStatus(banner);
        return map;
    }
}
