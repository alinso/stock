package com.alinso.stock.controller;

import com.alinso.stock.dao.ArrivalDao;
import com.alinso.stock.entity.Arrival;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by KHAN on 4.02.2018.
 */

@Controller
@RequestMapping("user/arrival")
public class ArrivalController {
    @Autowired
    private ArrivalDao arrivalDao;

    @Autowired
    Auth auth;


    @RequestMapping
    public String arrivalGet(Model model){
        model.addAttribute("title","Sipariş Tarihi");
        model.addAttribute(new Arrival());
        return "admin/arrival/arrival_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String arrivalPost(@Valid @ModelAttribute Arrival arrival, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "admin/arrival/arrival_form";
        }
//        TODO: Dynamic or More Understandable
        if(arrivalDao.getArrivalCount(auth.getCurrentUser())>=3){
            bindingResult.rejectValue("arrivalDate","","En fazla 3 sipariş tarihi!");
            return "admin/arrival/arrival_form";
        }

        arrival.setCreateUser(auth.getCurrentUser());
        arrivalDao.saveOrUpdate(arrival);
        model.addAttribute("arrival",arrival);
        return "admin/arrival/arrival_save_confirm";
    }

    @RequestMapping(value="list")
    public String getArrivals(Model model){
        List<Arrival> arrivals=arrivalDao.getAll();
        model.addAttribute("arrivals",arrivals);
        return "admin/arrival/arrival_list";
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteArrival(@RequestAttribute int id, Model model ){
        return this.getArrivals(model);
    }

    @RequestMapping(value="update/{id}",method=RequestMethod.GET)
    public String updateArrivalPage(@PathVariable("id") int id, Model model){
        Arrival arrival=arrivalDao.get(id);
        model.addAttribute("arrivalUpdated",arrival);
        return "admin/arrival/arrival_update";
    }

    @RequestMapping(value="update",method = RequestMethod.POST)
    public String updateArrival(@Valid @ModelAttribute Arrival arrival, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return this.updateArrivalPage(arrival.getId(),model);
        }

        arrival.setUpdateUser(auth.getCurrentUser());
        arrivalDao.saveOrUpdate(arrival);
        model.addAttribute("arrival",arrival);
        return "admin/arrival/arrival_update_confirm";
    }

}
