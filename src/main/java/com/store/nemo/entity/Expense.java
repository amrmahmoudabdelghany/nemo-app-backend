package com.store.nemo.entity;

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
	
	@Column(name = "d_unit_price")
	private Double defaultUnitPrice ; 
	
	@Column(name = "purchase_date")
	private LocalDateTime purchaseDate ; 
	
	@Column(name = "description")
	private String description ;  
	
	@CreationTimestamp
	@Column(name = "register_date")
	private LocalDateTime registerDate ; 
	
	
	public Expense() {
	
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


	public Double getDefaultUnitPrice() {
		return defaultUnitPrice;
	}


	public void setDefaultUnitPrice(Double defaultUnitPrice) {
		this.defaultUnitPrice = defaultUnitPrice;
	}


	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(LocalDateTime purchaseDate) {
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultUnitPrice == null) ? 0 : defaultUnitPrice.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result + ((purchasePrice == null) ? 0 : purchasePrice.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (defaultUnitPrice == null) {
			if (other.defaultUnitPrice != null)
				return false;
		} else if (!defaultUnitPrice.equals(other.defaultUnitPrice))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (purchasePrice == null) {
			if (other.purchasePrice != null)
				return false;
		} else if (!purchasePrice.equals(other.purchasePrice))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
	
	
	
	
}
