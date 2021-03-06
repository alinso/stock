package com.alinso.stock.entity;

import javax.persistence.*;

/**
 * Created by KHAN on 7.02.2018.
 */
@Entity
@Table(name="stockArrival")
public class StockArrival extends BaseEntity {

    @ManyToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="stock")
    Stock stock;

    @ManyToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="arrival")
    Arrival arrival;

    @Column(name="productAmount")
    private Integer productAmount;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productNumber) {
        this.productAmount = productNumber;
    }
}
