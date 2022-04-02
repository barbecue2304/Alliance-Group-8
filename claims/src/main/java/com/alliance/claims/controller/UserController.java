package com.alliance.claims.controller;

import com.alliance.claims.entity.User;
import com.alliance.claims.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ModelAndView addUserController(@ModelAttribute User user) {

        ModelAndView modelAndView = new ModelAndView();
        User addUser = userService.addUser(user);

        modelAndView.addObject("user", addUser);
        modelAndView.setViewName("indexPage");

        return modelAndView;
    }

    @GetMapping("/")
    public String indexPage() {

        return "register";

    }
}
