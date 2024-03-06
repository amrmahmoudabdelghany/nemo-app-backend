package com.store.nemo.models;

import java.time.LocalDate;

public class PerishedModel {
    
    private Integer id ; 
    private Integer itemId ; 
    private Integer quantity ; 
    private Double price ; 
    private LocalDate date ; 
    private String description ; 




    public PerishedModel() { 

    }


  


    public Integer getId() {
        return id;
    }





    public void setId(Integer id) {
        this.id = id;
    }





    public Integer getItemId() {
        return itemId;
    }





    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }





    public Double getPrice() {
        return price;
    }





    public void setPrice(Double price) {
        this.price = price;
    }





    public LocalDate getDate() {
        return date;
    }





    public void setDate(LocalDate date) {
        this.date = date;
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
