package com.alinso.stock.entity;

import javax.persistence.*;

/**
 * Created by KHAN on 7.02.2018.
 */
@Entity
@Table(name="shockShelf")
public class StockShelf extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
            @JoinColumn(name="stock")
    Stock stock;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
            @JoinColumn(name = "shelf")
    Shelf shelf;

    @Column(name="productNumber")
    Integer productNumber;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }
}
