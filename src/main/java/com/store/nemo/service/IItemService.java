package com.store.nemo.service;

import java.util.List;

import com.store.nemo.entity.Item;

public interface IItemService {

	public List<Item> listAllItems() ; 
	public List<Item> listItemsByCategoryId(Integer categoryId) ; 
	public Item getItemById(Integer itemId) ;
	public void save(Item item);
	public void removeItem(Integer itemId); 
	
}
