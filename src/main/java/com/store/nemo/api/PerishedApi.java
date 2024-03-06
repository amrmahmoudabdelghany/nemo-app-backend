package com.store.nemo.api;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import com.store.nemo.entity.Item;
import com.store.nemo.entity.PerishedItem;
import com.store.nemo.entity.Stock;
import com.store.nemo.exceptions.InvalidData;
import com.store.nemo.models.PerishedModel;
import com.store.nemo.repositories.ItemRepository;
import com.store.nemo.repositories.PerishedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("perished")
@CrossOrigin("*")
public class PerishedApi {

    @Autowired 
    private ItemRepository itemRepository ; 

    @Autowired
    private PerishedRepository perishedRepository ; 

    @PostMapping("/save")
    public Map<String , String> save(@RequestBody PerishedModel perished) { 
     
        
        // check data consistency
        
        //check price validation 
        if(perished.getPrice() < 0) { 
            throw new InvalidData("Perished price can not be negative number") ; 
        }

        //check description validation 
        if(perished.getDescription().length() > 300) { 
            throw new InvalidData("Description length must not exceedes 300 character") ; 
        }


        // here we need make check for item info 
        if(perished.getItemId() == null || perished.getItemId() == -1) { 
            throw new InvalidData("Can not determine inteded item") ; 
        }
        Item targetItem  = this.itemRepository.findById(perished.getItemId()).orElseThrow(()->{
          throw new InvalidData("Target item is not exists") ; 
        }) ; 

        if(perished.getQuantity() == null || perished.getQuantity() == -1) { 
            throw new InvalidData("Can not derermine sale quantity ") ; 
        }

        if(perished.getQuantity() <= 0 ) { 
            throw new InvalidData("Invalid sale quantity ") ; 
        }

        //validate sale date ---------
     
        if(LocalDate.now().isBefore(perished.getDate() )) { 
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
        
        
       PerishedItem newPerished = this.perishedRepository.findById(perished.getId()).orElse(new PerishedItem(-1)); 
    
       boolean create = (newPerished.getId() == -1)  ; 
        

       newPerished.setDescription(perished.getDescription());
       newPerished.setQuantity(perished.getQuantity());
       newPerished.setPerishedPrice(perished.getPrice());   
       newPerished.setStock(targetStock);
      
       newPerished.setPerishedDate(perished.getDate()); // after enter old data we must remove this statment and put @Timestamp annotation

       
       newPerished = this.perishedRepository.save(newPerished) ; 
     
       
       //here we can do post validation procedure
        if(create)
        return Collections.singletonMap("message", "The perished item has been registered successfully.") ; // and finaly return new saved sale transacion  
        else
        return Collections.singletonMap("message", "The perished item has been modified successfully.") ;
    }

    @DeleteMapping("/delete/{perishedId}")
    public Map<String  , String> deleteSale(@PathVariable Integer perishedId) { 
 
        if(perishedId == null) { 
            throw new InvalidData("Sale id is not exists.") ; 
        }
        PerishedItem perishedItem = this.perishedRepository.findById(perishedId).orElseThrow(()->{
            throw new InvalidData("Can not find perished item  with id " + perishedId) ; 
        }) ; 


        this.perishedRepository.delete(perishedItem);

        return Collections.singletonMap("message", "The perished item  with id ' " +perishedItem.getId() + " ' has been deleted successfully.") ; 
    }

    
}
