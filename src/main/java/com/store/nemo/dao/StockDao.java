package com.store.nemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.store.nemo.entity.Stock;

@Repository
public class StockDao implements IStockDao {

	@Autowired
	private EntityManager entityManager ;

	@Override
	public Stock save(Stock entity) {
		Session session = entityManager.unwrap(Session.class) ; 
		  session.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public List<Stock> findAll() {
		Session session = entityManager.unwrap(Session.class) ; 
		Query query = session.createQuery("FROM Stock ") ; 
		List<Stock> res = query.getResultList() ;
		return res;
	}

	@Override
	public Stock findById(Integer entityId) {
		   Session session = entityManager.unwrap(Session.class) ; 
		   Stock stock = session.get(Stock.class, entityId) ; 
		return stock;
	}

	@Override
	public void delete(Stock entity) {
		Session session = entityManager.unwrap(Session.class) ; 
		session.delete(entity);
		
	}

	@Override
	public Long findCountByItemId(Integer itemId) {
		Session session = entityManager.unwrap(Session.class) ; 
		
		Query query = session.createQuery("SELECT COUNT(s) FROM Stock s WHERE s.item.id =:itemId") ;
		query.setParameter("itemId", itemId) ; 
		Long res = (Long)query.getSingleResult() ; 
		return res;
	} 
}
