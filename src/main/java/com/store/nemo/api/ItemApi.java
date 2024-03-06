package com.store.nemo.api;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.store.nemo.entity.Category;
import com.store.nemo.entity.Item;
import com.store.nemo.entity.Stock;
import com.store.nemo.exceptions.DataCohesionViolation;
import com.store.nemo.exceptions.InvalidData;
import com.store.nemo.exceptions.UniquenessConstraintViolation;
import com.store.nemo.models.ItemModel;
import com.store.nemo.repositories.CategoryRepository;
import com.store.nemo.repositories.ExpensesRepository;
import com.store.nemo.repositories.ItemRepository;
import com.store.nemo.repositories.SalesRepository;
import com.store.nemo.repositories.StockRepository;
import com.store.nemo.service.DefualtItemService;

import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping ;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 

@RestController
@RequestMapping("items")
@CrossOrigin("*")
public class ItemApi {
	
	
	@Autowired
    private  ItemRepository itemRepository ; 
	@Autowired
    private  CategoryRepository categoryRepository ; 

	@Autowired
	private ExpensesRepository expensesRepository ; 
	
	@Autowired
	private SalesRepository salesRepository ; 
	

	@PostMapping("/save")
	public Item saveItem(@RequestBody ItemModel item){ 
       

		//check exists of category id 
		if(item.getCategoryId() == null) { 
			throw new InvalidData("Category id not exists in your request ") ; 
		}

		//check validation of category id  

		Category category = this.categoryRepository
		.findById(item.getCategoryId())
		.orElseThrow(()-> new InvalidData("Category with id " + item.getCategoryId()  + " is not valid") ) ;
		

	   //check existence of item 

	   if(item.getItem() == null) { 
		   throw new InvalidData("Item data not exists in your request ") ; 
	   }
     
	   if(item.getItem().getPurchasingPrice() < 0 ){
         throw new InvalidData("The purchase price of the item must not be a negative number") ; 
	   } 
	   if(item.getItem().getSallingPrice() < 0) { 
		throw new InvalidData("The salling price of the item must not be a negative number") ; 
	   }

	   //check validation of description (length ..)
       if(item.getItem().getDescription().length() > 300) { 
		throw new InvalidData("Item description is too long , the maximum length is 300 character ") ; 
	    }   

	   
	
     

       boolean create  = false ; 

	   //check intend action (persists or update)
       if(item.getItem().getId() == null || item.getItem().getId() == -1) create = true ; 


	   if(create) { 
  
		//check validation of item name 
		if(this.itemRepository.existsByName(item.getItem().getName())) { 
			throw new UniquenessConstraintViolation("Item name must be unique , so you colud not create item with name " 
			+ item.getItem().getName() + " because its already exists")  ; 
		   }
		
		//create stock for the new item  
		Stock stock = new Stock() ; 
        stock.setQuantity(0);
         
	     Item res = item.getItem() ; 
          
		 res.setId(null);
		// category.addItem(res);

		 res.setStock(stock);
         res.setCategory(category);
         
		 
		 itemRepository.save(res) ; 
              
 		return res ; 

	   }else { // case user intend to edit existence item 

		Item res =  itemRepository.getOne(item.getItem().getId()) ; 
      
		 if(res ==  null) { 
			throw new InvalidData("you are trying to edit invalid data") ; 
		 }

		 if((!res.getName().equals(item.getItem().getName()) )&& itemRepository.existsByName(item.getItem().getName())) { 
		   throw new InvalidData("Item name must be unique") ; 
		 }

	     res.setName(item.getItem().getName());
		 res.setDescription(item.getItem().getDescription());
		 res.setPurchasingPrice(item.getItem().getPurchasingPrice());
		 res.setSallingPrice(item.getItem().getSallingPrice());
		 res.setCategory(category);

		return itemRepository.save(res) ; 

	   }



	}
   
	@DeleteMapping( path = "/remove/{itemId}" , produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE )
	public Map<String , String> removeItem(@PathVariable Integer itemId ) {

		// check validation of item id 
		
		if(itemId == null) { 
			throw new InvalidData("Item id not exists in your request") ; 
		}

		Item target  = this.itemRepository.findById(itemId).orElseThrow(()->{
			throw new InvalidData("Item is not exists") ; 
		}); 
		// check if there exists sotck assosiated with this item 
		// if(target.getStock() != null) { 
//			 throw new DataCohesionViolation("There exists stock associated with this item , please remove associated stock first and try agin .") ; 
//		 }
		 
		// check for there exists related data 

		if(this.salesRepository.findCountByItemId(target.getId()) > 0) { 
			throw new InvalidData("There exists sales related with this item  , please remove the related sales first and try again.") ; 
		} 
		
		if(this.expensesRepository.findCountByItemId(target.getId()) > 0) { 
			throw new InvalidData("There exists expenses related with this item  , please remove the related expenses first and try again.") ;
		}
		

		this.itemRepository.delete(target);
		

		//return  "Item '" + target .getName() + "' has been deleted successfully"; 
		return Collections.singletonMap("message","Item '" + target.getName() + "' has been deleted successfully" ) ; 
	}


	@ResponseBody
	@ExceptionHandler({UniquenessConstraintViolation.class , InvalidData.class , DataCohesionViolation.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String itemUniqunessViolation(Exception ex ) { 
	 
		return ex.getMessage() ; 
	}

	
	
	
	

}
