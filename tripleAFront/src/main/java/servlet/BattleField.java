package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDAOCard;
import dao.IDAOHistory;
import dao.IDAOPlayer;
import dao.jdbc.DAOPlayerJDBC;
import dao.jpa.DAOCardJPA;
import dao.jpa.DAOHistoryJPA;
import dao.jpa.DAOPlayerJPA;
import model.AI;
import model.Card;
import model.Game;
import model.History;
import model.Human;
import model.Player;

@WebServlet("/battleField")
public class BattleField extends HttpServlet {
	//test: http://localhost:8181/tripleAFront/
	static IDAOPlayer daoPlayer = new DAOPlayerJPA();
	static IDAOHistory daoHistory =new DAOHistoryJPA();
	static IDAOCard daoCard = new DAOCardJPA();
	String card = "0";
	static String message = "<p>Cliquez sur une carte pour commencer</p>";
	static String msgDef = "<p>Saisir carte à protéger</p>";
	static int turn = 0;
	static int i=0;
	static int tour=0;
	static int def=0;
	static Boolean catt;
	static Boolean end=false;
	// static Human h = null;
	// static AI ai = null;


	// à changer
	static Card c1 = new Card(137, 33, 25);
	static Card c2 = new Card(150, 20, 25);
	static Card c3 = new Card(124, 34, 37);
	static Card c4 = new Card(126, 26, 43);
	static Card c5 = new Card(148, 22, 25);
	static Card c6 = new Card(136, 30, 29);
	
	//static Human h = Game().getInstance().getHumain();
	//static Human ai = Game().getInstance().getAI();
	static Human h = new Human(1, "Coco", c1, c2, c3, true, 2);
	static AI ai = new AI(2, "IA1", c4, c5, c6, true, 1);
	static List<Card> deckH = h.deck();
	static List<Card> deckAI = ai.deck();
	// à changer
	static int maxhp1 = deckH.get(0).getLife() + deckH.get(1).getLife() + deckH.get(2).getLife();
	static int maxhp2 = deckAI.get(0).getLife() + deckAI.get(1).getLife() + deckAI.get(2).getLife();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("endGame", "start");
		request.getSession().setAttribute("namec1", "Legolas");
		request.getSession().setAttribute("classc1", "Codeur");
		request.getSession().setAttribute("namec2", "Aragorn");
		request.getSession().setAttribute("classc2", "Hacker");
		request.getSession().setAttribute("namec3", "Gimli");
		request.getSession().setAttribute("classc3", "Debugeur");
		request.getSession().setAttribute("namec4", "Smaug");
		request.getSession().setAttribute("classc4", "Omniscient");
		request.getSession().setAttribute("namec5", "Sauron");
		request.getSession().setAttribute("classc5", "Maitre du jeu");
		request.getSession().setAttribute("namec6", "Saroumane");
		request.getSession().setAttribute("classc6", "Mentaliste");
		
		refresh(request);		

		this.getServletContext().getRequestDispatcher("/WEB-INF/battleField.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int card = Integer.parseInt(request.getParameter("card"));

		if (turn == 0) {
			c1.setPosition(true);
			c6.setPosition(true);
			int targeth=h.RNG(3);
			deckH.get(targeth).setProtection(true);
			int targetai=h.RNG(3);
			deckAI.get(targetai).setProtection(true);
			message="<p>Fight!!!</p>"+message;
			tour = h.RNG(2);
			if (tour==0) {message="<p>Saisir carte adverse à attaquer avec c1</p>"+message;def=0;}
			else if (tour==1) {message=msgDef+message;def=1;}
			turn++;
		}
			
		else {
			if (!end) {
				if (i >= 0 && i < 3) {
					if(tour == 0) {

						if (testCard(card) && def==0 && !end) {
							if(deckH.get(i).getLife()>0) {
							message=h.attack(deckH, deckAI, ai, i, (card)-3)+message;
							end=ai.verifyEnd();}
							else {message="<p>La carte c"+Integer.toString(i+1)+" est morte"+message;}

							if(deckAI.get(i).getLife()>0) {message=msgDef+message;def=1;}
							else {nextTurn();}
							}
						else if(!testCard(card) && def==1 && ((card)!=h.getCardProtected(deckH) || h.deck().size() == 1) && !end) {

							if (h.deck().size() != 1) {
								h.protection(deckH,(card));
								}
							else {
								h.deck().get(0).setProtection(false);
								message="<p>Pas de protection avec une carte</p>"+message;
							}
							if(deckAI.get(i).getLife() > 0) {message=ai.attack(deckH, deckAI, ai, i)+message;}
							end=h.verifyEnd();
							nextTurn();
						}
					}
					else {
						System.out.println("intour="+tour);
						if (!testCard(card) && def==1 && ((card)!=h.getCardProtected(deckH) || h.deck().size() == 1) && !end) {
							System.out.println("def=1");
							if (h.deck().size() != 1) {
								h.protection(deckH,(card));
							}
							else {
								h.deck().get(0).setProtection(false);
								message="<p>Pas de protection avec une carte</p>"+message;
							}
							if(deckAI.get(i).getLife() > 0) {message=ai.attack(deckH, deckAI, ai, i)+message;
							end=h.verifyEnd();}
							if(deckH.get(i).getLife()>0) {
							message="<p>Saisir carte adverse a attaquer avec c"+Integer.toString(i+1)+"</p>"+message;
							def=0;}
							else {nextTurn();}
						}
						else if(testCard(card) && def==0 && !end) {
							message=h.attack(deckH, deckAI, ai, i, (card)-3)+message;
							end=ai.verifyEnd();
							nextTurn();	
						}
					}				
				}
			}
		}

		if(ai.verifyEnd()) {
			message="<p>Le joueur a gagné!!!</p>"+message;
			request.getSession().setAttribute("endGame", "win");
			}
		else if(h.verifyEnd()) {
			message="<p>Le joueur a perdu...</p>"+message;
			
			
			History history = new History(Game.getInstance().getHuman(), 3, false, 2569, 3256);
            daoHistory.insert(history);
            List<History> listh = daoHistory.selectAll();
            for (History h : listh) {
                h.setNbWin(daoHistory.countWin(h.getName()));
                daoHistory.update(h);
            }
            deletePlayer(h);
            deletePlayer(ai);
			request.getSession().setAttribute("endGame", "lose");
		}
		refresh(request);
		this.getServletContext().getRequestDispatcher("/WEB-INF/fieldAjax.jsp").forward(request, response);

	}
	
