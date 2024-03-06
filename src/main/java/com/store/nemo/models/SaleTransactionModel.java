package com.store.nemo.models;

public class SaleTransactionModel {
    
    private Integer salId ; 
    private Integer stockId ; 
    private Integer quantity ; 
    private Double price ; 
    private String description ; 

    public SaleTransactionModel() { 

    }

    public Integer getSalId() {
        return salId;
    }

    public void setSalId(Integer salId) {
        this.salId = salId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SaleTransactionModel [description=" + description + ", price=" + price + ", quantity=" + quantity
                + ", salId=" + salId + ", stockId=" + stockId + "]";
    }

    
}
