package com.alinso.stock.controller;

import com.alinso.stock.dao.CategoryDao;
import com.alinso.stock.entity.Category;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user/category")
public class CategoryController {

    @Autowired
    Auth auth;

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping
    public String categoryGet(Model model){
        model.addAttribute("title","Kategori");
        model.addAttribute(new Category());
        return "admin/category/category_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String categoryPost(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "admin/category/category_form";
        }
        category.setCreateUser(auth.getCurrentUser());
        categoryDao.saveOrUpdate(category);
        model.addAttribute("category",category);
        return "admin/category/category_save_confirm";
    }

    @RequestMapping(value="list")
    public String getCategories(Model model){
        List<Category> categories=categoryDao.getAll();
        model.addAttribute("categories",categories);
        return "admin/category/category_list";
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteCategory(@RequestAttribute int id, Model model ){
        return this.getCategories(model);
    }

    @RequestMapping(value="update/{id}",method=RequestMethod.GET)
    public String updateCategoryPage(@PathVariable("id") int id,Model model){
        Category category=categoryDao.get(id);
        model.addAttribute("categoryUpdated",category);
        return "admin/category/category_update";
    }

    @RequestMapping(value="update",method = RequestMethod.POST)
    public String updateCategory(@Valid @ModelAttribute Category category,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return this.updateCategoryPage(category.getId(),model);
        }

        category.setUpdateUser(auth.getCurrentUser());
        categoryDao.saveOrUpdate(category);
        model.addAttribute("category",category);
        return "admin/category/category_update_confirm";
    }
}
