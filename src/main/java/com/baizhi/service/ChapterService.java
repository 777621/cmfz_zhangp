package com.baizhi.service;


import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


public interface ChapterService {
    public HashMap<String,Object> findAll(@Param("albumId") String albumId, @Param("page") Integer page, @Param("rows") Integer rows);
    public String save(Chapter chapter);
    public void update(Chapter chapter);
    public void delete(String id);
    public void uploadChapter(MultipartFile url, String id, HttpServletRequest request);
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response);
}
