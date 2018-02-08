package com.alinso.stock.dao;

import com.alinso.stock.entity.StockShelf;
import org.springframework.stereotype.Repository;

/**
 * Created by KHAN on 7.02.2018.
 */
@Repository
public class StockShelfDao extends BaseDAO<StockShelf>{
    //TODO:BaseDao Dışında kullanılacaklar.
    StockShelfDao(){
        super.setTheClass(StockShelf.class);
    }
}
