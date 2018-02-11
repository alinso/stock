package com.alinso.stock.entity;

import com.alinso.stock.dto.StockSearchListDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //optional= ?
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
