package com.baizhi.entity;
/*
第一次拉取项目修改代码
i love you
i love you too
第一次在本地项目中修改代码
*/
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    @Excel(name="ID")
    private String id;
    @Excel(name="封面",type=2)
    private String cover;
    @Excel(name="用户名")
    private String name;
    @Excel(name="用户昵称")
    private String nikename;
    @Excel(name="密码")
    private String password;
    @Excel(name="性别")
    private String sex;
    @Excel(name="状态")
    private String status;
    @Excel(name="手机号")
    private String phone;
    @Excel(name="创建日期",format = "yyyy-MM-dd")
    private Date create_date;
    @Excel(name="地址")
    private String address;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", create_date=" + create_date +
                ", address='" + address + '\'' +
                '}';
    }

    public User(String id, String cover, String name, String nikename, String password, String sex, String status, String phone, Date create_date, String address) {
        this.id = id;
        this.cover = cover;
        this.name = name;
        this.nikename = nikename;
        this.password = password;
        this.sex = sex;
        this.status = status;
        this.phone = phone;
        this.create_date = create_date;
        this.address = address;
    }
    public User() {
    }
}
