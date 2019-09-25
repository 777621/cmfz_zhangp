package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {
    /**
     * @param albumId
     * @param page     分页
     * @param rows
     * @return
     */
    public List<Chapter> findAll(@Param("albumId") String albumId, @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * @return   总条数
     */
    public Integer totalcounts();

    /**
     * @param chapter  添加音频
     * @return
     */
    public void save(Chapter chapter);

    /**
     * @param chapter  修改音频
     */
    public void update(Chapter chapter);

    /**
     * @param id  删除
     */
    public void delete (String id);
}
