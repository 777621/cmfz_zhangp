package com.baizhi.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HashMap<String, Object> findAll(Integer page, Integer rows) {
        List<User> users = userDAO.findAll(page, rows);
        HashMap<String, Object> maps = new HashMap<String, Object>();
        maps.put("page", page);  //当前页号
        Integer totalcounts = userDAO.totalcounts();
        maps.put("records", totalcounts);    //总条数
        Integer count;
        if (totalcounts % rows == 0) {
            count = totalcounts / rows;
        } else {
            count = totalcounts / rows + 1;
        }
        maps.put("total", count);   //总页数
        maps.put("rows", users);   //遍历的所有值
        return maps;
    }

    @Override
    public HashMap<String, Object> updateStatus(User user) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            userDAO.update(user);
            map.put("success", "200");
            map.put("success", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "400");
            map.put("error", "修改失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> showAll(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        List<User> users = userDAO.showAll();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo/");
        for (User user : users) {
            user.setCover(realPath + user.getCover());
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息表", "用户", "测试"), User.class, users);
        try {
            workbook.write(new FileOutputStream(new File("D:/user.xls")));
            workbook.close();
            map.put("error", 200);
            map.put("message", "成功");
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 400);
            map.put("message", "失败");
        }
        return map;
    }
}
