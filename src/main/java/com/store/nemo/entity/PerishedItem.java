package com.store.nemo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "perished_items")
public class PerishedItem { 

  
    @Id
    @Column(name = "perished_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ; 

    @Column(name = "perished_date")
    private LocalDate perishedDate ; 

    @Column(name = "quantity")
    private Integer quantity ; 

    @Column(name = "perished_price")
    private Double perishedPrice ; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stock ; 


    @Column(name ="description")
    private String description  ;

    @CreationTimestamp
    @Column(name = "registration_date")
    private LocalDateTime registrationDate ;



    public PerishedItem(Integer id) { 
      this.id = id ; 
    }

   public  PerishedItem() { 

    }




public Integer getId() {
    return id;
}




public Double getPerishedPrice() {
    return perishedPrice;
}




public void setPerishedPrice(Double perishedPrice) {
    this.perishedPrice = perishedPrice;
}




public void setId(Integer id) {
    this.id = id;
}




public LocalDate getPerishedDate() {
    return perishedDate;
}




public void setPerishedDate(LocalDate perishedDate) {
    this.perishedDate = perishedDate;
}




public Integer getQuantity() {
    return quantity;
}




public void setQuantity(Integer quantity) {
    this.quantity = quantity;
}




public Stock getStock() {
    return stock;
}




public void setStock(Stock stock) {
    this.stock = stock;
}




public String getDescription() {
    return description;
}




public void setDescription(String description) {
    this.description = description;
}




public LocalDateTime getRegistrationDate() {
    return registrationDate;
}




public void setRegistrationDate(LocalDateTime registrationDate) {
    this.registrationDate = registrationDate;
}





    

}