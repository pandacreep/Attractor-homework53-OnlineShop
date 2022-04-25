package com.pandacreep.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OnlineShopController {
    @GetMapping
    public String root() {
        return "index";
    }
}
