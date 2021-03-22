package com.store.nemo.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.store.nemo.config.TestDbConfig;
import com.store.nemo.dao.CategoryDao;
import com.store.nemo.entity.Category;

@SpringBootTest

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestDbConfig.class)
class CatgoryServiceUnitTest {
	 @Mock
	 private EntityManager entityManager ; 
	 @InjectMocks
	 private CategoryDao categoryDao; 
	 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Before All ") ; 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		System.out.println("After All") ; 
	}

	@BeforeEach
	public void setUp() { 
	
	}
	@Test
	void test() {
		
		Category cate = new Category("Category 1") ; 
		 categoryDao.save(cate) ; 
	}

}
