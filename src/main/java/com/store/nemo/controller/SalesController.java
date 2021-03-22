package com.store.nemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sales")
public class SalesController {

	
	@GetMapping()
	public String listSales(@RequestParam String category) { 
		return "sales" ; 
	}
}
