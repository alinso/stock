package com.alinso.stock.controller;

import com.alinso.stock.common.Util;
import com.alinso.stock.dao.CategoryDao;
import com.alinso.stock.entity.Category;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
@RequestMapping("user/category")
public class CategoryController extends BaseController {

    @Autowired
    Auth auth;

    @Autowired
    CategoryDao categoryDao;


    @PostConstruct
    public void CategoryControllerPC(){
        super.setLinks("category");
        super.setTitles("Kategori");
        super.setTheClass(Category.class, categoryDao);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public String show(@PathVariable("id") int id,Model model) throws InstantiationException, IllegalAccessException {
        Category category = (Category) setEntity(id);
         model = setShowFormModel(model,category);
        return "user/category/form";
    }


    @RequestMapping(value="list")
    public String list(Model model){
        model  = setListModel(model);
        return "user/category/category_list";
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestAttribute int id, Model model ){
        return this.list(model);
    }

    @RequestMapping(value="save",method = RequestMethod.GET)
    public String saveGetRedirect(Model md) throws IllegalAccessException, InstantiationException {
        return this.show(0,md);
    }

    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Category category,BindingResult bindingResult,Model model) throws IllegalAccessException, InstantiationException {
        if(bindingResult.hasErrors()){
            return this.show(category.getId(),model);
        }

        category.setCreateUser(auth.getCurrentUser());
        try {
            categoryDao.saveOrUpdate(category);
            model.addAttribute("message",Util.saveSuccessMessage);
        }catch (Exception e){
            model.addAttribute("message",Util.saveErrorMessage);
        }
        model.addAttribute("title",createTitle);
        model.addAttribute("entity",new Category());

        return "user/category/form";
    }
}
