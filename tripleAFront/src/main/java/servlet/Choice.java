package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDAOCard;
import dao.IDAOPlayer;
import model.AI;
import model.Card;
import model.Game;
import model.Human;

@WebServlet("/choice")
public class Choice extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean block = (boolean) request.getSession().getAttribute("blockRefresh");
		Human h = Game.getInstance().getHuman();
		
		if (!block) {
			int pts = Game.getInstance().getPassivePoints(), n = Game.getInstance().getCardChoice();
			request.getSession().setAttribute("blockRefresh", true);

			List<Card> listChoice = h.createChoice(n, pts);
			
			request.getSession().setAttribute("pts", pts);
			request.getSession().setAttribute("listChoice", listChoice);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/choice.jsp").forward(request, response);
		} else {
			response.sendRedirect("home");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDAOPlayer daoPlayer = Game.getInstance().getDaoPlayer();
		IDAOCard daoCard = Game.getInstance().getDaoCard();
		
		String choiceStats = request.getParameter("choiceStats");
		List<Card> listChoice = (List<Card>) request.getSession().getAttribute("listChoice");
		
		String[] stats = choiceStats.split("\\|");
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();

		Card c1 = new Card(Integer.parseInt(stats[0]), Integer.parseInt(stats[1]), Integer.parseInt(stats[2]));
		Card c2 = listChoice.get(Integer.parseInt(stats[3])-1);
		Card c3 = listChoice.get(Integer.parseInt(stats[4])-1);
		
		daoCard.insert(c1);
		daoCard.insert(c2);
		daoCard.insert(c3);
				
		h.setCard1(c1);
		h.setCard2(c2);
		h.setCard3(c3);
		
		int pts = 100 - Game.getInstance().getPassivePoints();
		ai.setCard1(ai.createCardRNG(pts));
		ai.setCard2(ai.createCardRNG(pts));
		ai.setCard3(ai.createCardRNG(pts));
		
		daoPlayer.insert(h);
		daoPlayer.insert(ai);
		
		h = (Human) daoPlayer.checkConnect(h.getName());
		ai = (AI) daoPlayer.checkConnect(ai.getName());
		
		h.setIdOpponent(ai.getId());
		ai.setIdOpponent(h.getId());
		ai.setName("AI" + ai.getId());
		
		daoPlayer.update(h);
		daoPlayer.update(ai);
		
		request.getSession().setAttribute("Human", h);
		request.getSession().setAttribute("AI", ai);
		
		response.sendRedirect("matchup");
	}
}
