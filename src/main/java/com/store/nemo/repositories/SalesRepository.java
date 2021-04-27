package com.store.nemo.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.store.nemo.entity.Sale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;



@RepositoryRestResource(path = "sales" )
@CrossOrigin("http://localhost:4200")
public  interface SalesRepository extends PagingAndSortingRepository<Sale , Integer> {


    //@Query("SELECT s FROM Sales s")
  //  List<Sales> getAllSeals() ; 
    @Query("SELECT s FROM Sale s  WHERE "+
          "(s.saleDate BETWEEN :fromDate AND :toDate ) " +
          "AND (:key != ''  AND( s.description LIKE %:key% " + 
           "OR s.quantity LIKE %:key% " + 
           "OR s.salePrice LIKE %:key% " + 
           "OR s.saleDate LIKE %:key% "  +  
           "OR s.stock.item.name LIKE %:key% " +  
           "OR s.stock.item.description LIKE %:key%)) ")
    Page<Sale> findBySaleDateBetween(
      @Param("key") String key  , 
      @Param("fromDate")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
      @Param("toDate")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate , 
    
      Pageable pageable ) ;
    
    
    @Query("SELECT s FROM Sale s  WHERE (s.stock.item.category.id =:categoryId) AND (s.saleDate BETWEEN :fromDate AND :toDate)  " + 
    "AND (:key != ''  AND( s.description LIKE %:key% " + 
    "OR s.quantity LIKE %:key% " + 
    "OR s.salePrice LIKE %:key% " + 
    "OR s.saleDate LIKE %:key% "  +  
    "OR s.stock.item.name LIKE %:key% " +  
    "OR s.stock.item.description LIKE %:key%)) "
    )
    Page<Sale> findByCategoryIdBetween(
      @Param("categoryId") Integer categoryId ,
      @Param("key") String key , 
      @Param("fromDate")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
      @Param("toDate") 
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate , 
     Pageable pageable) ; 

    @Query("SELECT s FROM Sale s  WHERE (s.stock.item.id = :itemId) AND (s.saleDate BETWEEN :fromDate AND :toDate) " + 
    "AND (:key != ''  AND( s.description LIKE %:key% " + 
    "OR s.quantity LIKE %:key% " + 
    "OR s.salePrice LIKE %:key% " + 
    "OR s.saleDate LIKE %:key% "  +  
    "OR s.stock.item.name LIKE %:key% " +  
    "OR s.stock.item.description LIKE %:key%)) ")
    Page<Sale> findByItemIdBetween(@Param("itemId") Integer itemId ,
    @Param("key") String key , 
    @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) LocalDate fromDate  ,
     @Param("toDate") 
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) LocalDate toDate  
     ,  Pageable pageable) ; 



     // unused
     @Query("SELECT SUM(s.salePrice) FROM Sale s WHERE s.saleDate BETWEEN :fromDate  AND :toDate")
     Double findTotalPriceBetween(
       @Param("fromDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate) ;
     //unused
     @Query("SELECT SUM(s.salePrice) FROM Sale s WHERE (s.stock.item.category.id = :categoryId) AND (s.saleDate BETWEEN :fromDate AND :toDate)  ")
     Double findTotalPriceByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
    // @Query("SELECT SUM(s.salePrice) FROM Sale s , Item i WHERE (i.id = :itemId AND s.stock.id = i.stock.id) AND s.saleDate BETWEEN :fromDate AND :toDate ")
    @Query("SELECT SUM(s.salePrice) FROM Sale s WHERE (s.stock.item.id = :itemId) AND s.saleDate BETWEEN :fromDate AND :toDate ")
    Double findTotalPriceByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 

     //unused
     @Query("SELECT SUM(s.quantity) FROM Sale s WHERE s.saleDate BETWEEN :fromDate  AND :toDate")
     Integer findTotalQuantityBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT SUM(s.quantity) FROM Sale s  WHERE (s.stock.item.category.id = :categoryId)AND (s.saleDate BETWEEN :fromDate AND :toDate)  ")
     Integer findTotalQuantityByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT SUM(s.quantity) FROM Sale s WHERE (s.stock.item.id = :itemId) AND s.saleDate BETWEEN :fromDate AND :toDate ")
     Integer findTotalQuantityByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 

     

     //unused
     @Query("SELECT SUM(s.profit) FROM Sale s WHERE s.saleDate BETWEEN :fromDate  AND :toDate")
     Double findTotalProfitBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT SUM(s.profit) FROM Sale s  WHERE (s.stock.item.category.id = :categoryId) AND (s.saleDate BETWEEN :fromDate AND :toDate)  ")
     Double findTotalProfitByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT SUM(s.profit) FROM Sale s WHERE (s.stock.item.id = :itemId) AND s.saleDate BETWEEN :fromDate AND :toDate ")
     Double findTotalProfitByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 



     //unused
     @Query("SELECT AVG(s.salePrice) FROM Sale s WHERE s.saleDate BETWEEN :fromDate  AND :toDate")
     Double findAvgPriceBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT AVG(s.salePrice) FROM Sale s  WHERE (s.stock.item.category.id = :categoryId) AND (s.saleDate BETWEEN :fromDate AND :toDate)  ")
     Double findAvgPriceByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT AVG(s.salePrice) FROM Sale s  WHERE (s.stock.item.id = :itemId) AND s.saleDate BETWEEN :fromDate AND :toDate ")
     Double findAvgPriceByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


     //unused
     @Query("SELECT AVG(s.profit) FROM Sale s WHERE s.saleDate BETWEEN :fromDate  AND :toDate")
     Double findAvgProfitBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT AVG(s.profit) FROM Sale s  WHERE (s.stock.item.category.id = :categoryId) AND (s.saleDate BETWEEN :fromDate AND :toDate)  ")
     Double findAvgProfitByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT AVG(s.profit) FROM Sale s  WHERE (s.stock.item.id = :itemId) AND s.saleDate BETWEEN :fromDate AND :toDate ")
     Double findAvgProfitByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 

     @Query("SELECT COUNT(s) FROM Sale s WHERE s.stock.item.id = :itemId")
     Integer findCountByItemId(@Param("itemId")Integer itemId );
    //@Query("SELECT s  FROM Sales s ,  ")
   // Page<Sales> findAllByItemId(@Param("itemId") Integer itemId , Pageable pageable) ; 
}

