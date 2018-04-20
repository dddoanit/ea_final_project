package edu.mum.cs544.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/", "/index", "/home"})
	public String homePage(Model model) {
		return "index";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
	
}
