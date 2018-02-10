package com.alinso.stock.dao;

import com.alinso.stock.entity.Stock;
import com.alinso.stock.entity.StockArrival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by KHAN on 7.02.2018.
 */
@Repository
public class StockArrivalDao extends BaseDAO<StockArrival>{
    //TODO : BaseDao DIışında kullanılacaklar.
    StockArrivalDao(){
        super.setTheClass(StockArrival.class);
    }

    @Transactional
    public List<StockArrival> getByStockId(Stock stock){
        Query q  =entityManager.createQuery("select s from StockArrival s where s.stock=:stock");
        q.setParameter("stock",stock);

        return q.getResultList();
    }

    @Transactional
    public void sync(List<StockArrival> stockArrivalList, Stock stock){
        List<StockArrival> stockArrivalList1  =getByStockId(stock);
        entityManager.createQuery("delete from StockArrival where stock=:stock").setParameter("stock",stock).executeUpdate();
        bulkInsert(stockArrivalList);
    }


    @Transactional
    public void bulkInsert(List<StockArrival> stockArrivalList){
        int batchSize = 30;


        Integer i=0;
        for (StockArrival stockArrival: stockArrivalList){

            entityManager.persist(stockArrival);

            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
            i++;
        }
    }

}
