package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.Human;

@WebServlet("/matchup")
public class Matchup extends SpringServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
		int stats = h.getCard1().getAtk() + h.getCard1().getDef() + h.getCard1().getLife();
		
		
		
		List<Card> cardH = h.deck();
		List<Card> cardAI = ai.deck();
		
		request.getSession().setAttribute("cardH", cardH);
		request.getSession().setAttribute("cardAI", cardAI);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/matchup.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String choiceStats = request.getParameter("choicePos");
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
		if (choiceStats.contentEquals("1")) {
			h.getCard1().setPosition(true);
		} else if (choiceStats.contentEquals("2")) {
			h.getCard2().setPosition(true);
		} else if (choiceStats.contentEquals("3")) {
			h.getCard3().setPosition(true);
		}
		
		ai.choiceDistance();
		
		System.out.println("Je suis là");
		
		response.sendRedirect("battleField");
		
		System.out.println("et ici !");
	}
}