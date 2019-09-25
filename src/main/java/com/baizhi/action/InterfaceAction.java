package com.baizhi.action;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class InterfaceAction {
    @Resource
    private BannerService bannerService;
    @Resource
    private AlbumService albumService;
    @Resource
    private ArticleService articleService;
    @RequestMapping("first_page")
    public HashMap<String,Object> first_page(String uid,String type,String sub_type){
        HashMap<String, Object> map = new HashMap<>();
        if(uid!=null){
            if(type.equals("all")){
                //首页展示所有
                HashMap<String, Object> banners = bannerService.findAll(1, 5);
                List<Banner> banner = (List<Banner>)banners.get("rows");
                map.put("banner",banner);
                HashMap<String, Object> albums = albumService.findAll(1, 6);
                List<Album> album = (List<Album>)albums.get("rows");
                map.put("album",album);
                HashMap<String, Object> articles = articleService.findAll(1, 2);
                List<Article> article = (List<Article>)articles.get("rows");
                map.put("article",article);

            }
            if(type.equals("wen")){
                //专辑数据
                HashMap<String, Object> albums = albumService.findAll(1, 6);
                List<Album> album = (List<Album>)albums.get("rows");
                map.put("album",album);
            }
            if(type.equals("si")){
                //文章数据
                if(sub_type!=null){
                    if(sub_type.equals("ssyj")){
                        //上师言教数据
                        HashMap<String, Object> articles = articleService.findAll(1, 2);
                        List<Article> article = (List<Article>)articles.get("rows");
                        map.put("article",article);
                    }else{
                        //显密法要数据
                        HashMap<String, Object> articles = articleService.findAll(1, 20);
                        List<Article> article = (List<Article>)articles.get("rows");
                        map.put("article",article);
                    }
                }
            }
        }
        return map;
    }
}

