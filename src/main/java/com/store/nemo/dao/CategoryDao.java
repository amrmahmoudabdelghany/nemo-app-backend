package com.store.nemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.store.nemo.entity.Category;


@Repository
public class CategoryDao implements ICategoryDao{

	@Autowired
	private EntityManager entityManager;

	public CategoryDao() {
		 
	}
	@Override
	public Category save(Category entity) {
		 
		
		Session session = entityManager.unwrap(Session.class) ;
		session.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public Category findById(Integer entityId) {
	
	   Session session = entityManager.unwrap(Session.class) ; 
	   return session.get(Category.class, entityId) ; 		
	}

	@Override
	public void delete(Category entity) {
		Session session = entityManager.unwrap(Session.class) ; 
		 session.delete(entity);
		
	}

	@Override
	public List<Category> findAll() {
		Session session = entityManager.unwrap(Session.class) ; 
		Query query = session.createQuery("FROM Category") ; 
		return query.getResultList() ;
	}
	@Override
	public Category findByName(String name) {
		Session session = entityManager.unwrap(Session.class) ; 
		Query query = session.createQuery("From Category c WHERE c.name=:categoryName") ;
		query.setParameter("categoryName", name);
		List<Category> categories  = query.getResultList() ; 
		if(categories.isEmpty()) { 
			return null ; 
		}
		return categories.get(0) ; 
	} 
	
	
	
	
	
}
