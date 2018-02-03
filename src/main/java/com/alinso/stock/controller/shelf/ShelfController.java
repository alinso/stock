package com.alinso.stock.controller.shelf;

import com.alinso.stock.dao.shelf.ShelfDao;
import com.alinso.stock.entity.shelf.Shelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by KHAN on 2.02.2018.
 */
@Controller
public class ShelfController {

    @Autowired
    ShelfService shelfService;

    @RequestMapping("/deneme")
    public String shelfGet(Model model){
        model.addAttribute(new Shelf());
        return "deneme";
    }

}
