package com.example.task2628;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository rep;
    public List<Blog> listAll() {
        return rep.findAll();
    }
    public List<Blog> searchAll(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

        if (keyword != null)
            return rep.search_all(keyword);

        return rep.findAll();
    }
    public List<Blog> searchNameText(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

        if (keyword != null)
            return rep.search_name_text(keyword);

        return rep.findAll();
    }
    public List<Blog> searchNameData(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

        if (keyword != null)
            return rep.search_name_date(keyword);

        return rep.findAll();
    }
    public List<Blog> searchDateText(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

        if (keyword != null)
            return rep.search_date_text(keyword);

        return rep.findAll();
    }
    public List<Blog> searchText(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

        if (keyword != null)
            return rep.search_text(keyword);

        return rep.findAll();
    }
    public List<Blog> searchDate(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

        if (keyword != null)
            return rep.search_date(keyword);

        return rep.findAll();
    }
    public List<Blog> searchName(String keyword) {
        keyword = keyword != null ? keyword.toLowerCase() : keyword;

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
}
