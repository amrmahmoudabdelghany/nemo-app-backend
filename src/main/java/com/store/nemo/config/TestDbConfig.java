package com.store.nemo.config;



import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TestDbConfig {

	
	//@Bean
	public DataSource createDataSource() { 
		
		DataSourceBuilder dataSource = DataSourceBuilder.create() ; 
		return dataSource.driverClassName("com.mysql.cj.jdbc.Driver")
		.url("jdbc:mysql://localhost:3306/nemo")
		.username("root") 
		.password("root")
		.build() ; 
		
		
	}
}
