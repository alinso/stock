package com.alinso.stock.controller;

/**
 * Created by KHAN on 10.02.2018.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping
    String login(){
        return "login";
    }
}
