package fr.formation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.formation.dao.IDAOCard;
import fr.formation.dao.IDAOPlayer;
import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.Human;
import fr.formation.model.Player;

@Controller
public class NewGameController {
	
	@Autowired
	private IDAOPlayer daoPlayer;
	
	@Autowired
	private IDAOCard daoCard;

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
	public String verifyName(@RequestParam String pseudo, @RequestParam Boolean suppression) {

		if (suppression) {
			Player p1 = this.daoPlayer.findByName(pseudo);
			Optional<Player> optionalPlayer = this.daoPlayer.findById(p1.getIdOpponent());
			Player p2 = optionalPlayer.get();
			
			deletePlayer(p1);
			deletePlayer(p2);
		}
		
		Human h = (Human) this.daoPlayer.findByName(pseudo);
		if (h instanceof Human) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	@PostMapping("/newGameNext")
	public String getName(@RequestParam String pseudo, @RequestParam Boolean charge) {
		
		if (!charge) {
			Human h = Game.getInstance().getHuman();
			
			pseudo = pseudo.toUpperCase();
			h.setName(pseudo);
			
			return "redirect:/choice";
		} else {
			Game.getInstance().setHuman((Human) this.daoPlayer.findByName(pseudo));
			
			Human h = Game.getInstance().getHuman();
			Optional<Player> optionalPlayer = this.daoPlayer.findById(h.getIdOpponent());
			
			Game.getInstance().setAI((AI) optionalPlayer.get());
			
			return "redirect:/matchup";
		}
	}
	
	private void deletePlayer(Player p) {
		Card c1 = p.getCard1();
		Card c2 = p.getCard2();
		Card c3 = p.getCard3();
		this.daoPlayer.deleteById(p.getId());
		this.daoCard.deleteById(c1.getId());
		this.daoCard.deleteById(c2.getId());
		this.daoCard.deleteById(c3.getId());
	}
}
