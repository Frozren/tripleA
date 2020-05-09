package fr.formation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.Human;

@Controller
public class MatchupController {

	@GetMapping("/matchup")
	public String afficher(Model model, HttpSession session) {
		session.setAttribute("blockRefresh", false);
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
		int cardDistanceAI = ai.choiceDistance();
		int phase = h.getPhase();
		
		model.addAttribute("cardDistanceAI", cardDistanceAI);
		
		List<Card> cardH = h.deck();
		List<Card> cardAI = ai.deck();
		
		model.addAttribute("cardH", cardH);
		model.addAttribute("cardAI", cardAI);
		model.addAttribute("phase", phase);
		
		return "matchup";
	}
	
	@PostMapping("/matchup")
	public String getPosition(@RequestParam int choicePos, HttpSession session) {
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
		int phase = h.getPhase() + 1;
		h.setPhase(phase);
		
		if (choicePos == 1) {
			h.getCard1().setPosition(true);
		} else if (choicePos == 2) {
			h.getCard2().setPosition(true);
		} else if (choicePos == 3) {
			h.getCard3().setPosition(true);
		}
		session.setAttribute("turn", "0");
		return "redirect:/battleField";
	}
}
