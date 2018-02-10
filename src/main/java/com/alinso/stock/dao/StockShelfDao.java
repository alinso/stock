package com.alinso.stock.dao;

import com.alinso.stock.entity.Stock;
import com.alinso.stock.entity.StockShelf;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by KHAN on 7.02.2018.
 */
@Repository
public class StockShelfDao extends BaseDAO<StockShelf>{
    //TODO:BaseDao Dışında kullanılacaklar.
    StockShelfDao(){
        super.setTheClass(StockShelf.class);
    }


    @Transactional
    public List<StockShelf> getByStockId(Stock stock){
        Query q  =entityManager.createQuery("from StockShelf where stock=:stock");
        q.setParameter("stock",stock);
        return q.getResultList();
    }

    @Transactional
    public void sync(List<StockShelf> stockShelfList, Stock stock){
        List<StockShelf> stockShelfList1  =getByStockId(stock);
        entityManager.createQuery("delete from StockShelf where stock=:stock").setParameter("stock",stock).executeUpdate();
        bulkInsert(stockShelfList);
    }

    @Transactional
    public void bulkInsert(List<StockShelf> stockShelfList){
        int batchSize = 30;


        Integer i=0;
        for (StockShelf stockShelf: stockShelfList){

            entityManager.persist(stockShelf);

            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
            i++;
        }
    }

}
