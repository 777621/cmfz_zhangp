package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import com.baizhi.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Resource
    private ArticleDAO articleDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> findAll(Integer page, Integer rows) {
        List<Article> articles = articleDAO.findAll(page, rows);
        HashMap<String,Object> maps = new HashMap<String, Object>();
        maps.put("page",page);  //当前页号
        Integer totalcounts = articleDAO.totalcounts();
        maps.put("records",totalcounts);    //总条数
        Integer count;
        if(totalcounts%rows==0){
            count = totalcounts/rows;
        }else{
            count = totalcounts/rows+1;
        }
        maps.put("total",count);   //总页数
        maps.put("rows",articles);   //遍历的所有值
        return maps;
    }

    @Override
    public void delete(String id) {
        articleDAO.delete(id);
    }

    @Override
    public void save(Article article) {
        String uuid = UUIDUtil.getUUID();
        article.setId(uuid);
        article.setCreate_date(new Date());
        article.setStatus("2");
        article.setT_id("公共");
        articleDAO.save(article);

    }

    @Override
    public void update(Article article) {
        articleDAO.update(article);
    }
}
