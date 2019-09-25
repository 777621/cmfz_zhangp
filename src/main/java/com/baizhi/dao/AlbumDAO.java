package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {
    /**
     * @param page
     * @param rows  根据当前页   每页显示条数  查询所有
     * @return
     */
    public List<Album> findAll(@Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * @return  总条数
     */
    public Integer totalcounts();

    /**
     */
    public void save(Album album);


    /**
     * @param album  修改专辑
     */
    public void update(Album album);

    /**
     * @param id 删除专辑
     */
    public void delete(String id);
}
