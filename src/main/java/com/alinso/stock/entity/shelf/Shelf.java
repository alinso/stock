package com.alinso.stock.entity.shelf;

import com.alinso.stock.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by KHAN on 2.02.2018.
 */

@Entity
@Table(name="shelf")
public class Shelf extends BaseEntity{

    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
