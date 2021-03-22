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

@Entity
@Table(name = "sales")
public class Sales {

	@Id
	@Column(name = "sal_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;  
	
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "stock_id")
	private Stock stock ; 
	
	@Column(name = "quantity")
	private Integer quantity ; 
	
	@Column(name = "sale_price")
	private Double salePrice ; 
	
	@Column(name = "sale_date")
	private LocalDateTime saleDate ; 
	
	@Column(name = "description")
	private String description ; 
	
	@Column(name = "register_date")
	private LocalDateTime registerDate ; 
	
	
	public Sales() {
		
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


	public Double getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}


	public LocalDateTime getSaleDate() {
		return saleDate;
	}


	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((saleDate == null) ? 0 : saleDate.hashCode());
		result = prime * result + ((salePrice == null) ? 0 : salePrice.hashCode());
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
		Sales other = (Sales) obj;
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
		if (saleDate == null) {
			if (other.saleDate != null)
				return false;
		} else if (!saleDate.equals(other.saleDate))
			return false;
		if (salePrice == null) {
			if (other.salePrice != null)
				return false;
		} else if (!salePrice.equals(other.salePrice))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
}
