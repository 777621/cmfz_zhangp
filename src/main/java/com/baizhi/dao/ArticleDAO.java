package com.baizhi.dao;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleDAO {
    public List<Article> findAll(Integer page, Integer rows);
    public Integer totalcounts();
    public void delete(String id);
    public void save(Article article);
    public void update(Article article);
}
