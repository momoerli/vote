package com.wyc.vote.controller;

import com.wyc.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Object userInfo(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("{id}")
    public String selectUser (@PathVariable int id){
        return userService.selectUser(id).toString();
    }
}
