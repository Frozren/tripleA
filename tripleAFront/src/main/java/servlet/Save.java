package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDAOPlayer;
import model.AI;
import model.Game;
import model.Human;
import model.Player;

@WebServlet("/save")
public class Save extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("début2");
		
		IDAOPlayer daoPlayer = Game.getInstance().getDaoPlayer();
		
		List<Player> list = daoPlayer.selectAll();
		
		for (Player p : list) {
			System.out.println(p);
		}
		
		System.out.println();
		
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
		
		Human h = Game.getInstance().getHuman();
		AI ai = Game.getInstance().getAI();
		
		String saveId = request.getParameter("saveId");
		List<Player> list = (List<Player>) request.getSession().getAttribute("player");
		
		String name = list.get(Integer.parseInt(saveId)).getName();
		
		h = (Human) daoPlayer.checkConnect(name);
		ai = (AI) daoPlayer.selectById(h.getIdOpponent());
		
		request.getSession().setAttribute("Human", h);
		request.getSession().setAttribute("AI", ai);
		
		response.sendRedirect("matchup");
	}
}
