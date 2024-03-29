package com.example.task2628;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("useForm", new User());
        return "index";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("useForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("Error", "Пароли не совпадают");
            return "index";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("Error", "Пользователь с таким именем уже существует");
            return "index";
        }

        return "redirect:/";
    }
}
