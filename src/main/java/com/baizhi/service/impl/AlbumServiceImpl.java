package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumDAO albumDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> findAll(Integer page, Integer rows) {
        List<Album> albums = albumDAO.findAll(page, rows);
        HashMap<String,Object> maps = new HashMap<String, Object>();
        maps.put("page",page);  //当前页号
        Integer totalcounts = albumDAO.totalcounts();
        maps.put("records",totalcounts);    //总条数
        Integer count;
        if(totalcounts%rows==0){
            count = totalcounts/rows;
        }else{
            count = totalcounts/rows+1;
        }
        maps.put("total",count);   //总页数
        maps.put("rows",albums);   //遍历的所有值
        return maps;
    }

    @Override
    public String save(Album album) {
        String uuid = UUIDUtil.getUUID();
        album.setId(uuid);
        album.setPub_date(new Date());
        albumDAO.save(album);
        return uuid;
    }

    @Override
    public HashMap<String, Object> update(Album album) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        try{
            albumDAO.update(album);
            map.put("success","200");
            map.put("success","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","400");
            map.put("error","修改失败");
        }

        return map;
    }

    @Override
    public void delete(String id) {
        albumDAO.delete(id);
    }
}
