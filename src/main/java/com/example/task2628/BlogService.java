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

    public void fillN(int n) {

        String[] name = {"Пшеница",
                "Рожь",
                "Овес",
                "Ячмень",
                "Зерно кукурузы",
                "Початки кукурузы",
                "Рис нешелушеный",
                "Рис шелушеный (неполированный рис)",
                "Прочие зерновые",
                "Гречиха",
                "Зерно бобов",
                "Зерно гороха",
                "Зерно фасоли",
                "Нут",
                "Полба",
                "Просо",
                "Солод в зерне"};

        String[] authors = {"Maru26", "Va_iwm", "Root", "Op_pow", "Glep90", "Sad_boY", "Fifi13", "Four1", "Joy_di_ko2"};

        List<Blog> all = new ArrayList<>();

        for (int i = 0; i < n; i ++) {

            Blog blog = new Blog();
            blog.setName(name[rnd.nextInt(0, name.length)]);
            blog.setDate("2023-03-%02d".formatted(rnd.nextInt(1, 32)));
            blog.setAuthor(authors[rnd.nextInt(0, authors.length)]);
            blog.setBlog_text("Зерновые культуры");

            all.add(blog);
        }

        rep.saveAll(all);
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
