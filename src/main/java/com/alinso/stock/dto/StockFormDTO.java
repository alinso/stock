package com.alinso.stock.dto;

import com.alinso.stock.entity.Stock;
import com.alinso.stock.entity.StockArrival;
import com.alinso.stock.entity.StockShelf;

import java.util.List;

public class StockFormDTO {
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<StockShelf> getStockShelfList() {
        return stockShelfList;
    }

    public void setStockShelfList(List<StockShelf> stockShelfList) {
        this.stockShelfList = stockShelfList;
    }

    public List<StockArrival> getStockArrivalList() {
        return stockArrivalList;
    }

    public void setStockArrivalList(List<StockArrival> stockArrivalList) {
        this.stockArrivalList = stockArrivalList;
    }

    private List<StockShelf> stockShelfList;
    private List<StockArrival> stockArrivalList;

}
