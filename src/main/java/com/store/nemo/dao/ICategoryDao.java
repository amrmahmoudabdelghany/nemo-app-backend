package com.store.nemo.dao;

import com.store.nemo.entity.Category;

public interface ICategoryDao extends IDAO<Category> {

	public Category findByName(String name) ; 
	
	
}
