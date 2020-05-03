package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.Human;
import fr.formation.model.Player;

@WebServlet("/save")
public class Save extends SpringServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Player> list = daoPlayer.findAll().stream()
				.filter(player -> player.isTypePlayer())
				.collect(Collectors.toList());
		
		if (list.isEmpty()) {
			request.setAttribute("emptyList", true);
		} else {
			request.setAttribute("emptyList", false);
			request.getSession().setAttribute("player", list);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/save.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int saveId = Integer.parseInt(request.getParameter("saveId"));
		Boolean suppression = Boolean.parseBoolean(request.getParameter("suppression"));
		
		List<Player> list = (List<Player>) request.getSession().getAttribute("player");
		
		if (suppression) {
			Player p1 = list.get(saveId);
			Optional<Player> optionalPlayer = daoPlayer.findById(list.get(saveId).getIdOpponent());
			Player p2 = optionalPlayer.get();
			
			deletePlayer(p1);
			deletePlayer(p2);
			
			list.remove(saveId);
		} else {
			System.out.println("On va sur matchup");
			String name = list.get(saveId).getName();
			
			Game.getInstance().setHuman((Human) daoPlayer.findByName(name));
			
			Human h = Game.getInstance().getHuman();
			
			Optional<Player> op = daoPlayer.findById(h.getIdOpponent());
			
			Game.getInstance().setAI((AI) op.get());
			
			response.sendRedirect("matchup");
		}
	}
	
	protected void deletePlayer(Player p) {
		Card c1 = p.getCard1();
		Card c2 = p.getCard2();
		Card c3 = p.getCard3();
		daoPlayer.deleteById(p.getId());
		daoCard.deleteById(c1.getId());
		daoCard.deleteById(c2.getId());
		daoCard.deleteById(c3.getId());
	}
}
