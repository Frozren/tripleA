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
import fr.formation.model.Player;

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
		
		int sceneNum = (int) session.getAttribute("sceneNum");
		int musicNum = (int) session.getAttribute("musicNum");
		
		if (sceneNum == 6) {
			int i = h.RNG(5) + 1;
			session.setAttribute("sceneUrl", "assets/img/battlefield/field/bg" + i + ".jpg");
		} else {
			session.setAttribute("sceneUrl", "assets/img/battlefield/field/bg" + sceneNum + ".jpg");
		}
		
		if (musicNum == 0) {
			int i = h.RNG(6) + 1;
			session.setAttribute("musicUrl", "assets/sound/music" + i + ".mp3");
		} else {
			session.setAttribute("musicUrl", "assets/sound/music" + musicNum + ".mp3");
		}
		
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
