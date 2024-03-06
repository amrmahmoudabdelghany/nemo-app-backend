package com.store.nemo.dao;

import java.util.List;

public interface IDAO<T> {
	
	public T save(T entity) ;
	public List<T> findAll() ; 
	public T findById(Integer entityId ) ; 
	public void delete(T entity) ; 
	
}
