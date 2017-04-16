package org.collectiveone.ctwitterapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Base {
	
	@RequestMapping("/")
	public String defaultpage() {
		return "forward:/default.html";
	}
	
	
}
