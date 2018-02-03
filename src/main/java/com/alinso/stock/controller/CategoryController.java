package com.alinso.stock.controller;

import com.alinso.stock.dao.CategoryDao;
import com.alinso.stock.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user/category/")
public class CategoryController {
    @Autowired
    CategoryDao categoryDao;

    @GetMapping(value = "/{id}")
    public String showform(Model model, @PathVariable int id){
        Category category;
        String title  ="Kategori Düzenle";

           category = categoryDao.get(id);
        if(category == null){
            category  = new Category();
            title="Yeni Kategori Ekle";
        }

        model.addAttribute("category",category);
        model.addAttribute("title",title);
        return "admin/category/category_form";
    }


    @PostMapping(value = "save")
    public String save(@ModelAttribute Category category,Model model){
            model.addAttribute("title", "Kategori eklendi, Yeni Ekle");
            model.addAttribute("category",new Category());
            categoryDao.saveOrUpdate(category);
            return "admin/category/category_form";
    }
}
