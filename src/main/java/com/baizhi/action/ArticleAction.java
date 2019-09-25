package com.baizhi.action;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("article")
public class ArticleAction {
    @Resource
    private ArticleService articleService;
    @RequestMapping("findAll")
    public HashMap<String,Object> findAll(Integer page,Integer rows){
        HashMap<String, Object> map = articleService.findAll(page, rows);
        return map;
    }
    @RequestMapping("add")
    public HashMap<String,Object> add(Article article,String oper){
        HashMap<String, Object> map = new HashMap<>();
        articleService.save(article);
        map.put("success","200");
        map.put("message","添加成功");
        return map;
    }
    @RequestMapping("update")
    public void update(Article article){
        articleService.update(article);
    }
    @RequestMapping("delete")
    public HashMap<String,Object> delete(String id){
        HashMap<String, Object> map = new HashMap<>();
        articleService.delete(id);
        map.put("success","200");
        map.put("message","删除成功");
        return map;
    }
    @RequestMapping("upload")
    public HashMap<String, Object> upload(MultipartFile photo, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/article");
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdirs();
            }
            String filename = photo.getOriginalFilename();
            String name = new Date().getTime()+"_"+filename;
            photo.transferTo(new File(realPath,name));
            String scheme = request.getScheme();  //获取http
            String serverName = request.getServerName();//获取localhost
            int serverPort = request.getServerPort();   //获取端口号
            String contextPath = request.getContextPath();  //获取项目名
            String url = scheme+"://"+serverName+":"+serverPort+contextPath+"/upload/article/"+name;
            map.put("error",0);
            map.put("url",url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("message","上传失败");
        }
        return map;
    }
    //浏览远程图片
    @RequestMapping("queryPhoto")
    public HashMap<String, Object> queryPhoto(HttpServletRequest request){
        HashMap<String, Object> maps = new HashMap<>();
        ArrayList<Object> lists = new ArrayList<>();
        String realPath = request.getSession().getServletContext().getRealPath("upload/article");
        File file = new File(realPath);
        String[] names = file.list();
        for (int i = 0;i<names.length;i++) {
            String name = names[i];
            HashMap<String, Object> map = new HashMap<>();
            map.put("is_dir",false);  //是否是文件夹
            map.put("has_file",false);  //是否有文件
            File file1 = new File(realPath, name);
            map.put("filesize",file1.length());  //文件大小
            map.put("is_photo",true);   //是否是图片
            String extension = FilenameUtils.getExtension(name);
            map.put("filetype",extension);    //图片类型
            map.put("filename",name);    //图片名称
            String[] s = name.split("_");
            String times = s[0];
            long time = Long.parseLong(times);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = dateFormat.format(time);
            map.put("datetime",format);
            lists.add(map);
        }
        maps.put("current_url","http://localhost:8989/cmfz_zhangp/upload/article/");
        maps.put("total_count",lists.size());
        maps.put("file_list",lists);
        return maps;
    }
}
