package com.alinso.stock.controller.shelf;

import com.alinso.stock.dao.shelf.ShelfDao;
import com.alinso.stock.entity.shelf.Shelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by KHAN on 2.02.2018.
 */
@Service
@Transactional
public class ShelfService {
    @Autowired
    ShelfDao shelfDao;

    public List<Shelf> getShelves() {
        List<Shelf> shelves =shelfDao.getAll();
        return shelves;
    }
}
