package com.store.nemo.dbtest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.mysql.cj.jdbc.Driver;

public class EncodingTest {

	
	
	
	
	@Test
	public void testInesert() throws ClassNotFoundException, SQLException {
		
		 Class.forName("com.mysql.cj.jdbc.Driver") ; 
		 
		Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/nemo?characterEncoding=utf8" ,"root" , "root") ; 
		
		Statement statment = conn.createStatement() ; 
		boolean accept = statment.execute("INSERT INTO ITEM(item_name , purchase_price , sale_price,category_id) "
				+ "VALUES(' شسيشسي شسينشسيشسمنيتشسمي سشكي تسيش ' ,"
				+ "5.0 , 10.0 , 1) ") ; 
	
		conn.close();
		assertTrue(accept);
		
	}
	public void testRetrive() { 
		
	}
}
