package com.hunter.springbootpostgresql.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//to manage thymeleaf templates, use @controller annotation
@Controller
@RequestMapping("/")
public class TempletesController {

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("main")
    public String getMainView() {
        return "main";
    }
}
