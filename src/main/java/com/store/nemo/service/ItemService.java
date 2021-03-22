package com.store.nemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.nemo.dao.IItemDao;
import com.store.nemo.dao.ItemDao;
import com.store.nemo.entity.Item;
import com.store.nemo.exceptions.UniquenessConstraintViolation;

@Service
public class ItemService implements IItemService {

	@Autowired
	private IItemDao itemDao;

	
	public ItemService() {
		System.out.println("On Create item service ") ; 
	}
	@Override
	@Transactional
	public List<Item> listAllItems() {
		return itemDao.findAll();
	}

	@Override
	@Transactional
	public List<Item> listItemsByCategoryId(Integer categoryId) {
		return this.itemDao.findItemByCategoryId(categoryId);
	}

	@Override
	@Transactional
	public Item getItemById(Integer itemId) {

		return itemDao.findById(itemId);
	}

	@Override
	@Transactional
	public void save(Item item) {


		
		Item itemByName = this.itemDao.findItemByName(item.getName()) ; 
		// if there exists other item with the same name 
		if ( itemByName != null && 
				itemByName.getId()!= item.getId() ) { 
			
			throw new UniquenessConstraintViolation("Item name must be unique");
		}else if (itemByName != null && itemByName.getId() == item.getId()) { 
			itemByName.setCategory(item.getCategory());
			//itemByName.setPurchasePrice(item.getPurchasePrice());
			//itemByName.setSalePrice(item.getSalePrice());
			this.itemDao.save(itemByName) ; 
			return ;
		}
		
			this.itemDao.save(item) ; 
		
		
	}

	@Override
	@Transactional
	public void removeItem(Integer itemId) { 
		
		
	}
}
