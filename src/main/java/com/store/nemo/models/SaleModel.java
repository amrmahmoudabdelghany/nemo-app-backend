package com.store.nemo.models;

import java.time.LocalDate;

public class SaleModel {
    
    private Integer salId ; 
    private Integer itemId ; 
    private Integer quantity ; 
    private Double price ; 
    private LocalDate saleDate ; 
    private String description ; 


    public SaleModel() { 

    }


    public Integer getSalId() {
        return salId;
    }


    public void setSalId(Integer salId) {
        this.salId = salId;
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


    public LocalDate getSaleDate() {
        return saleDate;
    }


    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    


}
