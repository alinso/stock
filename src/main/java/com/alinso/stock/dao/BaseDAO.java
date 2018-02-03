package com.alinso.stock.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Component
public class BaseDAO< T extends Serializable> {


    @PersistenceContext
    EntityManager entityManager;

    private Class< T > theClass;


    @Transactional
    public T get( Integer id ){

        T entity =   entityManager.find(theClass, id );
        return entity;
    }

    @Transactional
    public List< T > getAll(){
        return entityManager.createQuery( "from " + theClass.getName() ).getResultList();
    }

    @Transactional
    public void saveOrUpdate( T entity ){
        entityManager.merge( entity );
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