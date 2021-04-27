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
@Table(name = "expenses")
public class Expense {

	

	
	
	@Id
	@Column(name = "exp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ; 
	
	@ManyToOne(fetch = FetchType.EAGER   )
	@JoinColumn(name = "stock_id" )
	private Stock stock ; 
	
	@Column(name = "quantity")
	private Integer quantity ; 
	
	@Column(name = "purchase_price")
	private Double purchasePrice ; 
	

	
	@Column(name = "purchase_date")
	private LocalDate purchaseDate ; 
	
	@Column(name = "description")
	private String description ;  
	
	@CreationTimestamp
	@Column(name = "register_date")
	private LocalDateTime registerDate ; 
	
	
	public Expense() {
	
	}
	
	public Expense(Integer id) { 
		this.id = id ; 
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Double getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDateTime getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}


	
	
	
	
}
