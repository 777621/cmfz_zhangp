package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.HashMap;
import java.util.List;

public interface BannerService {
    /**
     * @param page
     * @param rows  分页查询所有
     * @return
     */
    public HashMap<String,Object> findAll(Integer page, Integer rows);

    /**
     * @return  总条数
     */
    public Integer totalcounts();

    /**
     * @param banner 添加轮播图
     */
    public String add(Banner banner);

    /**
     * @param id  删除轮播图
     */
    public void delete(String id);

    /**
     * @param banner 修改轮播图
     */
    public HashMap<String,Object> updateStatus(Banner banner);
}
