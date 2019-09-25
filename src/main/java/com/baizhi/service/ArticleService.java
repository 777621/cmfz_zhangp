package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.HashMap;

public interface ArticleService {
    public HashMap<String,Object> findAll(Integer page, Integer rows);
    public void delete(String id);
    public void save(Article article);
    public void update(Article article);
}
