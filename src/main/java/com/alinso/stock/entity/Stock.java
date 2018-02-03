package com.alinso.stock.entity;

import com.alinso.stock.entity.BaseEntity;
import com.alinso.stock.entity.Category;
import com.alinso.stock.entity.Shelf;

import javax.persistence.*;
import java.util.List;

/**
 * Created by KHAN on 2.02.2018.
 */
@Entity
@Table(name="stock")
public class Stock extends BaseEntity {

    @Column(name="productName")
    private String productName;

    @Column(name="productCode")
    private String productCode;

    public String getProductName() {
        return productName;
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="stockShelve"
            ,joinColumns = @JoinColumn(name="shelfId")
            ,inverseJoinColumns = @JoinColumn(name="stockId"))
    private List<Shelf> shelves;


    /*
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="stockOrder"
            ,joinColumns = @JoinColumn(name="orderId")
            ,inverseJoinColumns = @JoinColumn(name="stockId"))
    private List<Order> orders;*/

    @ManyToOne(fetch = FetchType.LAZY) //optional= ?
    @JoinColumn(name = "category")
    private Category category;


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }




    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
