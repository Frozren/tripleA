package fr.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.Human;
import fr.formation.model.Player;

//@WebServlet("/choice")
public class Choice extends SpringServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean block = (boolean) request.getSession().getAttribute("blockRefresh");
		Human h = Game.getInstance().getHuman();
		
		if (h.getPhase() == 0) {
			if (!block) {
				int passivePoints = Game.getInstance().getPassivePoints(), nbCardChoice = Game.getInstance().getCardChoice();
				request.getSession().setAttribute("blockRefresh", true);

				List<Card> listChoice = h.createChoice(nbCardChoice, passivePoints);

				request.setAttribute("phase", h.getPhase());
				request.setAttribute("pts", passivePoints);
				request.setAttribute("nbCards", nbCardChoice);
				request.setAttribute("hp", 100);
				request.setAttribute("atk", 20);
				request.setAttribute("def", 25);
				request.getSession().setAttribute("listChoice", listChoice);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/choice.jsp").forward(request, response);
			} else {
				response.sendRedirect("home");
			}
		} else {
			int passivePoints = Game.getInstance().getBonusPts();
			
			h = (Human) daoPlayer.findByName(h.getName());
			Optional<Player> op = daoPlayer.findById(h.getIdOpponent());
			AI ai = (AI) op.get();
			
			List<Card> listChoice = new ArrayList<>();
			
			listChoice.add(h.getCard2());
			listChoice.add(h.getCard3());
			listChoice.add(ai.getCard1());
			listChoice.add(ai.getCard2());
			listChoice.add(ai.getCard3());

			request.setAttribute("phase", h.getPhase());
			request.setAttribute("pts", passivePoints);
			request.setAttribute("nbCards", 5);
			request.setAttribute("hp", h.getCard1().getLife());
			request.setAttribute("atk", h.getCard1().getAtk());
			request.setAttribute("def", h.getCard1().getDef());
			request.getSession().setAttribute("listChoice", listChoice);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/choice.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int pts = 0;
		String choiceStats = request.getParameter("choiceStats");
		List<Card> listChoice = (List<Card>) request.getSession().getAttribute("listChoice");
		
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
			c4 = ai.createCardRNG(pts);
			c5 = ai.createCardRNG(pts);
			c6 = ai.createCardRNG(pts);
		} else if (h.getPhase() == 1) {
			pts = Game.getInstance().getPassivePoints() + Game.getInstance().getBonusPts();
			c4 = ai.createCardRNG(pts, ai.getCard1().getId());
			c5 = ai.createCardRNG(pts, ai.getCard2().getId());
			c6 = ai.createCardRNG(pts, ai.getCard3().getId());
		} else if (h.getPhase() == 2) {
			c4 = new Card(500, 50, 50);
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
		
		response.sendRedirect("matchup");
	}
}
