package com.store.nemo.models;

import com.store.nemo.entity.Item;

public class ItemModel {
    
    private Integer CategoryId ; 
    private Item item ; 
    

    public ItemModel(Integer categoryId , Item item ) { 
         this.CategoryId = categoryId ; 
         this.item = item ; 

    }


    public Integer getCategoryId() {
        return CategoryId;
    }


    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }


    public Item getItem() {
        return item;
    }


    public void setItem(Item item) {
        this.item = item;
    }

    
}
