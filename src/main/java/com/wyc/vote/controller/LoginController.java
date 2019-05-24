package com.wyc.vote.controller;


import com.wyc.vote.entity.User;
import com.wyc.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("login")
    public String jump2Login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        return "login";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/vote/main";

    }


    @GetMapping("register")
    public String register() {
        return "register";

    }

    @GetMapping("error")
    public String error() {
        return "loginError";

    }
}
