package com.alinso.stock.dao;

import com.alinso.stock.entity.BaseEntity;
import com.alinso.stock.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Component
public class BaseDAO< T extends BaseEntity> {

    public void setTheClass(Class theClassToSet){
        theClass = theClassToSet;
    }

    @PersistenceContext
    EntityManager entityManager;

    private Class< T > theClass;

    @Autowired
    protected Auth auth;

    @Transactional
    public T get( Integer id ){

        T entity =   entityManager.find(theClass, id );
        return entity;
    }

    @Transactional
    public List< T > getAll(){
        String hql="from "+theClass.getName()+" e\n" +
                " where e.createUser = :user";
        Query query= this.entityManager.createQuery(hql);
        query.setParameter("user",auth.getCurrentUser());
        List<T> entityList=query.getResultList();
        return  entityList;
    }

    @Transactional
    public T saveOrUpdate( T entity ){
        if(entity.getId()>0){
            entity.setUpdateUser(auth.getCurrentUser());
        }else{
            entity.setCreateUser(auth.getCurrentUser());
        }
        T res  =entityManager.merge( entity );
        return res;
    }

    @Transactional
    public void delete( T entity ){
        entityManager.remove( entity );
    }

    @Transactional
    public void delete( Integer entityId ) {
        T entity =get( entityId );
        delete( entity );
    }


}