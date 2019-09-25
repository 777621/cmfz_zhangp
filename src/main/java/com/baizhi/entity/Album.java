package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Album {
    private String id;
    private String title;
    private Double score;
    private String author;
    private String broadcast;
    private Integer count;
    private String brief;
    private String cover_img;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pub_date;

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", author='" + author + '\'' +
                ", broadcast='" + broadcast + '\'' +
                ", count=" + count +
                ", brief='" + brief + '\'' +
                ", cover_img='" + cover_img + '\'' +
                ", pub_date=" + pub_date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public Album(String id, String title, Double score, String author, String broadcast, Integer count, String brief, String cover_img, Date pub_date) {
        this.id = id;
        this.title = title;
        this.score = score;
        this.author = author;
        this.broadcast = broadcast;
        this.count = count;
        this.brief = brief;
        this.cover_img = cover_img;
        this.pub_date = pub_date;
    }

    public Album() {
    }
}
