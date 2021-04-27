package com.store.nemo.api;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.nemo.entity.Category;
import com.store.nemo.exceptions.InvalidData;
//import com.store.nemo.exceptions.InvalidData;
import com.store.nemo.repositories.CategoryRepository;


//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
@RestController
@RequestMapping("categories")
public class CategoryApi {

	@Autowired
	private CategoryRepository categoryRepository ; 
	
	
	
	public CategoryApi() {
	
	}
		

	@PostMapping("/save")
	public Category save(@RequestBody Category category ) { 
       
		System.out.println("On Save Category "  + category) ; 
		if(category.getName() == null || category.getName().equals(""))  {
			 throw new InvalidData("Category Name is required") ; 
		}
		

		if(category.getId() == null || category.getId() == -1 ) {  // case create 
			if(this.categoryRepository.existsByName(category.getName().toLowerCase())) { 
				throw new InvalidData("Category name already exists") ; 
			}
 		}else { 
			  // check if the name of the input category is already exists to another category 
			Category dbCategory = this.categoryRepository.findByName(category.getName()) ; 
			if(dbCategory != null && dbCategory.getId() != category.getId()){ 
				throw new InvalidData("Category name must be unique") ; 
			}
			
		}

		this.categoryRepository.save(category) ; 
	//	Category target = this.categoryRepository.findById(category.getId()).orElse(new Category(-1 , category.getName()))


		//if(!create && this.categoryRepository.existsByName(category.getName())) { 
		//	throw new InvalidData("Category name must be uniqe") ; 
	//	}



     return category ;  
	}

	@DeleteMapping("/delete/{categoryId}")
	public Map<String , String> delete(@PathVariable("categoryId") Integer categoryId ) { 
 
	    if(categoryId == null) { 
			throw new InvalidData("Category id is required") ; 
		}

		Category category  = this.categoryRepository.findById(categoryId).orElseThrow(()->{
			throw new InvalidData("Category is not exists") ; 
 		}) ; 
        
		 if(category.getItems().size() > 0) { 
			 throw new InvalidData("There exsits item(s) related with this category , please remove related item(s) first and try agin") ; 
		 }

		 this.categoryRepository.delete(category);

		return Collections.singletonMap("message", "Category "+ category.getName() + " has been deleted successuffly.") ; 
	}
	


	@ResponseBody
	@ExceptionHandler({ InvalidData.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String itemUniqunessViolation(Exception ex ) { 
	 
		return ex.getMessage() ; 
	}

}
