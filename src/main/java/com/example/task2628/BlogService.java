package com.example.task2628;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BlogService {
    @Autowired
    private BlogRepository rep;
    private final Random rnd = new Random();
    public List<Blog> listAll() {
        return rep.findAll();
    }

    public List<Blog> searchAll(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : null;

        if (keyword != null)
            return rep.search_all(keyword);

        return rep.findAll();
    }
    public List<Blog> searchDateName(String date, String name) {
        date = date != null ? date.toLowerCase() : null;
        name = name != null ? name.toLowerCase() : null;

        if (date != null && name != null)
            return rep.search_date_name(date, name);

        return rep.findAll();
    }
    public List<Blog> searchDateText(String date, String text) {
        date = date != null ? date.toLowerCase() : null;
        text = text != null ? text.toLowerCase() : null;

        if (date != null && text != null)
            return rep.search_date_text(date, text);

        return rep.findAll();
    }
    public List<Blog> searchText(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : null;

        if (keyword != null)
            return rep.search_text(keyword);

        return rep.findAll();
    }
    public List<Blog> searchDate(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : null;

        if (keyword != null)
            return rep.search_date(keyword);

        return rep.findAll();
    }
    public List<Blog> searchName(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : null;

        if (keyword != null)
            return rep.search_name(keyword);

        return rep.findAll();
    }

    public void add(Blog blog) {
        rep.save(blog);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public Blog get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }
}
