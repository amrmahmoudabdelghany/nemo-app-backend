package com.store.nemo.models;

import java.time.LocalDate;

public class ExpenseModel {
    
    private Integer expId ; 
    private Integer itemId ; 
    private Integer quantity ; 
    private Double price ; 
    private LocalDate expDate ; 
    private String description ; 


    public ExpenseModel(){

    }
    public ExpenseModel(Integer id ) { 
        this.expId = id ; 
    }
    public Integer getExpId() {
        return expId;
    }
    public void setExpId(Integer expId) {
        this.expId = expId;
    }
    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
    public LocalDate getExpDate() {
        return expDate;
    }
    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
