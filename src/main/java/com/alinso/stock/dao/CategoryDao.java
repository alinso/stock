package com.alinso.stock.dao;

import com.alinso.stock.dao.BaseDAO;
import com.alinso.stock.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * Created by KHAN on 2.02.2018.
 */
@Repository
public class CategoryDao extends BaseDAO<Category> {

    public CategoryDao(){
        super.setTheClass(Category.class);
    }
//    TODO: AbstractDao Dışında Kullanılacaklar
}