	public static void nextTurn() {
		if(!end) {
		turn++;
		message="<p>Round n°"+Integer.toString(turn)+message;
		tour = h.RNG(2);
		if (i<2) {i++;}
		else{i=0;}
		if(tour==0 && deckH.get(i).getLife()>0) {message="<p>Saisir carte adverse à attaquer avec c"+Integer.toString(i+1)+"</p>"+message;def=0;}
		else {message=msgDef+message;def=1;System.out.println("indef=1");}				
		}
	}
	
	public static Boolean testCard(int card) {
		Boolean catt=false;
		if(tour==0) {
		catt=false;
		if(i!=0) {
			if((deckAI.get(0).getLife()>0 || deckAI.get(1).getLife()>0) && (card==4 || card==5)) {catt=true;}
			else if(deckAI.get(0).getLife()<=0 && deckAI.get(1).getLife()<=0 && (card==4 || card==5 || card==6)) {catt=true;}
		}
		else if(i==0 && (card==4 || card==5 || card==6)) {catt=true;}
		}
		else {
		catt=true;
		if(card==1 || card==2 || card==3) {catt=false;}
		else {System.out.println("Erreur de saisie");}
		}
		return catt;
	}
	
	public static void refresh(HttpServletRequest request) {
		System.out.println("turn="+turn);
		request.getSession().setAttribute("message", message);
		request.getSession().setAttribute("hpb1", (deckH.get(0).getLife() + deckH.get(1).getLife() + deckH.get(2).getLife()) * 100 / maxhp1);
		request.getSession().setAttribute("hpb2", (deckAI.get(0).getLife() + deckAI.get(1).getLife() + deckAI.get(2).getLife()) * 100 / maxhp2);
		
		request.getSession().setAttribute("deckH", deckH);
		request.getSession().setAttribute("deckAI", deckAI);
		
		if(c1.getLife()>0) {request.getSession().setAttribute("disc1", "visible");}
		else {request.getSession().setAttribute("disc1", "hidden");}
		if(c2.getLife()>0) {request.setAttribute("disc2", "visible");}
		else {request.getSession().setAttribute("disc2", "hidden");}
		if(c3.getLife()>0) {request.getSession().setAttribute("disc3", "visible");}
		else {request.getSession().setAttribute("disc3", "hidden");}
		if(c4.getLife()>0) {request.getSession().setAttribute("disc4", "visible");}
		else {request.getSession().setAttribute("disc4", "hidden");}
		if(c5.getLife()>0) {request.getSession().setAttribute("disc5", "visible");}
		else {request.getSession().setAttribute("disc5", "hidden");}
		if(c6.getLife()>0) {request.getSession().setAttribute("disc6", "visible");}
		else {request.getSession().setAttribute("disc6", "hidden");}
		
		if(def==1) {request.setAttribute("cursor", "img/cursor/shield.ico");
		request.getSession().setAttribute("cursorai", "");
		request.getSession().setAttribute("cursorch", "img/cursor/shield.ico");}
		else if(def==0) {request.getSession().setAttribute("cursor", "img/cursor/epeg.ico");
		request.getSession().setAttribute("cursorai", "img/cursor/eped.ico");
		request.getSession().setAttribute("cursorch", "");}
	}
	
	public static void deletePlayer(Player player) {
        Card c1 = player.getCard1();
        Card c2 = player.getCard2();
        Card c3 = player.getCard3();
        daoPlayer.delete(player.getId());
        daoCard.delete(c1.getId());
        daoCard.delete(c2.getId());
        daoCard.delete(c3.getId());
	}
	
}