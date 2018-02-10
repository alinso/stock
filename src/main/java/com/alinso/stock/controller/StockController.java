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

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KHAN on 6.02.2018.
 */
@Controller
@RequestMapping("user/stock")
public class StockController extends BaseController{

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

    @PostConstruct
    public void StockControllerPC(){
        super.setLinks("stock");
        super.setTitles("Ürün");
        super.setTheClass(Arrival.class, arrivalDao);
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
        model.addAttribute("categories",categoryDao.getAll());
        model.addAttribute("title", "Stok Ekle");

        return "user/stock/form";



    }

    @RequestMapping(value = "save",method = RequestMethod.GET)
    public String saveGetRedirect(Model md){
        return show(0, md);
    }


    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute StockFormDTO stockFormDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/stock/form";
        }

        Stock stock =  new Stock();
        if(stockFormDTO.getStock().getId()!=0)
            stock  =stockDao.get(stockFormDTO.getStock().getId());

        Category category  = categoryDao.get(stockFormDTO.getStock().getCategory().getId());
        stock.setCategory(category);
        stock.setProductCode(stockFormDTO.getStock().getProductCode());
        stock.setProductName(stockFormDTO.getStock().getProductName());
        stock  =stockDao.saveOrUpdate(stock);

        stockArrivalDao.sync(stockFormDTO.getStockArrivalList(),stock);
        stockShelfDao.sync(stockFormDTO.getStockShelfList(),stock);
        model.addAttribute("message","Kaydedildi,eklemeye devam edebilirsiniz!");
        model.addAttribute("lastRecordId",stock.getId());
        return show(0,model);
    }

    @RequestMapping(value="list")
    public String getStocks(Model model){
        List<Stock> stocks=stockDao.getAll();
        model.addAttribute("entities",stocks);
        model.addAttribute("addNewLink",this.addNewLink);
        return "user/stock/stock_list";
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteStock(@RequestAttribute int id, Model model ){
        return this.getStocks(model);
    }




}
