package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDAOHistory;
import model.Game;
import model.History;

@WebServlet("/home")
public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDAOHistory daoHistory = Game.getInstance().getDaoHistory();
		
		boolean blockRefresh = false;
		request.getSession().setAttribute("blockRefresh", blockRefresh);
		request.getSession().setAttribute("erreur", "N");
		
		List<History> history = daoHistory.selectAll();
		
		if (history.isEmpty()) {
			request.setAttribute("emptyHistory", true);
		} else {
			
			List<History> histoT = new ArrayList<History>(history);
			List<History> histoD = new ArrayList<History>(history);
			List<History> histoWin = new ArrayList<History>(history);
			
			histoT.sort(Comparator.comparing(History::getDmgTaken).thenComparing(History::getEtat));
			histoD.sort(Comparator.comparing(History::getEtat).thenComparing(History::getDmgDealt).reversed());
			histoWin.sort(Comparator.comparing(History::getName));
			
			for (int i = histoWin.size() - 1; i > 0; i--) {
				if (histoWin.get(i).getName().contentEquals(histoWin.get(i - 1).getName())) {
					histoWin.remove(i);
				}
			}
			
			histoWin.sort(Comparator.comparing(History::getNbWin).reversed());
						
			request.setAttribute("emptyHistory", false);
			request.setAttribute("histoT", histoT);
			request.setAttribute("histoD", histoD);
			request.setAttribute("histoWin", histoWin);
			request.setAttribute("history", history);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
