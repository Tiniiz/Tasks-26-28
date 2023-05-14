package com.example.task2628;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private String name;
    private String date;
    private String blogText;
    private String author;


    @Override
    public String toString() {
        return "blog{" +
                "id=" + id +
                "url=" + url +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", blogText='" + blogText + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlog_text() {
        return blogText;
    }

    public void setBlog_text(String blog_text) {
        this.blogText = blog_text;
    }

    public static ArrayList<String> numFields() {
        ArrayList<String> res = new ArrayList<>();
        res.add("id");
        res.add("name");
        res.add("content");
        res.add("citySend");
        res.add("dateSend");
        res.add("cityArrive");
        res.add("dateArrive");

        return res;
    }

}
