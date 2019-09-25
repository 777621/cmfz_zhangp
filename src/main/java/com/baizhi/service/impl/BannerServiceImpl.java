package com.baizhi.service.impl;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDAO bannerDAO;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> findAll(Integer page, Integer rows) {
        List<Banner> banners = bannerDAO.findAll(page, rows);
        HashMap<String,Object> maps = new HashMap<String, Object>();
        maps.put("page",page); //当前页号
        Integer totalcounts = bannerDAO.totalcounts();
        maps.put("records",totalcounts);  //总条数
        Integer count;
        if(totalcounts%rows==0){
            count=totalcounts/rows;
        }else{
            count=totalcounts/rows+1;
        }
        maps.put("total",count);    //总页数
        maps.put("rows",banners);
        return maps;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Integer totalcounts() {
        Integer totalcounts = bannerDAO.totalcounts();
        return totalcounts;
    }

    @Override
    public String add(Banner banner) {
        String uuid = UUIDUtil.getUUID();
        banner.setId(uuid);
        banner.setStatus("2");
        banner.setUp_date(new Date());
        bannerDAO.add(banner);
        return uuid;
    }

    @Override
    public void delete(String id) {
        bannerDAO.delete(id);
    }

    @Override
    public HashMap<String,Object> updateStatus(Banner banner) {
        HashMap<String,Object> map = new HashMap<>();
        try {
            bannerDAO.updateStatus(banner);
            map.put("success","200");
            map.put("success","修改成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("error","400");
            map.put("error","修改失败");
        }
        return map;
    }
}
