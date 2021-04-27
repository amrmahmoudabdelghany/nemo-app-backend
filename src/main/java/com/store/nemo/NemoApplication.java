package com.store.nemo;

import com.store.nemo.entity.Category;
import com.store.nemo.entity.Expense;
import com.store.nemo.entity.Item;
import com.store.nemo.entity.PerishedItem;
import com.store.nemo.entity.Sale;
import com.store.nemo.entity.Stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;


@SpringBootApplication
public class NemoApplication  implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(NemoApplication.class, args);
		
	}

	 
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		  config.exposeIdsFor(Item.class , Category.class , Stock.class , Sale.class , Expense.class  , PerishedItem.class) ; 
	}

}
