package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDAOPlayer;
import fr.formation.model.AI;
import fr.formation.model.Game;
import fr.formation.model.Human;
import fr.formation.model.Player;

@WebServlet("/save")
public class Save extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		IDAOPlayer daoPlayer = Game.getInstance().getDaoPlayer();
		
		List<Player> list = daoPlayer.findAll();
		
		if (list.isEmpty()) {
			request.setAttribute("emptyList", true);
		} else {
			request.setAttribute("emptyList", false);
			request.getSession().setAttribute("player", list);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/save.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDAOPlayer daoPlayer = Game.getInstance().getDaoPlayer();
		
		String saveId = request.getParameter("saveId");
		List<Player> list = (List<Player>) request.getSession().getAttribute("player");
		
		String name = list.get(Integer.parseInt(saveId)).getName();
		
		Game.getInstance().setHuman((Human) daoPlayer.checkConnect(name));
		
		Human h = Game.getInstance().getHuman();
		
		Game.getInstance().setAI((AI) daoPlayer.findById(h.getIdOpponent()));
		
		response.sendRedirect("matchup");
	}
}
