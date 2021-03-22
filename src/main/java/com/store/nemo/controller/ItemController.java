package com.store.nemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.nemo.entity.Item;
import com.store.nemo.exceptions.UniquenessConstraintViolation;
import com.store.nemo.service.IItemService;

@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private IItemService itemService ; 
	
	@GetMapping()
	public String listAllItems(@RequestParam String category , Model model) { 
		
		// category variable need pre-validation 
	    
		if(category.equals("*")) { 
		 model.addAttribute("items", itemService.listAllItems()) ; 
		}else { 
			model.addAttribute("items",itemService.listItemsByCategoryId(Integer.parseInt(category))) ; 
		}
		return "items" ; 
	}
	
	@GetMapping("/edit-form")
	public String showItemForm(@RequestParam String itemId  , Model model) { 
		Item item = itemService.getItemById(Integer.parseInt(itemId)) ; 
		model.addAttribute("item", item) ; 
		return "item-form" ; 
	}
	@GetMapping("/create-form")
	public String showItemForm(Model model ) { 
		Item item = new Item(); 
		model.addAttribute("item" , item) ; 
		return "item-form" ; 
	}
	@PostMapping("/save")
	public String editItem(@ModelAttribute("item") Item item ) { 
		
		this.itemService.save(item) ; 
		
		return "redirect:/items?category=" + item.getCategory().getId() ; 
	}
	@GetMapping("/delete")
	public String deleteItem(@RequestParam Integer itemId , @RequestParam Integer categoryId) {
		
		this.itemService.removeItem(itemId) ; 
		
		return "redirect:/items?category=" + categoryId ; 
	}
	
	
	@ExceptionHandler(UniquenessConstraintViolation.class)
	public String itemUniqunessViolation(Exception ex , Model model) { 
	 
		
		model.addAttribute("message" , ex.getMessage()) ;  
		
		return "error-message" ; 
	}
	
}
