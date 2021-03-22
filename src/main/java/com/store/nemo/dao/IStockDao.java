package com.store.nemo.dao;

import com.store.nemo.entity.Stock;

public interface IStockDao extends IDAO<Stock> {
	
	public Long findCountByItemId(Integer itemId) ; 

}
