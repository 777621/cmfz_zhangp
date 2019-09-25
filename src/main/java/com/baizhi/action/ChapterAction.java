package com.baizhi.action;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/chapter")
public class ChapterAction {
    @Resource
    private ChapterService chapterService;
    @RequestMapping("findAll")
    @ResponseBody
    public HashMap<String,Object> fndAll(String albumId, Integer page, Integer rows){
        HashMap<String, Object> map = chapterService.findAll(albumId, page, rows);
        return map;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(String albumId,Chapter chapter,String oper){
        String id = null;
        if("add".equals(oper)){
            chapter.setAlbum_id(albumId);
            id = chapterService.save(chapter);
        }else if("edit".equals(oper)){
            chapterService.update(chapter);
        }else if("del".equals(oper)){
            chapterService.delete(chapter.getId());
        }
        System.out.println("111111111111111111111111111"+id+"1111111111111111111111111");
        return id;
    }
    @RequestMapping("/uploadChapter")
    @ResponseBody
    public void uploadChapter(MultipartFile url, String id, HttpServletRequest request){
        System.out.println("222222222222222222222222222"+id+"2222222222222222222222222222222");
        chapterService.uploadChapter(url, id, request);
    }
    @RequestMapping("download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response){
        chapterService.download(fileName, request, response);
    }
}
