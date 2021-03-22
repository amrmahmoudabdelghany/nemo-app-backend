package com.store.nemo.dao;

import java.util.List;

import com.store.nemo.entity.Item;

public interface IItemDao extends IDAO<Item> {

	public List<Item> findItemByCategoryId(Integer cateoryId) ; 
	public Item findItemByName(String itemName) ;  
	public Long findCountByCategoryId(Integer categoryId) ; 
	
}
