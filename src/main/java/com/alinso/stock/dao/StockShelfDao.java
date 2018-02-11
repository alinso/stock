package com.alinso.stock.dao;

import com.alinso.stock.entity.Shelf;
import com.alinso.stock.entity.Stock;
import com.alinso.stock.entity.StockShelf;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ShelfDao shelfDao;

    @Transactional
    public List<StockShelf> getByStock(Stock stock){
        Query q  =entityManager.createQuery("from StockShelf where stock=:stock");
        q.setParameter("stock",stock);
        return q.getResultList();
    }



    @Transactional
    public void sync(List<StockShelf> stockShelfList, Stock stock){
        List<StockShelf> stockShelfList1  =getByStock(stock);
        entityManager.createQuery("delete from StockShelf where stock=:stock").setParameter("stock",stock).executeUpdate();
        bulkInsert(stockShelfList,stock);
    }

    @Transactional
    public void bulkInsert(List<StockShelf> stockShelfList,Stock stock){
        int batchSize = 30;


        Integer i=0;
        if(stockShelfList!=null)
        for (StockShelf stockShelf: stockShelfList){

            stockShelf.setStock(stock);
            stockShelf.setShelf(shelfDao.get(stockShelf.getShelf().getId()));
            entityManager.persist(stockShelf);

            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
            i++;
        }
    }

    @Transactional
    public StockShelf getByShelfAndStock(Shelf shelf,Stock stock){
      return  (StockShelf) entityManager.createQuery("from StockShelf where shelf=:shelf and stock=:stock")
              .setParameter("shelf",shelf)
              .setParameter("stock",stock)
              .getSingleResult();

    }

}
