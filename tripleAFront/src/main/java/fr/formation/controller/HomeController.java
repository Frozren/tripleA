package fr.formation.controller;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.formation.dao.IDAOHistory;
import fr.formation.model.History;


@Controller
//@RequestMapping("/home") // PREFIXER TOUS LES MAPPINGS DE CETTE CLASSE
public class HomeController {
	
	@Autowired
	private IDAOHistory daoHistory;
	
	@GetMapping({"/","/prologue"})
	public String afficher() {
		return "prologue";
	}
	
	@GetMapping("/home")
	public String findAllHistory(Model model, HttpSession session) {
		session.setAttribute("blockRefresh", false);
		List<History> history = this.daoHistory.findAll();
		
		if (history.size() < 3) {
			model.addAttribute("emptyHistory", true);
		} else {
			List<History> histoT = new ArrayList<History>(history);
			List<History> histoD = new ArrayList<History>(history);
			List<History> histoWin = new ArrayList<History>(history);
			
			histoT = histoT.stream().sorted(
	                comparing(History::getEtat)
	               .thenComparing(reverseOrder(comparing(History::getDmgTaken))).reversed())
	               .collect(Collectors.toList());
				
			histoD = histoD.stream().sorted(
	                comparing(History::getEtat)
	               .thenComparing(comparing(History::getDmgDealt)).reversed())
	               .collect(Collectors.toList());
			
			histoWin = histoWin.stream().sorted(
	                comparing(History::getName)
	                .thenComparing(History::getDateEnd).reversed())
	               .collect(Collectors.toList());
			
			for (int i = histoWin.size() - 1; i > 0; i--) {
				if (histoWin.get(i).getName().contentEquals(histoWin.get(i - 1).getName())) {
					histoWin.remove(i);
				}
			}
			
			histoWin = histoWin.stream().sorted(
	                comparing(History::getNbWin).reversed())
	               .collect(Collectors.toList());
			
			model.addAttribute("emptyHistory", false);
			model.addAttribute("histoT", histoT);
			model.addAttribute("histoD", histoD);
			model.addAttribute("histoWin", histoWin);
		}

		history.sort(Comparator.comparing(History::getDateEnd).reversed());
		model.addAttribute("history", history);
		
		return "home";
	}
}