package com.example.task2628;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ShippingService service;

    @RequestMapping("/")
    public String HomePage(Model model, @Param("keyword") String keyword,
                           @Param("sort_by") String sort_by, @Param("reverse") String reverse) {
        List<Cargo> list = service.ListAll(keyword, sort_by, reverse);

        model.addAttribute("List", list);
        model.addAttribute("keyword", keyword);

        return "index";
    }

    @RequestMapping("/fill")
    public String fillBook(Model model, @Param("n") String n) {
        if (n != null) {
            service.fillN(Integer.parseInt(n));
            return "redirect:/";
        }
        else {
            model.addAttribute("n", 30);
            return "fill";
        }
    }

    @RequestMapping("/truncate")
    public String truncateBook() {
        service.truncate();
        return "redirect:/";
    }

    @RequestMapping("/new")
    public String newBookForm(Model model) {
        Cargo cargo = new Cargo();
        model.addAttribute("cargo", cargo);
        return "create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("cargo") Cargo cargo) {
        service.add(cargo);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editBookForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit");
        Cargo cargo = service.get(id);
        mav.addObject("cargo", cargo);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Long id) {
        service.del(id);
        return "redirect:/";
    }
}
