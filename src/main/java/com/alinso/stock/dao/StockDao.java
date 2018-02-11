package com.alinso.stock.dao;

import com.alinso.stock.dao.BaseDAO;
import com.alinso.stock.entity.Stock;
import com.alinso.stock.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by KHAN on 2.02.2018.
 */
@Repository
public class StockDao extends BaseDAO<Stock>{
//    TODO: Abstract Dao dışında yapılacaklar
    StockDao(){
        super.setTheClass(Stock.class);
    }

    public List<Stock> getStockListByCode(String productCode){
        String hql="from Stock s \n" +
                "where (s.productCode like :productCode or s.productName like :productName) \n" +
                "and s.createUser = :user";
        Query query=super.entityManager
                .createQuery(hql,Stock.class);
        query.setParameter("productCode","%"+productCode+"%")
                .setParameter("productName","%"+productCode+"%")
                .setParameter("user",this.auth.getCurrentUser());
        List<Stock> stockList=query.getResultList();
        return stockList;
    }

    public List<Stock> getStockListByCodeDForGuests(String productCode,User user){
        if(user ==null){
            return null;
        }
        String hql="from Stock s \n" +
                "where (s.productCode like :productCode or s.productName like :productName) \n" +
                "and s.createUser = :user";
        Query query=super.entityManager
                .createQuery(hql,Stock.class);
        query.setParameter("productCode","%"+productCode+"%")
                .setParameter("productName","%"+productCode+"%")
                .setParameter("user",user);
        List<Stock> stockList=query.getResultList();
        return stockList;
    }

}
