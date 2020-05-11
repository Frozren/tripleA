package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrologueController {

	@GetMapping({"/","/prologue"})
	public String afficher(HttpSession session) {

		System.out.println("prologue");
		session.setAttribute("difficulte", 2);
		session.setAttribute("sceneNum", 6);
		session.setAttribute("musicNum", 0);
		session.setAttribute("musicVol", 0.05);
		
		return "prologue";
	}
	
}
