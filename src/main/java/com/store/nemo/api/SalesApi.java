package com.store.nemo.api;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import com.store.nemo.entity.Item;
import com.store.nemo.entity.Sale;
import com.store.nemo.entity.Stock;
import com.store.nemo.exceptions.InvalidData;
import com.store.nemo.models.SaleModel;
import com.store.nemo.models.SaleTransactionModel;
import com.store.nemo.repositories.ItemRepository;
import com.store.nemo.repositories.SalesRepository;
import com.store.nemo.repositories.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sales")
@CrossOrigin("*")
public class SalesApi {
    

  
   @Autowired
   private SalesRepository salesRepository ; 

   @Autowired 
   private ItemRepository itemRepository ; 

   public SalesApi() { 

   }
 
    @PostMapping("/save")
    public Map<String , String> save(@RequestBody SaleModel sale) { 
     
        
        // check data consistency
        
        //check price validation 
        if(sale.getPrice() < 0) { 
            throw new InvalidData("Sale price can not be negative number") ; 
        }

        //check description validation 
        if(sale.getDescription().length() > 300) { 
            throw new InvalidData("Description length must not exceedes 300 character") ; 
        }


        // here we need make check for item info 
        if(sale.getItemId() == null || sale.getItemId() == -1) { 
            throw new InvalidData("Can not determine inteded item") ; 
        }
        Item targetItem  = this.itemRepository.findById(sale.getItemId()).orElseThrow(()->{
          throw new InvalidData("Target item is not exists") ; 
        }) ; 

        if(sale.getQuantity() == null || sale.getQuantity() == -1) { 
            throw new InvalidData("Can not derermine sale quantity ") ; 
        }

        if(sale.getQuantity() <= 0 ) { 
            throw new InvalidData("Invalid sale quantity ") ; 
        }

        //validate sale date ---------
     
        if(LocalDate.now().isBefore(sale.getSaleDate() )) { 
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
        
        
       Sale newSale = this.salesRepository.findById(sale.getSalId()).orElse(new Sale(-1));
    
       boolean create = (newSale.getId() == -1)  ; 
        

       newSale.setDescription(sale.getDescription());
       newSale.setQuantity(sale.getQuantity());
       newSale.setSalePrice(sale.getPrice());   
       newSale.setProfit((sale.getPrice() - (targetItem.getPurchasingPrice() * sale.getQuantity())));
       newSale.setStock(targetStock);
      
       newSale.setSaleDate(sale.getSaleDate()); // after enter old data we must remove this statment and put @Timestamp annotation

       
       newSale = this.salesRepository.save(newSale) ; 
     
       
       //here we can do post validation procedure
        if(create)
        return Collections.singletonMap("message", "The sale has been registered successfully.") ; // and finaly return new saved sale transacion  
        else
        return Collections.singletonMap("message", "The sale has been modified successfully.") ;

        
    }

    @DeleteMapping("/delete/{saleId}")
    public Map<String  , String> deleteSale(@PathVariable Integer saleId) { 
 
        if(saleId == null) { 
            throw new InvalidData("Sale id is not exists.") ; 
        }
        Sale sale = this.salesRepository.findById(saleId).orElseThrow(()->{
            throw new InvalidData("Can not find sale with id " + saleId) ; 
        }) ; 


        this.salesRepository.delete(sale);

        return Collections.singletonMap("message", "The sale with id ' " +sale.getId() + " ' has been deleted successfully.") ; 
    }
}
