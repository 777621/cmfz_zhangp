package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Article {
    private String id;
    private String title;
    private String author;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    private String status;
    private String t_id;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", create_date=" + create_date +
                ", status='" + status + '\'' +
                ", t_id='" + t_id + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Article(String id, String title, String author, String content, Date create_date, String status, String t_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.create_date = create_date;
        this.status = status;
        this.t_id = t_id;
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }


    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(String id, String title, String author, String content, Date create_date, String t_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.create_date = create_date;
        this.t_id = t_id;
    }

    public Article() {
    }
}
