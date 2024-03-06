package com.store.nemo.models;

public class PurchaseModel {
    
    private Integer stockId ; 
    private Integer quantity ; 
    private String description ; 

    public PurchaseModel() { 

    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
