package fr.formation.controller;


import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OptionsController {
		
	@GetMapping({"/options"})
	public String afficher() {
		return "options";
		}
	
	@PostMapping({"/options"})
	public String options(@RequestParam String lum, HttpSession session) {
		
		return "options";
	}