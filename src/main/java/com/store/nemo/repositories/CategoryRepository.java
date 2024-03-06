package com.store.nemo.repositories;

import java.util.List;

import com.store.nemo.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;



@CrossOrigin("*")
@RepositoryRestResource(path= "categories")
public interface CategoryRepository extends JpaRepository<Category , Integer>{
    
    public List<Category> findAll() ; 
    
    @Query("SELECT c FROM Category c , Item i WHERE (i.id = :itemId AND c.id = i.category.id)")
    public Category findByItemId(@Param("itemId") Integer itemId ) ; 

    public boolean existsByName(String name) ; 
    public Category findByName(String name); 
}
