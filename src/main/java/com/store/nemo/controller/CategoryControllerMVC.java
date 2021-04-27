package com.store.nemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.nemo.entity.Category;
import com.store.nemo.exceptions.DataCohesionViolation;
import com.store.nemo.exceptions.UniquenessConstraintViolation;
import com.store.nemo.service.CategoryService;
import com.store.nemo.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class CategoryControllerMVC {

	@Autowired
	private ICategoryService service ; 
	
	
	
	@GetMapping("/list-all")
	public String listAllCategories(Model model) { 
		
		return "category";
	}
	
	@GetMapping("/edit-form")
	public String showEditForm(@RequestParam String categoryId , Model model) { 
		 Category cat = this.service.getCategoryById(Integer.parseInt(categoryId)) ;
		 model.addAttribute("category" , cat) ; 
		return "category-form" ; 
	}
	@GetMapping("/create-form")
	public String showCreateForm(Model model) { 
		Category category = new Category() ; 
		model.addAttribute("category" , category) ; 
		return "category-form" ; 
	}
	@PostMapping("/save")
	public String editCategory(@ModelAttribute Category category , Model model) { 
	    boolean create = (category.getId() == null) ; 
	    
		this.service.save(category);
		if(create) { 
			model.addAttribute("message" , "Category ' " + category.getName() + " ' has been created successfully.") ; 
		}else { 
			model.addAttribute("message"  ,"Category ' " + category.getName() + " ' has been updated successfully.") ;
		}
	   List<Category> categories = this.service.listAllCategories() ; 
	   model.addAttribute("categories" , categories) ; 
		return "category" ; 
	}
	
	@GetMapping("/remove")
	public String removeCategory(@RequestParam Integer categoryId , Model model) { 
		
		this.service.removeCategory(categoryId);
		
		  List<Category> categories = this.service.listAllCategories() ; 
		   model.addAttribute("categories" , categories) ;
		   model.addAttribute("message" , "Category has been removed successuflly.") ; 
	   return "category" ; 
	}
	@ExceptionHandler({UniquenessConstraintViolation.class , DataCohesionViolation.class})
	public String handleUniquenessNameViolation(Exception ex, Model model) { 
		model.addAttribute("message", ex.getMessage()) ; 
		return "error-message" ; 
	}
	
	
}
