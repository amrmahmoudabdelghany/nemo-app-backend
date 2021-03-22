package com.store.nemo.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TapSelectionDetection implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request ;
		String requestUri = req.getRequestURI();
        
		if (!requestUri.contains("/content/")) {
		String tapName = "";
        String selectedCategoryId = req.getParameter("category") ;
        
         
		
			if (requestUri.contains("/nemo/items")) {
				tapName = "items";
			} else if (requestUri.contains("/nemo/sales")) {
				tapName = "sales";
			} else if (requestUri.contains("/nemo/perished")) {
				tapName = "perished";
			} else if (requestUri.contains("/nemo/statistics")) {
				tapName = "statistics";
			}else if (requestUri.contains("/nemo/expenses")) { 
				tapName = "expenses" ; 
					
			}else if(requestUri.contains("/nemo/archive")){
				tapName = "archive" ; 
			}else {
			 
				tapName = "stock";
			}
			
			request.setAttribute("preCategoryId", (selectedCategoryId != null)? selectedCategoryId : "*");
			request.setAttribute("preTapName", tapName);
		}

	chain.doFilter(request,response);

}

}
