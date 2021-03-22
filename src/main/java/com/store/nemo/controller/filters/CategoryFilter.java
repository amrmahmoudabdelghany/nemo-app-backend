package com.store.nemo.controller.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.store.nemo.entity.Category;
import com.store.nemo.service.CategoryService;

@Component
@Order(2)
public class CategoryFilter  implements Filter{
   private static String [] filteredUris = {"/nemo/" ,
		   "/nemo/category/list-all" ,
		   "/nemo/items" ,
		   "/nemo/items/edit-form" ,
		   "/nemo/items/create-form" ,
		   "/nemo/sales" ,
		   "/nemo/perished"} ;
	
   @Autowired
    private CategoryService categoryService  ; 
   
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest) request ; 
		String requestUri = req.getRequestURI() ; 
	     for(String uri : filteredUris) { 
	    	 if(requestUri.equalsIgnoreCase(uri)) { 
	    		 
	    		 List<Category> categories = categoryService.listAllCategories() ; 
	    		 if(categories != null) {
	    		 req.setAttribute("categories",categories );
	    		 }
	    	 }
	     }
		chain.doFilter(request, response);

		
	}
}
