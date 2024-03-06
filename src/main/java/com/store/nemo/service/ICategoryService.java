package com.store.nemo.service;

import java.util.List;

import com.store.nemo.entity.Category;

public interface ICategoryService {

	public void createCategory(Category category) ; 
	public void removeCategory(Integer category) ; 
	public Category getCategoryById(Integer id) ; 
	public void save(Category cateory) ; 
	public List<Category> listAllCategories() ; 
	
}
