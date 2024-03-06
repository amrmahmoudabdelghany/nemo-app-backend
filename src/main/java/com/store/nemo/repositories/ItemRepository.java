package com.store.nemo.repositories;

import java.util.List;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.store.nemo.entity.Item;




@RepositoryRestResource(path = "items" )
@CrossOrigin("*")
public interface ItemRepository  extends JpaRepository<Item ,  Integer>{

	@Query("SELECT i FROM Item i WHERE i.category.id = :categoryId")
	public List<Item> findAllByCategoryId(@Param("categoryId")Integer categoryId) ; 


	@Query("SELECT i FROM Item i ")
	public List<Item> findAll() ; 
   
	@Query("SELECT i FROM Item i WHERE i.name LIKE %:key% OR i.description LIKE %:key%")
	public Page<Item> findLike(@Param("key")String key , Pageable pageable ) ;

	@Query("SELECT i FROM Item i WHERE i.category.id = :categoryId")
	public Page<Item> findByCategoryId(@Param("categoryId")Integer categoryId , Pageable page ); 
   
	@Query("SELECT i FROM Item i WHERE i.category.id = :categoryId AND (i.name LIKE %:key% OR i.description LIKE %:key%)")
	public Page<Item> findByCategoryIdLike(@Param("categoryId") Integer categoryId  ,@Param("key") String key ,  Pageable pageable) ; 
  
	public boolean existsByName(String name) ; 
	
}
