package com.alinso.stock.entity.order;

import com.alinso.stock.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by KHAN on 2.02.2018.
 */
@Entity
@Table(name="order")
/**
 * Namı Diğer Tır Class
 * TODO: Başka ne var?
 */
public class Order extends BaseEntity {

    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
