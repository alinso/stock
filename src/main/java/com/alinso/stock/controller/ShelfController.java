package com.alinso.stock.controller;

import com.alinso.stock.dao.ShelfDao;
import com.alinso.stock.entity.Shelf;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by KHAN on 2.02.2018.
 */
@Controller
@RequestMapping("/user/shelf")
public class ShelfController {

    @Autowired
    Auth auth;

    @Autowired
    ShelfDao shelfDao;

    @RequestMapping
    public String shelfGet(Model model){
        model.addAttribute("title","Raf");
        model.addAttribute(new Shelf());
        return "admin/shelf/shelf_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String shelfPost(@Valid @ModelAttribute Shelf shelf, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "admin/shelf/shelf_form";
        }
        shelf.setCreateUser(auth.getCurrentUser());
        shelfDao.saveOrUpdate(shelf);
        model.addAttribute("shelf",shelf);
        return "admin/shelf/shelf_save_confirm";
    }

    @RequestMapping(value="list")
    public String getShelves(Model model){
        List<Shelf> shelves=shelfDao.getAll();
        model.addAttribute("shelves",shelves);
        return "admin/shelf/shelf_list";
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteShelf(@RequestAttribute int id, Model model ){
        return this.getShelves(model);
    }

    @RequestMapping(value="update/{id}",method=RequestMethod.GET)
    public String updateShelfPage(@PathVariable("id") int id,Model model){
        Shelf shelf=shelfDao.get(id);

        shelf.setUpdateUser(auth.getCurrentUser());
        model.addAttribute("shelfUpdated",shelf);
        return "admin/shelf/shelf_update";
    }

    @RequestMapping(value="update",method = RequestMethod.POST)
    public String updateShelf(@Valid @ModelAttribute Shelf shelf,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return this.updateShelfPage(shelf.getId(),model);
        }
        shelfDao.saveOrUpdate(shelf);
        model.addAttribute("shelf",shelf);
        return "admin/shelf/shelf_update_confirm";
    }


}
