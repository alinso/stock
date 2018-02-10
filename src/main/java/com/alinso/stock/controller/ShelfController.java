package com.alinso.stock.controller;

import com.alinso.stock.common.Util;
import com.alinso.stock.dao.ShelfDao;
import com.alinso.stock.entity.Shelf;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by KHAN on 2.02.2018.
 */
@Controller
@RequestMapping("/user/shelf")
public class ShelfController extends BaseController {

    @Autowired
    Auth auth;

    @Autowired
    ShelfDao shelfDao;

    @PostConstruct
    public void ShelfControllerPC(){
        super.setLinks("shelf");
        super.setTitles("Raf");
        super.setTheClass(Shelf.class,shelfDao);
    }


    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public String show(@PathVariable("id") int id,Model model) throws InstantiationException, IllegalAccessException {
        Shelf shelf = (Shelf) setEntity(id);
        model = setShowFormModel(model,shelf);
        return "user/shelf/form";
    }


    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Shelf shelf,BindingResult bindingResult,Model model) throws IllegalAccessException, InstantiationException {
        if(bindingResult.hasErrors()){
            return this.show(shelf.getId(),model);
        }

        shelf.setCreateUser(auth.getCurrentUser());

        try {
            shelfDao.saveOrUpdate(shelf);
            model.addAttribute("message",Util.saveSuccessMessage);
        }catch (Exception e){
            model.addAttribute("message",Util.saveErrorMessage);
        }
        model.addAttribute("title",createTitle);


        return this.show(0,model);
    }

    @RequestMapping(value="list")
    public String list(Model model){
        model  = setListModel(model);
        return "user/shelf/shelf_list";
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteShelf(@RequestAttribute int id, Model model ) throws IllegalAccessException, InstantiationException {
        return this.show(0,model);
    }

    @RequestMapping(value="save",method = RequestMethod.GET)
    public String saveGetRedirect(Model md) throws IllegalAccessException, InstantiationException {
        return this.show(0,md);
    }


}
