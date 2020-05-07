package fr.formation.controller;

import org.apache.commons.pool2.impl.AbandonedConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.formation.dao.IDAOPlayer;
import fr.formation.model.AI;
import fr.formation.model.Game;
import fr.formation.model.Human;

@Controller
public class NewGameController {
	
	@Autowired
	private IDAOPlayer daoPlayer;

	@GetMapping("/newGame")
	public String afficher() {
		Human h = new Human();
		AI ai = new AI();
		Game.getInstance().setHuman(h);
		Game.getInstance().setAI(ai);
		
		return "newGame";
	}
	
	@PostMapping("/newGame")
	@ResponseBody
	public String verifyName(@RequestParam String pseudo) {

		Human h = (Human) this.daoPlayer.findByName(pseudo);
		if (h instanceof Human) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	@PostMapping("/newGameNext")
	public String getName(@RequestParam String pseudo) {
		Human h = Game.getInstance().getHuman();
		
		pseudo = pseudo.toUpperCase();
		h.setName(pseudo);
		
		return "redirect:/choice";
	}
}
