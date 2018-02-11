package com.alinso.stock.controller;

import com.alinso.stock.dao.UserDAO;
import com.alinso.stock.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {
    @Autowired
    UserDAO userDAO;


    @GetMapping(value = "/register")
    public String Register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    //Very good name saveStudent :)
    public String saveStudent(@ModelAttribute User user, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       userDAO.saveOrUpdate(user);
       return null;
    }

}
