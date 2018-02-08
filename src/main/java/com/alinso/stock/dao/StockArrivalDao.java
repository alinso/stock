package com.alinso.stock.dao;

import com.alinso.stock.entity.StockArrival;
import org.springframework.stereotype.Repository;

/**
 * Created by KHAN on 7.02.2018.
 */
@Repository
public class StockArrivalDao extends BaseDAO<StockArrival>{
    //TODO : BaseDao DIışında kullanılacaklar.
    StockArrivalDao(){
        super.setTheClass(StockArrival.class);
    }
}
