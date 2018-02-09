package com.alinso.stock.controller;

import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by KHAN on 9.02.2018.
 */
@Controller
@RequestMapping("user/home")
public class HomeController {

    @Autowired
    Auth auth;

    @RequestMapping
    String getHome(Model model){
        model.addAttribute(auth.getCurrentUser());
        return "user/home/home";
    }

}
