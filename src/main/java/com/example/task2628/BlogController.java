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
    public String admin_panel(Model model, @Param("date") String date, @Param("name") String name,
                              @Param("text") String text, @Param("all") String all, @Param("keyword") String keyword) {
        List<Blog> blogList = null;

        if (date != null && text != null) {
            blogList = blogService.searchDateText(date, text);
        } else if (date != null && name != null) {
            blogList = blogService.searchDateName(date, name);
        } else if (date != null) {
            blogList = blogService.searchDate(date);
        } else if (name != null) {
            blogList = blogService.searchName(name);
        } else if (text != null) {
            blogList = blogService.searchText(text);
        } else if (all != null) {
            blogList = blogService.searchAll(all);
        } else {
            blogList = blogService.listAll();
        }

        model.addAttribute("List", blogList);
        return "autoblog";
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

    @RequestMapping("/blog/truncate")
    public String truncateBook() {
        blogService.truncate();
        return "redirect:/";
    }

    @GetMapping("/auto-blog")
    public String autoblog(Model model, @Param("date") String date, @Param("name") String name,
                           @Param("text") String text, @Param("all") String all, @Param("keyword") String keyword) {
        List<Blog> blogList = null;

        if (date != null && text != null) {
            blogList = blogService.searchDateText(date, text);
        } else if (date != null && name != null) {
            blogList = blogService.searchDateName(date, name);
        } else if (date != null) {
            blogList = blogService.searchDate(date);
        } else if (name != null) {
            blogList = blogService.searchName(name);
        } else if (text != null) {
            blogList = blogService.searchText(text);
        } else if (all != null) {
            blogList = blogService.searchAll(all);
        } else {
            blogList = blogService.listAll();
        }

        model.addAttribute("List", blogList);
        return "blog";
    }

    @RequestMapping("/get-more/{id}")
    public ModelAndView getMoreBlog(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-blog");
        Blog blog = this.blogService.get(id);
        mav.addObject("blog", blog);
        return mav;
    }

}
