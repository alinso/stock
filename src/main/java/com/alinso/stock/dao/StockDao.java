package com.alinso.stock.dao;

import com.alinso.stock.dao.BaseDAO;
import com.alinso.stock.entity.Stock;
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
        String hql="from Stock s where s.productCode like :productCode";
        Query query=super.entityManager
                .createQuery(hql,Stock.class);
        return query.setParameter("productCode","%"+productCode+"%").getResultList();
    }

}
