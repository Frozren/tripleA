package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDAOPlayer;
import fr.formation.model.Game;
import fr.formation.model.Human;

@WebServlet("/newGame")
public class NewGame extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/newGame.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDAOPlayer daoPlayer = Game.getInstance().getDaoPlayer();
		
		String name = request.getParameter("pseudo").toUpperCase();
		Human h = Game.getInstance().getHuman();
		h = (Human) daoPlayer.checkConnect(name);
		
		if (h instanceof Human) {
			request.getSession().setAttribute("erreur", "Y");
			doGet(request, response);
		} else {
			h = Game.getInstance().getHuman();
			h.setName(name);
			request.getSession().setAttribute("name", name);
			response.sendRedirect("choice");
		}
	}
}
