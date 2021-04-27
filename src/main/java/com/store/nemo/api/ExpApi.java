package com.store.nemo.api;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import com.store.nemo.entity.Expense;
import com.store.nemo.entity.Item;
import com.store.nemo.entity.Stock;
import com.store.nemo.exceptions.InvalidData;
import com.store.nemo.models.ExpenseModel;
import com.store.nemo.repositories.ExpensesRepository;
import com.store.nemo.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("expenses")
@CrossOrigin("*")
public class ExpApi {
    

    @Autowired
    private ItemRepository itemRepository ; 
    @Autowired
    private ExpensesRepository expenseRepository ; 


    @PostMapping("/save")
    public Map<String , String> save(@RequestBody ExpenseModel exp) { 
     
        
        // check data consistency
        
        //check price validation 
        if(exp.getPrice() < 0) { 
            throw new InvalidData("Sale price can not be negative number") ; 
        }

        //check description validation 
        if(exp.getDescription().length() > 300) { 
            throw new InvalidData("Description length must not exceedes 300 character") ; 
        }


        // here we need make check for item info 
        if(exp.getItemId() == null || exp.getItemId() == -1) { 
            throw new InvalidData("Can not determine inteded item") ; 
        }
        Item targetItem  = this.itemRepository.findById(exp.getItemId()).orElseThrow(()->{
          throw new InvalidData("Target item is not exists") ; 
        }) ; 

        if(exp.getQuantity() == null || exp.getQuantity() == -1) { 
            throw new InvalidData("Can not derermine sale quantity ") ; 
        }

        if(exp.getQuantity() <= 0 ) { 
            throw new InvalidData("Invalid sale quantity ") ; 
        }

        //validate sale date ---------
     
        if(LocalDate.now().isBefore(exp.getExpDate() )) { 
           throw new InvalidData("Invalid date") ; 
        } 
        

     
        //verify existence of stock into database 
       /// Stock targetStock = this.stockRepository.findById(sale.getStockId()).orElseThrow(()->{
        // throw new InvalidData("Can not find item stock for the target sale transaction") ; 
        //});
       
        Stock targetStock  = targetItem.getStock() ; 
        
        //if flow reach to this point then 
        //description is valid 
        //price is valid  
        //quantity is valid 
        
        
       Expense newExp = this.expenseRepository.findById(exp.getExpId()).orElse(new Expense(-1)); 
    
       boolean create = (newExp.getId() == -1)  ; 
        

       newExp.setDescription(exp.getDescription());
       newExp.setQuantity(exp.getQuantity());
       newExp.setPurchasePrice(exp.getPrice());   
       newExp.setStock(targetStock);
      
       newExp.setPurchaseDate(exp.getExpDate()); // after enter old data we must remove this statment and put @Timestamp annotation

       
       newExp = this.expenseRepository.save(newExp) ; 
     
       
       //here we can do post validation procedure
        if(create)
        return Collections.singletonMap("message", "The sale has been registered successfully.") ; // and finaly return new saved sale transacion  
        else
        return Collections.singletonMap("message", "The sale has been modified successfully.") ;
    }

    @DeleteMapping("/delete/{expId}")
    public Map<String  , String> deleteSale(@PathVariable Integer expId) { 
 
        if(expId == null) { 
            throw new InvalidData("Expense id is not exists.") ; 
        }
        Expense exp = this.expenseRepository.findById(expId).orElseThrow(()->{
            throw new InvalidData("Can not find sale with id " + expId) ; 
        }) ; 


        this.expenseRepository.delete(exp);

        return Collections.singletonMap("message", "The Expense with id ' " +exp.getId() + " ' has been deleted successfully.") ; 
    }

}
