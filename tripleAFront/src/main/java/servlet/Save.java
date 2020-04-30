package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.AI;
import fr.formation.model.Game;
import fr.formation.model.Human;
import fr.formation.model.Player;

@WebServlet("/save")
public class Save extends SpringServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String saveId = request.getParameter("saveId");
		List<Player> list = (List<Player>) request.getSession().getAttribute("player");
		
		String name = list.get(Integer.parseInt(saveId)).getName();
		
		Game.getInstance().setHuman((Human) daoPlayer.findByName(name));
		
		Human h = Game.getInstance().getHuman();
		
		Optional<Player> op = daoPlayer.findById(h.getIdOpponent());
		
		Game.getInstance().setAI((AI) op.get());
		
		response.sendRedirect("matchup");
	}
}
