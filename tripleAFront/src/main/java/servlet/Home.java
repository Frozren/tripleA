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
//			List<String> listName = daoHistory.selectNames();
//			List<Long> listWin = new ArrayList<>();
//			int i = 0;
			
			histoT.sort(Comparator.comparing(History::getDmg_taken).thenComparing(History::getEtat));
			histoD.sort(Comparator.comparing(History::getEtat).thenComparing(History::getDmg_dealt).reversed());
			
//			for (String h : listName) {
//				listWin.add(daoHistory.countWin(listName.get(i)));
//				i++;
//			}
						
			request.setAttribute("emptyHistory", false);
			request.setAttribute("histoT", histoT);
			request.setAttribute("histoD", histoD);
			request.setAttribute("history", history);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
