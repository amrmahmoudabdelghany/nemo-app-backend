package com.store.nemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/perished")
public class PerishedController {

	
	@GetMapping
	public String listPerishedItems(@RequestParam String category) { 
		return "perished" ;
	}
}
