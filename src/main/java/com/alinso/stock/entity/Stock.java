package com.alinso.stock.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by KHAN on 2.02.2018.
 */
@Entity
@Table(name="stock")
public class Stock extends BaseEntity {

    //TODO: Validationlar eklenecek

    @Column(name="productCode")
    private String productCode;

    @Column(name="productName")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY) //optional= ?
    @JoinColumn(name = "category")
    private Category category;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
