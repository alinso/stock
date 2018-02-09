package com.alinso.stock.controller;

import com.alinso.stock.dao.*;
import com.alinso.stock.dto.StockFormDTO;
import com.alinso.stock.entity.*;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KHAN on 6.02.2018.
 */
@Controller
@RequestMapping("user/stock")
public class StockController {

    @Autowired
    StockDao stockDao;

    @Autowired
    ShelfDao shelfDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ArrivalDao arrivalDao;

    @Autowired
    StockShelfDao stockShelfDao;

    @Autowired
    StockArrivalDao stockArrivalDao;

    @Autowired
    Auth auth;

    @RequestMapping
    public String stockGet(Model model){
        model.addAttribute("title","Stoklar");
        model.addAttribute("shelfList",shelfDao.getAll());
//        model.addAttribute("categoryList",categoryDao.getAll());
//        model.addAttribute("arrivalList",arrivalDao.getAll());
        model.addAttribute("stock",new Stock());
        model.addAttribute("stockShelf",new StockShelf());
        return "admin/stock/stock_form";
    }


    @RequestMapping(value="{id}",method=RequestMethod.GET)
    public String show(@PathVariable("id") int id, Model model){
        Stock stock= new Stock();
        List<StockShelf> stockShelves  = new ArrayList<>();
        List<StockArrival> stockArrivals =  new ArrayList<>();
        List<Arrival> arrivals;
        List<Shelf> shelves;
        if(id==0){

             stock=new Stock();
             shelves = shelfDao.getAll();
            arrivals = arrivalDao.getAll();

            for(Shelf shelf: shelves){
                StockShelf stockShelf  = new StockShelf();
                stockShelf.setStock(stock);
                stockShelf.setShelf(shelf);
                stockShelves.add(stockShelf);
            }
            for(Arrival arrival  :arrivals){
                StockArrival stockArrival =  new StockArrival();
                stockArrival.setArrival(arrival);
                stockArrival.setStock(stock);
                stockArrivals.add(stockArrival);
            }

        }
        StockFormDTO stockFormDTO =  new StockFormDTO();
        stockFormDTO.setStock(stock);
        stockFormDTO.setStockArrivalList(stockArrivals);
        stockFormDTO.setStockShelfList(stockShelves);

        model.addAttribute("stockForm",stockFormDTO);
        model.addAttribute("stock",stock);
        model.addAttribute("title", "Stok Ekle");

        return "user/stock/form";



    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String stockPost(@Valid @ModelAttribute StockFormDTO stockFormDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "admin/stock/stock_form";
        }
        StockFormDTO s = stockFormDTO;
        return "admin/stock/stock_save_confirm";
    }

    @RequestMapping(value="list")
    public String getStocks(Model model){
        List<Stock> stocks=stockDao.getAll();
        model.addAttribute("stocks",stocks);
        return "admin/stock/stock_list";
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteStock(@RequestAttribute int id, Model model ){
        return this.getStocks(model);
    }

    @RequestMapping(value="update/{id}",method=RequestMethod.GET)
    public String updateStockPage(@PathVariable("id") int id, Model model){
        Stock stock=stockDao.get(id);
        model.addAttribute("stockUpdated",stock);
        return "admin/stock/stock_update";
    }

    @RequestMapping(value="update",method = RequestMethod.POST)
    public String updateStock(@Valid @ModelAttribute Stock stock, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return this.updateStockPage(stock.getId(),model);
        }

        stock.setUpdateUser(auth.getCurrentUser());
        stockDao.saveOrUpdate(stock);
        model.addAttribute("stock",stock);
        return "admin/stock/stock_update_confirm";
    }

}
