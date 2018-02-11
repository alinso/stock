package com.alinso.stock.controller;

import com.alinso.stock.dao.StockDao;
import com.alinso.stock.dao.UserDAO;
import com.alinso.stock.dto.StockSearchListDTO;
import com.alinso.stock.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GuestController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    StockDao stockDao;


    @GetMapping(value = "firm/{username}")
    public String searchForm(@PathVariable("username") String username, Model model){
        User user  =userDAO.getByUsername(username);
        model.addAttribute("user", user);
        return "guest/search_form";
    }

    @PostMapping(value = "firm/{username}")
    public String searchProduct(@RequestParam("product") String product, @PathVariable("username") String username, Model model){

        User user  = userDAO.getByUsername(username);
        List<StockSearchListDTO> stockList=stockDao.getStockListByCodeForGuests(product,user);
        model.addAttribute("user",user);
        model.addAttribute("entities",stockList);
        return "/guest/search_form";

    }

}
