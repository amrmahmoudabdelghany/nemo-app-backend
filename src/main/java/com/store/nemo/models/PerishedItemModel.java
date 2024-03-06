package com.store.nemo.models;

public class PerishedItemModel {
    
    private Integer perishedId  ; 
    private Integer stockId ; 
    private Integer quantity ; 
    private Integer price ; 
    private String description ; 




    public PerishedItemModel() { 

    }




    public Integer getPerishedId() {
        return perishedId;
    }




    public void setPerishedId(Integer perishedId) {
        this.perishedId = perishedId;
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




    public Integer getPrice() {
        return price;
    }




    public void setPrice(Integer price) {
        this.price = price;
    }




    public String getDescription() {
        return description;
    }




    public void setDescription(String description) {
        this.description = description;
    }



    
}
