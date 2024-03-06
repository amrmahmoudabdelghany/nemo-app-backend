package com.store.nemo.repositories;

import java.time.LocalDate;

import com.store.nemo.entity.PerishedItem;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(path = "perished" )
@CrossOrigin("*")
public interface PerishedRepository  extends PagingAndSortingRepository< PerishedItem , Integer> {
    
    
    //@Query("SELECT s FROM Sales s")
  //  List<Sales> getAllSeals() ; 
  @Query("SELECT p FROM PerishedItem p  WHERE "+
  "(p.perishedDate BETWEEN :fromDate AND :toDate ) " +
  "AND (:key != ''  AND( p.description LIKE %:key% " + 
   "OR p.quantity LIKE %:key% " + 
   "OR p.perishedPrice LIKE %:key% " + 
   "OR p.perishedDate LIKE %:key% "  +  
   "OR p.stock.item.name LIKE %:key% " +  
   "OR p.stock.item.description LIKE %:key%)) ")
Page<PerishedItem> findByDateBetween(
@Param("key") String key  , 
@Param("fromDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
@Param("toDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate , 

Pageable pageable ) ;


@Query("SELECT p FROM PerishedItem p  WHERE (p.stock.item.category.id =:categoryId) AND (p.perishedDate BETWEEN :fromDate AND :toDate)  " + 
"AND (:key != ''  AND( p.description LIKE %:key% " + 
"OR p.quantity LIKE %:key% " + 
"OR p.perishedPrice LIKE %:key% " + 
"OR p.perishedDate LIKE %:key% "  +  
"OR p.stock.item.name LIKE %:key% " +  
"OR p.stock.item.description LIKE %:key%)) "
)
Page<PerishedItem> findByCategoryIdBetween(
@Param("categoryId") Integer categoryId ,
@Param("key") String key , 
@Param("fromDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
@Param("toDate") 
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate , 
Pageable pageable) ; 

@Query("SELECT p FROM PerishedItem p  WHERE (p.stock.item.id = :itemId) AND (p.perishedDate BETWEEN :fromDate AND :toDate) " + 
"AND (:key != ''  AND( p.description LIKE %:key% " + 
"OR p.quantity LIKE %:key% " + 
"OR p.perishedPrice LIKE %:key% " + 
"OR p.perishedDate LIKE %:key% "  +  
"OR p.stock.item.name LIKE %:key% " +  
"OR p.stock.item.description LIKE %:key%)) ")
Page<PerishedItem> findByItemIdBetween(@Param("itemId") Integer itemId ,
@Param("key") String key , 
@Param("fromDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) LocalDate fromDate  ,
@Param("toDate") 
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) LocalDate toDate  
,  Pageable pageable) ; 


     //unused
     @Query("SELECT SUM(p.quantity) FROM PerishedItem p WHERE p.perishedDate BETWEEN :fromDate  AND :toDate")
     Integer findTotalQuantityBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT SUM(p.quantity) FROM PerishedItem p WHERE (p.stock.item.category.id = :categoryId)AND (p.perishedDate BETWEEN :fromDate AND :toDate)  ")
     Integer findTotalQuantityByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT SUM(p.quantity) FROM PerishedItem p WHERE (p.stock.item.id = :itemId) AND p.perishedDate BETWEEN :fromDate AND :toDate ")
     Integer findTotalQuantityByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


    
     //unused
     @Query("SELECT SUM(p.perishedPrice) FROM PerishedItem p WHERE p.perishedDate BETWEEN :fromDate  AND :toDate")
     Double findTotalPriceBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT SUM(p.perishedPrice) FROM PerishedItem p  WHERE (p.stock.item.category.id = :categoryId)AND (p.perishedDate BETWEEN :fromDate AND :toDate)  ")
     Double findTotalPriceByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT SUM(p.perishedPrice) FROM PerishedItem p WHERE (p.stock.item.id = :itemId) AND p.perishedDate BETWEEN :fromDate AND :toDate ")
     Double findTotalPriceByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 

}
