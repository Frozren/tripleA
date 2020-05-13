package fr.formation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.Game;

@Controller
public class OptionsController {

	@GetMapping("/options")
	public String afficher() {
		return "options";
	}
	
	@PostMapping("/optionsApply")
	public String setOptions(@RequestParam int formDifficulte,
			@RequestParam int formScene,
			@RequestParam int formMusique,
			@RequestParam double formVolume,
			HttpSession session) {
		int difficulte = 0;
		
		if (formDifficulte == 1) {
			difficulte = 3;
		} else if (formDifficulte == 3) {
			difficulte = 1;
		} else {
			difficulte = 2;
		}
		
		Game.getInstance().setPassivePoints(difficulte * 25);
		Game.getInstance().setCardChoice(difficulte * 2 + 1);
		
		session.setAttribute("difficulte", formDifficulte);
		session.setAttribute("sceneNum", formScene);
		session.setAttribute("musicNum", formMusique);
		session.setAttribute("musicVol", formVolume);
		
		return "redirect:/home";
	}
	
	@GetMapping("/optionsReset")
	public String resetOptions(HttpSession session) {
		Game.getInstance().setPassivePoints(50);
		Game.getInstance().setCardChoice(5);

		session.setAttribute("difficulte", 2);
		session.setAttribute("sceneNum", 6);
		session.setAttribute("musicNum", 0);
		session.setAttribute("musicVol", 0.05);
		
		return "redirect:/options";
	}
		
}
