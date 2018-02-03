package com.alinso.stock.dao;


import com.alinso.stock.entity.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class UserDAO extends BaseDAO<User>{


    @Transactional
    public User getByUsername(String username) {
        User usr = (User) entityManager
                .createQuery("from User where username=:username")
                .setParameter("username", username).getSingleResult();
        return usr;
    }
}