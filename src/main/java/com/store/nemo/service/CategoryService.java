package com.store.nemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.nemo.dao.CategoryDao;
import com.store.nemo.dao.ICategoryDao;
import com.store.nemo.dao.IItemDao;
import com.store.nemo.entity.Category;
import com.store.nemo.entity.Item;
import com.store.nemo.exceptions.DataCohesionViolation;
import com.store.nemo.exceptions.UniquenessConstraintViolation;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryDao categoryDao ; 
	
	@Autowired
	private IItemDao itemDao ; 

	
		
	public CategoryService() {
		System.out.println("On Create Server") ; 
	}
	
	@Override
	@Transactional
	public void createCategory(Category category) {
		
		categoryDao.save(category) ; 
	}

	@Override
	@Transactional
	public void removeCategory(Integer categoryId) {
	    
		Category category = this.categoryDao.findById(categoryId) ; 
		
		Long relatedItems = this.itemDao.findCountByCategoryId(categoryId) ; 
		if(relatedItems > 0) { 
			throw new DataCohesionViolation("The Catgory '" + category.getName() + 
					"' Cannot be deleted because it contains " + relatedItems + " items.\n"
							+ "Remove related items first and try agin.") ; 
		}
		this.categoryDao.delete(category);
		
	}

	@Override
	@Transactional
	public List<Category> listAllCategories() {
		return this.categoryDao.findAll() ; 
	}

	@Override
	@Transactional
	public Category getCategoryById(Integer id) {
		return categoryDao.findById(id) ; 
	}

	@Override
	@Transactional
	public void save(Category category) {
		
		Category categoryByName = this.categoryDao.findByName(category.getName()) ; 
		if(categoryByName != null && categoryByName.getId() != category.getId()) {
			throw new UniquenessConstraintViolation("Category Name Must Be Unique.") ; 
		}
		
		this.categoryDao.save(category) ; 
	}

}
