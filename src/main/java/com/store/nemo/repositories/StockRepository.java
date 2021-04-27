package com.store.nemo.repositories;

import java.util.List;

import com.store.nemo.entity.Stock;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;





@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(path = "stocks")
public interface StockRepository extends PagingAndSortingRepository<Stock , Integer> { 

    
 @Query("SELECT s FROM Stock s , Item i WHERE (i.category.id = :categoryId )AND s.id = i.stock.id ")
 Page<List<Stock>> findByItemCategory(@Param("categoryId") Integer categoryId , Pageable pagable) ; 
 //java.time.LocalDateTime
 @Query("SELECT s FROM Stock s , Item i WHERE (i.stock.id=s.id )AND (i.name LIKE %:key% OR i.description LIKE %:key% OR s.quantity  LIKE %:key% OR s.modifyDate LIKE %:key%)")
 Page<List<Stock>> findLike(@Param("key")String key , Pageable pagable) ; 

 @Query("SELECT s FROM Stock s , Item i WHERE (i.category.id = :categoryId AND s.id = i.stock.id) AND (i.name LIKE %:key% OR i.description LIKE %:key% OR s.quantity  LIKE %:key% OR s.modifyDate LIKE %:key%)")
 Page<List<Stock>> findByCategoryIdLike(@Param("categoryId") Integer categoryId , @Param("key")String key , Pageable pageable) ; 
 
 @Query("SELECT SUM(s.quantity) FROM Stock s ")
 Integer findTotalUnits() ; 

 @Query("SELECT SUM(s.quantity * s.item.purchasingPrice) FROM Stock s")
 Integer findTotalPurchasePrice() ; 

 @Query("SELECT SUM(s.quantity * (s.item.sallingPrice - s.item.purchasingPrice)) FROM Stock s")
 Integer findTotalExpProfits()  ; 

}