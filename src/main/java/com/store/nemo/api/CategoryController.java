package com.store.nemo.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.nemo.entity.Category;
import com.store.nemo.service.ICategoryService;

@RestController()
@RequestMapping("/api")
public class CategoryController {

	
	@Autowired
	private ICategoryService categoryService ; 
	
	
	public CategoryController() {
	  System.out.println("On Create Category Api") ; 
	}
	
	
	@GetMapping("/categories")
	public List<Category> listAllCategories() { 
		List<Category> res = categoryService.listAllCategories(); 
		int tSize = 0 ; 
		
		for(Category cat : res) { 
			tSize += cat.getItems().size() ; 
		}
		System.out.println("Items size " + tSize) ; 
		List<Category> catTes = new ArrayList<>() ; 
		catTes.add(new Category("test") ) ; 
		return res  ; 
	}
}
