package fr.formation.servlet;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.History;

//@WebServlet("/home")
public class Home extends SpringServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		boolean blockRefresh = false;
		request.getSession().setAttribute("blockRefresh", blockRefresh);
		request.getSession().setAttribute("erreur", "N");
		
		List<History> history = daoHistory.findAll();
		
		if (history.size() < 3) {
			request.setAttribute("emptyHistory", true);
		} else if (history.size() >= 3){
			
			List<History> histoT = new ArrayList<History>(history);
			List<History> histoD = new ArrayList<History>(history);
			List<History> histoWin = new ArrayList<History>(history);
			
			histoT = histoT.stream().sorted(
	                comparing(History::getEtat)
	               .thenComparing(reverseOrder(comparing(History::getDmgTaken))).reversed())
	               .collect(Collectors.toList());
				
			histoD = histoD.stream().sorted(
	                comparing(History::getEtat)
	               .thenComparing(comparing(History::getDmgDealt)).reversed())
	               .collect(Collectors.toList());
			
			histoWin = histoWin.stream().sorted(
	                comparing(History::getName)
	                .thenComparing(History::getDateEnd).reversed())
	               .collect(Collectors.toList());
			
			for (int i = histoWin.size() - 1; i > 0; i--) {
				if (histoWin.get(i).getName().contentEquals(histoWin.get(i - 1).getName())) {
					histoWin.remove(i);
				}
			}
			
			histoWin = histoWin.stream().sorted(
	                comparing(History::getNbWin).reversed())
	               .collect(Collectors.toList());
			
			request.setAttribute("emptyHistory", false);
			request.setAttribute("histoT", histoT);
			request.setAttribute("histoD", histoD);
			request.setAttribute("histoWin", histoWin);
		}
		
		history.sort(Comparator.comparing(History::getDateEnd).reversed());
		request.setAttribute("history", history);
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
