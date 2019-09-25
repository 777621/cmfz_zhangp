package com.baizhi.action;


import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumAction {
    @Resource
    private AlbumService albumService;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String,Object> findAll(Integer page,Integer rows){
        HashMap<String, Object> map = albumService.findAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Album album,String oper){
        String id = null;
        if("add".equals(oper)){
            id = albumService.save(album);
            System.out.println("------------------------"+id+"------------------------------------------------");
        }else if("edit".equals(oper)){
            albumService.update(album);
        }else if("del".equals(oper)){
            albumService.delete(album.getId());
        }
        return id;
    }
    @RequestMapping("albumUpload")
    public void albumUpload(MultipartFile cover_img, String id, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("upload/photo");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = cover_img.getOriginalFilename();
        String name = new Date().getTime()+"-"+filename;
        try {
            cover_img.transferTo(new File(realPath,name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Album album = new Album();
        album.setId(id);
        album.setCover_img(name);
        albumService.update(album);
        System.out.println(album);
    }
}
