package com.store.nemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/archive")
public class ArchiveController {

	
	
	
	@GetMapping
	public String listAllExpenses() { 
		
		return "archive" ; 
	}
	
}
