package com.store.nemo.api;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import com.store.nemo.entity.Expense;
import com.store.nemo.entity.PerishedItem;
import com.store.nemo.entity.Sale;
import com.store.nemo.entity.Stock;
import com.store.nemo.exceptions.InvalidData;
import com.store.nemo.exceptions.UniquenessConstraintViolation;
import com.store.nemo.models.PerishedItemModel;
import com.store.nemo.models.PerishedModel;
import com.store.nemo.models.PurchaseModel;
import com.store.nemo.models.SaleTransactionModel;
import com.store.nemo.repositories.ExpensesRepository;
import com.store.nemo.repositories.PerishedRepository;
import com.store.nemo.repositories.SalesRepository;
import com.store.nemo.repositories.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stocks")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
public class StockApi {
 
    
@Autowired
private StockRepository stockRepository ; 

@Autowired
private ExpensesRepository expRepository ; 

@Autowired
private SalesRepository salesRepository ; 

@Autowired
private PerishedRepository perishedRepository ; 
 
    @PostMapping("/sell")
    public Map<String , String> sell(@RequestBody SaleTransactionModel saleModel ){ 


        if(saleModel.getDescription().length() > 300) { 
            throw new InvalidData("Description length must not exceedes 300 character") ; 
        }
        
        if(saleModel.getPrice() < 0) { 
            throw new InvalidData("Sale Price can not be negative number") ; 
        }
        

        Stock targetStock = stockRepository.findById(saleModel.getStockId()).orElseThrow(()->{
            throw new InvalidData("Can not find target stock ") ; 
        }); 

        if(saleModel.getQuantity() <= 0) { 
            throw new InvalidData("Sale quantity must be at least 1 unit") ; 
        }

        if(targetStock.getQuantity() == 0) { 
            throw new InvalidData("Stock is empty !") ; 
        }


        if(saleModel.getQuantity() > targetStock.getQuantity()) { 
            throw new InvalidData("Description length must not exceedes 300 character") ; 
        }

        Sale sale = new Sale() ; 
        sale.setDescription(saleModel.getDescription());
        sale.setQuantity(saleModel.getQuantity());
        sale.setSaleDate(LocalDate.now());
        sale.setSalePrice(saleModel.getPrice());
        sale.setProfit((saleModel.getPrice() - (saleModel.getQuantity() * (targetStock.getItem().getPurchasingPrice())) ));
        sale.setStock(targetStock);

        this.salesRepository.save(sale) ; 

        targetStock.setQuantity(targetStock.getQuantity() - saleModel.getQuantity());
        this.stockRepository.save(targetStock); 

        return Collections.singletonMap("message","Sale registration has been successfully" ) ; 
    }

    @PostMapping("/buy")
    public Map<String , String> buy(@RequestBody PurchaseModel purchaseModel) { 
   
          if(purchaseModel.getDescription().length() > 300) { 
            throw new InvalidData("Description length must not exceedes 300 character") ; 
          }
       

          Stock targetStock = stockRepository.findById(purchaseModel.getStockId()).orElseThrow(()->{
              throw new InvalidData("Can not find target stock ") ; 
          }); 

          if(purchaseModel.getQuantity() <= 0) { 
            throw new InvalidData("Quantity must be at least 1 unit") ; 
          }
     
          Expense exp = new Expense() ; 

          exp.setDescription(purchaseModel.getDescription());
          exp.setPurchaseDate(LocalDate.now());
          double purchasePrice = purchaseModel.getQuantity() * targetStock.getItem().getPurchasingPrice() ; 
          exp.setPurchasePrice(purchasePrice);
          exp.setQuantity(purchaseModel.getQuantity());
          exp.setStock(targetStock);
          this.expRepository.save(exp) ; 
          targetStock.setQuantity(targetStock.getQuantity() + purchaseModel.getQuantity());
          this.stockRepository.save(targetStock) ; 

        return Collections.singletonMap("message","Purchase registration has been successfully" ) ; 
    }
    
    @PostMapping("/perished")
    public Map<String , String> perished(@RequestBody PerishedItemModel perisedModel) { 


       
        if(perisedModel.getDescription().length() > 300) { 
             throw new InvalidData("Description length must not exceedes 300 character") ;
        }

        Stock targetStock = stockRepository.findById(perisedModel.getStockId()).orElseThrow(()->{
            throw new InvalidData("Can not find target stock ") ; 
        }); 

        if(targetStock.getQuantity() == 0) { 
            throw new InvalidData("Stock is empty !") ; 
        }
        
        if(perisedModel.getQuantity() > targetStock.getQuantity()) {
            throw new InvalidData("Required quantity exceeds available quantity") ; 
        }
        
        PerishedItem perishedItem = new PerishedItem(); 
         
        perishedItem.setDescription(perisedModel.getDescription());
        perishedItem.setPerishedDate(LocalDate.now());
        double perishedPrice = perisedModel.getQuantity() * targetStock.getItem().getPurchasingPrice() ; 
        perishedItem.setPerishedPrice(perishedPrice);
        
        perishedItem.setQuantity(perisedModel.getQuantity());

        perishedItem.setStock(targetStock);

        perishedItem  = this.perishedRepository.save(perishedItem) ; 

        targetStock.decreaseBy(perishedItem.getQuantity()) ; 

        this.stockRepository.save(targetStock) ; 
        

         return Collections.singletonMap("message","Purchase registration has been successfully" ) ; 
    }


	@ResponseBody
	@ExceptionHandler({ InvalidData.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String itemUniqunessViolation(Exception ex ) { 
	 
		return ex.getMessage() ; 
	}

}
