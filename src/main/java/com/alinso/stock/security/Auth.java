package com.alinso.stock.security;


import com.alinso.stock.dao.UserDAO;
import com.alinso.stock.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Auth {

    @Autowired
    UserDAO userDAO;



    @Transactional
    public int getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int userId=0;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomAuthUser u = (CustomAuthUser)authentication.getPrincipal();
            userId = u.getId();

        }

        return userId;
    }



    @Transactional
    public User getCurrentUser(){
        if(this.getId()>0) {
            int id = this.getId();
            User user = userDAO.get(id);
            return user;
        }
        else
            return null;
    }

    @Transactional
    public String getUsername(){
        if(this.getId()>0) {
            int id = this.getId();
            User user = userDAO.get(id);
            return user.getUsername();
        }
        else
            return null;
    }

}
