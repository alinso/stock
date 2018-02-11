package com.alinso.stock.dto;

import java.io.Serializable;

public class StockSearchListDTO implements Serializable {

    private String productCode;
    private String productName;
    private Integer productAmount;

    public  StockSearchListDTO(String productName, String productCode, Long productAmount){
        this.productCode  =productCode;
        this.productAmount=productAmount.intValue();
        this.productName  =productName;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer amount) {
        productAmount = amount;
    }
}
