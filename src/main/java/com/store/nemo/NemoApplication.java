package com.store.nemo;

import java.io.IOException;
import java.net.URI;

import javax.swing.DesktopManager;

import com.store.nemo.api.ApiConfig;
import com.store.nemo.entity.Category;
import com.store.nemo.entity.Expense;
import com.store.nemo.entity.Item;
import com.store.nemo.entity.PerishedItem;
import com.store.nemo.entity.Sale;
import com.store.nemo.entity.Stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.qos.logback.core.util.ContentTypeUtil;


@SpringBootApplication
public class NemoApplication  implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(NemoApplication.class, args);
 
		try { 
		Runtime.getRuntime().exec("explorer http://localhost");
	  } catch(IOException e) { 
		System.err.println("Failed to open default browser please open your favorite browser and type localhost");
	   }
	     
		
		
		
	}

	
	@Bean
	public ServletRegistrationBean nemoApi(ApplicationContext parentContext) { 

		DispatcherServlet dispatcherServlet = new DispatcherServlet() ; 
		//dispatcherServlet.setDetectAllHandlerMappings(false); // test this statment

		AnnotationConfigWebApplicationContext applicationContext = new 
		AnnotationConfigWebApplicationContext();
		applicationContext.register(ApiConfig.class);

		applicationContext.setParent(parentContext);
		applicationContext.refresh();
        dispatcherServlet.setApplicationContext(applicationContext);

		ServletRegistrationBean servletRegistrationBean = new 
		ServletRegistrationBean(dispatcherServlet, true, "/api/*");
        
		servletRegistrationBean.setName("api");
		servletRegistrationBean.setLoadOnStartup(1);

		return servletRegistrationBean  ; 
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		  
		  //config.disableDefaultExposure();
		  config.exposeIdsFor(Item.class , Category.class , Stock.class , Sale.class , Expense.class  , PerishedItem.class) ; 
	}



}
