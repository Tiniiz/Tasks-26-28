package com.example.task2628;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/admin_panel")
    public String admin_panel(Model model) {
        List<Blog> blogList = this.blogService.listAll();
        model.addAttribute("blogs", blogList);
        return "admin_panel";
    }

    @RequestMapping({"/blog/new"})
    public String newBookForm(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "create-blog";
    }

    @RequestMapping(
            value = {"/blog/save"},
            method = {RequestMethod.POST}
    )
    public String saveBook(@ModelAttribute("blog") Blog blog) {
        this.blogService.add(blog);
        return "redirect:/admin_panel";
    }

    @RequestMapping({"/blog/edit/{id}"})
    public ModelAndView editBlog(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-blog");
        Blog blog = this.blogService.get(id);
        mav.addObject("blog", blog);
        return mav;
    }

    @RequestMapping({"/blog/delete/{id}"})
    public String deleteBlog(@PathVariable(name = "id") Long id) {
        this.blogService.del(id);
        return "redirect:/admin_panel";
    }

    @RequestMapping({"/blog/search"})
    public String search(Model model, @Param("search") String search,
                         @Param("keyword") String keyword ) {
        List<Blog> blogList = null;
        if (Objects.equals(search, "Поиск по дате и названию")) {blogList = this.blogService.searchNameData(keyword);}
        else if (Objects.equals(search, "Поиск по тексту записи")) {blogList = this.blogService.searchText(keyword);}
        else if (Objects.equals(search, "Поиск по дате и тексту")) {blogList = this.blogService.searchDateText(keyword);}
        else if (Objects.equals(search, "Поиск по всем критериям")) {blogList = this.blogService.searchAll(keyword);}
        else if (Objects.equals(search, "Поиск по названию")) {blogList = this.blogService.searchName(keyword);}
        else if (Objects.equals(search, "Поиск по дате")) {blogList = this.blogService.searchDate(keyword);}
        else if (Objects.equals(search, "Поиск по тексту и названию")) {blogList = this.blogService.searchNameText(keyword);}
        model.addAttribute("blogs", blogList);
        return "admin_panel";
    }

    @ResponseBody
    @GetMapping("/blog/findall")
    public List<Blog> findall() {
        return  blogService.listAll();
    }

    @GetMapping("/autoblog")
    public String autoblog() {
        return  "autoblog";
    }


}
