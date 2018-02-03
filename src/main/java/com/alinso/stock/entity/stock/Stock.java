package com.alinso.stock.entity.stock;

import com.alinso.stock.entity.BaseEntity;
import com.alinso.stock.entity.category.Category;
import com.alinso.stock.entity.order.Order;
import com.alinso.stock.entity.shelf.Shelf;

import javax.persistence.*;
import java.io.Serializable;
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
    @JoinTable(name="stocksshelves"
            ,joinColumns = @JoinColumn(name="shelfId")
            ,inverseJoinColumns = @JoinColumn(name="stockId"))
    private List<Shelf> shelves;


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="stocksOrder"
            ,joinColumns = @JoinColumn(name="orderId")
            ,inverseJoinColumns = @JoinColumn(name="stockId"))
    private List<Order> orders;

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
