package fr.formation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IDAOCard;
import fr.formation.dao.IDAOPlayer;
import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.Human;
import fr.formation.model.Player;

@Controller
public class ChoiceController {
	
	@Autowired
	private IDAOPlayer daoPlayer;
	
	@Autowired
	private IDAOCard daoCard;

	@GetMapping("/choice")
	public String afficher(Model model, HttpSession session) {
		Boolean blockRefresh = (Boolean) session.getAttribute("blockRefresh");
		Human h = Game.getInstance().getHuman();
		if (h.getPhase() == 0) {
			if (!blockRefresh) {
				int passivePoints = Game.getInstance().getPassivePoints();
				int nbCardChoice = Game.getInstance().getCardChoice();
				session.setAttribute("blockRefresh", true);
				
				List<Card> listChoice = h.createChoice(nbCardChoice, passivePoints);

				model.addAttribute("phase", h.getPhase());
				model.addAttribute("pts", passivePoints);
				model.addAttribute("nbCard", nbCardChoice);
				model.addAttribute("hp", 100);
				model.addAttribute("atk", 20);
				model.addAttribute("def", 25);

				session.setAttribute("listChoice", listChoice);
			} else {
				return "redirect:/home";
			}
		} else {
			int passivePoints = Game.getInstance().getBonusPts();
			
			h = (Human) this.daoPlayer.findByName(h.getName());
			Optional<Player> op = this.daoPlayer.findById(h.getIdOpponent());
			AI ai = (AI) op.get();
			
			List<Card> listChoice = new ArrayList<>();
			
			listChoice.add(h.getCard2());
			listChoice.add(h.getCard3());
			listChoice.add(ai.getCard1());
			listChoice.add(ai.getCard2());
			listChoice.add(ai.getCard3());
			
			model.addAttribute("phase", h.getPhase());
			model.addAttribute("pts", passivePoints);
			model.addAttribute("nbCard", 5);
			model.addAttribute("hp", h.getCard1().getLife());
			model.addAttribute("atk", h.getCard1().getAtk());
			model.addAttribute("def", h.getCard1().getDef());

			session.setAttribute("listChoice", listChoice);
		}
		return "choice";
	}
	
	@PostMapping("/choice")
	public String getCards(@RequestParam String choiceStats, HttpSession session) {
		int pts;
		
		List<Card> listChoice = (List<Card>) session.getAttribute("listChoice");
		
		String[] stats = choiceStats.split("\\|");
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
		Card c1 = new Card(Integer.parseInt(stats[0]), Integer.parseInt(stats[1]), Integer.parseInt(stats[2]));
		Card c2 = listChoice.get(Integer.parseInt(stats[3])-1);
		Card c3 = listChoice.get(Integer.parseInt(stats[4])-1);
		
		if (h.getPhase() == 0) {
			c2.setId(0);
			c3.setId(0);
		} else {
			c1.setId(h.getCard1().getId());
			c2.setId(h.getCard2().getId());
			c3.setId(h.getCard3().getId());
		}
		
		daoCard.save(c1);
		daoCard.save(c2);
		daoCard.save(c3);
				
		h.setCard1(c1);
		h.setCard2(c2);
		h.setCard3(c3);

		daoPlayer.save(h);
		
		Card c4 = null;
		Card c5 = null;
		Card c6 = null;
		
		if (h.getPhase() == 0) {
			pts = Game.getInstance().getPassivePoints();
			c4 = ai.createCardRNG(50);
			c5 = ai.createCardRNG(50);
			c6 = ai.createCardRNG(50);
		} else if (h.getPhase() == 1) {
			pts = Game.getInstance().getPassivePoints() + Game.getInstance().getBonusPts();
			c4 = ai.createCardRNG(50, ai.getCard1().getId());
			c5 = ai.createCardRNG(50, ai.getCard2().getId());
			c6 = ai.createCardRNG(50, ai.getCard3().getId());
		} else if (h.getPhase() == 2) {
			c4 = new Card(1500, 50, 45);
			c5 = new Card(0, 0, 0);
			c6 = new Card(0, 0, 0);
			c4.setId(ai.getCard1().getId());
			c5.setId(ai.getCard2().getId());
			c6.setId(ai.getCard3().getId());
		}
		
		daoCard.save(c4);
		daoCard.save(c5);
		daoCard.save(c6);
		
		ai.setCard1(c4);
		ai.setCard2(c5);
		ai.setCard3(c6);
		
		daoPlayer.save(ai);
		
		if (h.getPhase() == 0) {
			h.setIdOpponent(ai.getId());
			ai.setIdOpponent(h.getId());
			ai.setName("AI" + ai.getId());
			
			daoPlayer.save(h);
			daoPlayer.save(ai);
		}
		return "redirect:/matchup";
	}
}
