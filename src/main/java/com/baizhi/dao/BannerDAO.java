package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {
    /**
     * @param page 页号
     * @param rows 每页显示条数
     * @return     展示所有
     */
    public List<Banner> findAll(@Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * @return   总页数
     */
    public Integer totalcounts();

    /**
     * @param banner 添加轮播图
     */
    public void add(Banner banner);

    /**
     * @param id  删除轮播图
     */
    public void delete(String id);

    /**
     * @param banner  修改轮播图
     */
    public void updateStatus(Banner banner);

}
