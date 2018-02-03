package com.alinso.stock.dao;

import com.alinso.stock.dao.BaseDAO;
import com.alinso.stock.entity.Shelf;
import org.springframework.stereotype.Repository;

/**
 * Created by KHAN on 2.02.2018.
 */

@Repository
public class ShelfDao extends BaseDAO<Shelf> {
    ShelfDao(){
        super.setTheClass(Shelf.class);
    }
//    TODO: AbstractDao dışında kullanılacak database işlemleri
}
