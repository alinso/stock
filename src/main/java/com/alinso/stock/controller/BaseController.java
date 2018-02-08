package com.alinso.stock.controller;

import com.alinso.stock.dao.BaseDAO;
import com.alinso.stock.entity.BaseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.Serializable;
import java.util.List;

@Controller
public class BaseController< T extends Serializable>{

    private Class<T> theClass;
    private BaseDAO theDao;

    public String editTitle;
    public String createTitle;
    public String title;
    public String addNewLink;


    public void setLinks(String urlString){
        this.addNewLink  ="/user/"+urlString+"/0";
    }

    public void setTitles(String title){
        this.editTitle=title+" Düzenle";
        this.createTitle = title+" Ekle";
    }

    public void setTheClass(Class<T> classToSet, BaseDAO daoToSet){
        theClass  =classToSet;
        theDao = daoToSet;
        theDao.setTheClass(classToSet);
    }


    public BaseEntity setEntity(int id) throws IllegalAccessException, InstantiationException {
        BaseEntity entity  = (BaseEntity) theDao.get(id);
        title = editTitle;
        if(entity==null){
            entity  = (BaseEntity) theClass.newInstance();
            title  =createTitle;
        }
        return entity;
    }

    public Model setShowFormModel(Model model, BaseEntity entity){
        model.addAttribute("title",title);
        model.addAttribute("entity",entity);
        model.addAttribute("message","");
        return model;
    }


    public Model setListModel(Model model){
        List<BaseEntity> entities  =theDao.getAll();
        model.addAttribute("entities",entities);
        model.addAttribute("addNewLink",addNewLink);
        return model;
    }




}
