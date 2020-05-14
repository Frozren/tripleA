package fr.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.formation.annotation.IsAdmin;
import fr.formation.dao.IDAOCard;
import fr.formation.dao.IDAOPlayer;
import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.Human;
import fr.formation.model.Player;

@Controller
public class SaveController {
	
	@Autowired
	private IDAOPlayer daoPlayer;
	
	@Autowired
	private IDAOCard daoCard;

	@GetMapping("/save")
	public String afficher(Model model) {
		List<Player> list = this.daoPlayer.findByTypePlayer(true);
		
		if (list.isEmpty()) {
			model.addAttribute("emptyList", true);
		} else {
			model.addAttribute("emptyList", false);
			model.addAttribute("player", list);
		}
		return "save";
	}
	
	@PostMapping("/save")
	@ResponseBody
	@IsAdmin
	public void delete(Model model, @RequestParam int saveId) {
		List<Player> list = this.daoPlayer.findByTypePlayer(true);
		Player p1 = list.get(saveId);
		Optional<Player> optionalPlayer = this.daoPlayer.findById(p1.getIdOpponent());
		Player p2 = optionalPlayer.get();

		deletePlayer(p1);
		deletePlayer(p2);
	}
	
	@PostMapping("/saveNext")
	public String getSave(@RequestParam int saveId) {
		
		List<Player> list = this.daoPlayer.findByTypePlayer(true);
		String name = list.get(saveId).getName();
		
		Game.getInstance().setHuman((Human) this.daoPlayer.findByName(name));
		
		Human h = Game.getInstance().getHuman();
		Optional<Player> optionalPlayer = this.daoPlayer.findById(h.getIdOpponent());
		
		Game.getInstance().setAI((AI) optionalPlayer.get());
		
		return "redirect:/matchup";
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
