package com.store.nemo.repositories;

import java.time.LocalDate;

import com.store.nemo.entity.Expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(path = "expenses")
@CrossOrigin("*")
public interface ExpensesRepository extends PagingAndSortingRepository<Expense , Integer> {
    

    @Query("SELECT e FROM Expense e  WHERE "+
    "(e.purchaseDate BETWEEN :fromDate AND :toDate ) " +
    "AND (:key != ''  AND( e.description LIKE %:key% " + 
     "OR e.quantity LIKE %:key% " + 
     "OR e.purchasePrice LIKE %:key% " + 
     "OR e.purchaseDate LIKE %:key% "  +  
     "OR e.stock.item.name LIKE %:key% " +  
     "OR e.stock.item.description LIKE %:key%)) ")
Page<Expense> findByDateBetween(
@Param("key") String key  , 
@Param("fromDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
@Param("toDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate , 

Pageable pageable ) ;


@Query("SELECT e FROM Expense e  WHERE (e.stock.item.category.id =:categoryId) AND (e.purchaseDate BETWEEN :fromDate AND :toDate)  " + 
"AND (:key != ''  AND( e.description LIKE %:key% " + 
"OR e.quantity LIKE %:key% " + 
"OR e.purchasePrice LIKE %:key% " + 
"OR e.purchaseDate LIKE %:key% "  +  
"OR e.stock.item.name LIKE %:key% " +  
"OR e.stock.item.description LIKE %:key%)) "
)
Page<Expense> findByCategoryIdBetween(
@Param("categoryId") Integer categoryId ,
@Param("key") String key , 
@Param("fromDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
@Param("toDate") 
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate , 
Pageable pageable) ; 

@Query("SELECT e FROM Expense e  WHERE (e.stock.item.id = :itemId) AND (e.purchaseDate BETWEEN :fromDate AND :toDate) " + 
"AND (:key != ''  AND( e.description LIKE %:key% " + 
"OR e.quantity LIKE %:key% " + 
"OR e.purchasePrice LIKE %:key% " + 
"OR e.purchaseDate LIKE %:key% "  +  
"OR e.stock.item.name LIKE %:key% " +  
"OR e.stock.item.description LIKE %:key%)) ")
Page<Expense> findByItemIdBetween(@Param("itemId") Integer itemId ,
@Param("key") String key , 
@Param("fromDate")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) LocalDate fromDate  ,
@Param("toDate") 
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) LocalDate toDate  
,  Pageable pageable) ; 



     //unused
     @Query("SELECT SUM(e.quantity) FROM Expense e WHERE e.purchaseDate BETWEEN :fromDate  AND :toDate")
     Integer findTotalQuantityBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT SUM(e.quantity) FROM Expense e  WHERE (e.stock.item.category.id = :categoryId)AND (e.purchaseDate BETWEEN :fromDate AND :toDate)  ")
     Integer findTotalQuantityByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT SUM(e.quantity) FROM Expense e WHERE (e.stock.item.id = :itemId) AND e.purchaseDate BETWEEN :fromDate AND :toDate ")
     Integer findTotalQuantityByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


    
     //unused
     @Query("SELECT SUM(e.purchasePrice) FROM Expense e WHERE e.purchaseDate BETWEEN :fromDate  AND :toDate")
     Integer findTotalPriceBetween(
       @Param("fromDate")
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate fromDate ,
       @Param("toDate") 
       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
       LocalDate toDate) ; 

       //unused
     @Query("SELECT SUM(e.purchasePrice) FROM Expense e  WHERE (e.stock.item.category.id = :categoryId)AND (e.purchaseDate BETWEEN :fromDate AND :toDate)  ")
     Integer findTotalPriceByCategoryIdBetween(
     @Param("categoryId") Integer categoryId ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate ,
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


      //unused
     @Query("SELECT SUM(e.purchasePrice) FROM Expense e WHERE (e.stock.item.id = :itemId) AND e.purchaseDate BETWEEN :fromDate AND :toDate ")
     Integer findTotalPriceByItemIdBetween(@Param("itemId")Integer itemId  ,
     @Param("fromDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate fromDate , 
     @Param("toDate")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )LocalDate toDate ) ; 


     @Query("SELECT COUNT(e) FROM Expense e WHERE e.stock.item.id = :itemId")
     Integer findCountByItemId(@Param("itemId")Integer itemId);

}
