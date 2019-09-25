package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.HashMap;


public interface AlbumService {
    public HashMap<String,Object> findAll(Integer page,Integer rows);
    public String save(Album album);
    public HashMap<String,Object> update(Album album);
    public void delete(String id);
}
