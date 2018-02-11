package com.alinso.stock.controller;

import com.alinso.stock.common.Util;
import com.alinso.stock.dao.ArrivalDao;
import com.alinso.stock.entity.Arrival;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by KHAN on 4.02.2018.
 */

@Controller
@RequestMapping("user/arrival")
public class ArrivalController extends BaseController {
    @Autowired
    private ArrivalDao arrivalDao;

    @Autowired
    Auth auth;

    @PostConstruct
    public void ArrivalControllerPC(){
        super.setLinks("arrival");
        super.setTitles("Sipariş");
        super.setTheClass(Arrival.class, arrivalDao);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public String show(@PathVariable("id") int id,Model model) throws InstantiationException, IllegalAccessException {

        Arrival arrival = (Arrival) setEntity(id);
        model = setShowFormModel(model,arrival);
        return "user/arrival/form";
    }


    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Arrival arrival,BindingResult bindingResult,Model model) throws IllegalAccessException, InstantiationException {
        if(bindingResult.hasErrors()){
            return this.show(arrival.getId(),model);
        }
        //        TODO: Dynamic or More Understandable
        if(arrivalDao.getArrivalCount(auth.getCurrentUser())>=3){
            bindingResult.rejectValue("arrivalDate","","En fazla 3 sipariş tarihi!");
            return this.list(model);
        }

        try {
            arrivalDao.saveOrUpdate(arrival);
            model.addAttribute("message", Util.saveSuccessMessage);
        }catch (Exception e){
            model.addAttribute("message",Util.saveErrorMessage);
        }
        model.addAttribute("title",createTitle);

        return show(0,model);
    }


    @RequestMapping(value="list")
    public String list(Model model){
        model  = setListModel(model);
        return "user/arrival/arrival_list";
    }

    @RequestMapping(value="save",method = RequestMethod.GET)
    public String saveGetRedirect(Model md) throws IllegalAccessException, InstantiationException {
        return this.show(0,md);
    }

    //Not Allowed Yet
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteArrival(@RequestAttribute int id, Model model ){
        return this.list(model);
    }


}
