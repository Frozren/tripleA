package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AI;
import model.Card;
import model.Game;
import model.Human;

@WebServlet("/matchup")
public class Matchup extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Human h = (Human) request.getSession().getAttribute("Human");
//		AI ai = (AI) request.getSession().getAttribute("AI");
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
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
	}
}