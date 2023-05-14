package com.example.task2628;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @RequestMapping("/admin/users")
    public String makeAdmin(Model model) {
        List<User> list = userService.allUsers();

        model.addAttribute("allUsers", list);

        return "manage-user";
    }

//    @RequestMapping("/admin/make-admin/{id}")
//    public String makeAdmin(@PathVariable(name = "id") Long id) {
//        User user = userService.findUserById(id);
//        userService.deleteUser(id);
//        userService.saveAdmin(user);
//        return "redirect:/admin/users";
//    }
//
//    @RequestMapping("/admin/make-user/{id}")
//    public String makeUser(@PathVariable(name = "id") Long id) {
//        User user = userService.findUserById(id);
//        userService.deleteUser(id);
//        userService.saveUser(user);
//        return "redirect:/admin/users";
//    }

    @RequestMapping("/admin/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-user");
        User user = userService.findUserById(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("user") User user) {
        List<Role> newRoles = user.getRoles().stream().toList();

        for (Role newRole : newRoles) {
            if (newRole == null || (!newRole.toString().equals("ROLE_ADMIN") && !newRole.toString().equals("ROLE_USER"))) {
                return "redirect:/admin/users";
            }
        }

        userService.save(user);
        return "redirect:/admin/users";
    }

//    @PostMapping("/admin")
//    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
//                              @RequestParam(required = true, defaultValue = "" ) String action,
//                              Model model) {
//        if (action.equals("delete")){
//            userService.deleteUser(userId);
//        }
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/gt/{userId}")
//    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.usergtList(userId));
//        return "admin";
//    }


}