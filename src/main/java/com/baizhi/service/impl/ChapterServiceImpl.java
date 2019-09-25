package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterDAO chapterDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String,Object> findAll(String albumId, Integer page, Integer rows) {
        List<Chapter> chapters = chapterDAO.findAll(albumId, page, rows);
        HashMap<String,Object> maps = new HashMap<String, Object>();
        maps.put("page",page);
        Integer totalcounts = chapterDAO.totalcounts();
        maps.put("records",totalcounts);
        Integer count;
        if(totalcounts%rows==0){
            count = totalcounts/rows;
        }else{
            count = totalcounts/rows+1;
        }
        maps.put("total",count);
        maps.put("rows",chapters);
        return maps;
    }

    @Override
    public String save(Chapter chapter) {
        String uuid = UUIDUtil.getUUID();
        chapter.setId(uuid);
        chapter.setUp_date(new Date());
        chapterDAO.save(chapter);
        return uuid;
    }

    @Override
    public void update(Chapter chapter) {
        chapterDAO.update(chapter);
    }

    @Override
    public void delete(String id) {
        chapterDAO.delete(id);
    }
    public void uploadChapter(MultipartFile url, String id, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/upload/audio");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = url.getOriginalFilename();
        //给文件加时间戳
        String name = new Date().getTime()+"-"+filename;
        try {
            url.transferTo(new File(realPath,name));
            //文件大小
            double size= (double)url.getSize();
            double d = size/1024/1024;
            DecimalFormat decimalFormat = new DecimalFormat();
            String s = decimalFormat.format(d)+"MB";
            //文件时长
            AudioFile audioFile = AudioFileIO.read(new File(realPath,name));
            int length = audioFile.getAudioHeader().getTrackLength();
            String duration=length/60+"分"+length%60+"秒";
            Chapter chapter = new Chapter();
            chapter.setId(id);
            System.out.println("3333333333333333333333"+id+"333333333333333333333333333");
            chapter.setUrl(name);
            chapter.setSize(s);
            chapter.setDuration(duration);
            chapterDAO.update(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response){
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/audio/");
            FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));
            response.setHeader("content-disposition", "Attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
