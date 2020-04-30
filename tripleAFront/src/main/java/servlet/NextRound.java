package servlet;

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


@WebServlet("/nextRound")
public class NextRound extends SpringServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Human hbe = Game.getInstance().getHuman();
		String name = hbe.getName();
		Human h = (Human) daoPlayer.findByName(name);
		int idop = h.getIdOpponent();
		Optional<Player> aib = daoPlayer.findById(idop);
		Game.getInstance().setAI((AI) aib.get());
		AI ai = Game.getInstance().getAI();
		request.setAttribute("hp", h.getCard1().getLife());
		request.setAttribute("atk", h.getCard1().getAtk());
		request.setAttribute("def", h.getCard1().getDef());
		

			int pts = Game.getInstance().getPassivePoints();
			request.getSession().setAttribute("blockRefresh", true);

			List<Card> listChoice = new ArrayList<>();
			listChoice.add(h.getCard2());
			listChoice.add(h.getCard3());
			listChoice.add(ai.getCard1());
			listChoice.add(ai.getCard2());
			listChoice.add(ai.getCard3());
			System.out.println("print AI---------------------------------------");
			System.out.println(ai);
			System.out.println("print list cartes---------------------------------------");
			System.out.println(listChoice);
			
			request.setAttribute("pts", pts);
			request.setAttribute("nbCards", 5); //a enlever
			request.getSession().setAttribute("listChoice", listChoice);
			System.out.println("do get next round---------------------------------------");
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/nextRound.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("do post next round---------------------------------------");
		String choiceStats = request.getParameter("choiceStats");
		List<Card> listChoice = (List<Card>) request.getSession().getAttribute("listChoice");
		
		String[] stats = choiceStats.split("\\|");
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
		int idai1 = ai.getCard1().getId();
		int idai2 = ai.getCard2().getId();
		int idai3 = ai.getCard3().getId();

		Card c1 = new Card(Integer.parseInt(stats[0]), Integer.parseInt(stats[1]), Integer.parseInt(stats[2]));
		Card c2 = listChoice.get(Integer.parseInt(stats[3])-1);
		Card c3 = listChoice.get(Integer.parseInt(stats[4])-1);
		
		c1.setId(h.getCard1().getId());
		c2.setId(h.getCard2().getId());
		c3.setId(h.getCard3().getId());
		System.out.println("debut cates-----------------------");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println("fin cates-----------------------");
		
		daoCard.save(c1);
		daoCard.save(c2);
		daoCard.save(c3);
				
		h.setCard1(c1);
		h.setCard2(c2);
		h.setCard3(c3);
		System.out.println("debut humain-----------------------");
		System.out.println(h);
		System.out.println("debut humain-----------------------");
		
		
		
		daoPlayer.save(h);
		
		int pts = 75;
		Card c4 = ai.createCardRNG(pts);
		Card c5 = ai.createCardRNG(pts);
		Card c6 = ai.createCardRNG(pts);
		
		c4.setId(idai1);
		c5.setId(idai2);
		c6.setId(idai3);
		System.out.println("debut cates IA-----------------------");
		System.out.println(c4);
		System.out.println(c5);
		System.out.println(c6);
		System.out.println("fin cates IA-----------------------");
		
		daoCard.save(c4);
		daoCard.save(c5);
		daoCard.save(c6);
		
		ai.setCard1(c4);
		ai.setCard2(c5);
		ai.setCard3(c6);
		System.out.println("debut IA-----------------------");
		System.out.println(ai);
		System.out.println("debut IA-----------------------");
		
		daoPlayer.save(ai);
		System.out.println("fin do get next round---------------------------------------");
		response.sendRedirect("matchup");
	}

}
