package com.store.nemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.store.nemo.entity.Item;

@Repository
public class ItemDao implements IItemDao {

	@Autowired
	private EntityManager entityManager ; 
	
	
	@Override
	public Item save(Item entity) {
		Session session = entityManager.unwrap(Session.class) ; 
		
		session.saveOrUpdate(entity);
		return entity ; 
	}

	@Override
	public List<Item> findAll() {
		Session session = entityManager.unwrap(Session.class) ; 
		Query query = session.createQuery("FROM Item") ; 
		List<Item> items = query.getResultList() ; 
		return items ; 
	}

	@Override
	public Item findById(Integer entityId) {
		Session session = entityManager.unwrap(Session.class) ; 
		Item resItem = session.get(Item.class, entityId) ; 
		return resItem ; 
 	}

	@Override
	public void delete(Item entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> findItemByCategoryId(Integer cateoryId) {
		Session session = entityManager.unwrap(Session.class) ; 
		Query query = session.createQuery("FROM Item i WHERE i.category.id=:categoryId") ; 
		query.setParameter("categoryId" , cateoryId) ; 
		List<Item> items = query.getResultList() ; 
		return items;
	}
	
	@Override
	public Item findItemByName(String itemName) {
		Session session = entityManager.unwrap(Session.class) ; 
		Query query = session.createQuery("FROM Item i WHERE i.name =:itemName") ;
		query.setParameter("itemName" , itemName) ;
		List<Item> res = query.getResultList() ; 
		if(res.isEmpty()) { 
			return null ; 
		}
		
		return res.get(0);
	}

	@Override
	public Long findCountByCategoryId(Integer categoryId) {
		Session session = entityManager.unwrap(Session.class) ; 
		Query query  = session.createQuery("SELECT COUNT(i) FROM Item i WHERE i.category.id =:categoryId") ;
		query.setParameter("categoryId", categoryId) ; 
		Long res = (Long)query.getSingleResult() ; 	
		return res ; 
	}

}
