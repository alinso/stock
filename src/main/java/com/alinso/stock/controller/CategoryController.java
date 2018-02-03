package com.alinso.stock.controller;

import com.alinso.stock.dao.CategoryDao;
import com.alinso.stock.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {
    @Autowired
    CategoryDao categoryDao;

    @GetMapping(value = "admin/category/{id}")
    public String showform(Model model, @PathVariable int id){

        Category category  = categoryDao.get(id);
        if(category==null){
            category  = new Category();
        }

        model.addAttribute("category",category);
        return "admin/category_form";
    }
}
