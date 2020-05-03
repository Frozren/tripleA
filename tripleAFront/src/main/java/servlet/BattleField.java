package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.History;
import fr.formation.model.Human;
import fr.formation.model.Player;

@WebServlet("/battleField")
public class BattleField extends SpringServlet {
	//test: http://localhost:8181/tripleAFront/
	static String message;
	static String msgDef = "<p>Saisir la carte à protéger</p>";
	static int turn=0;
	static int card;
	static int i;
	static int tour;
	static int def;
	static Boolean end;
	static Human h = new Human();
	static AI ai = new AI();
	static List<Card> deckH = new ArrayList<>();
	static List<Card> deckAI = new ArrayList<>();
	static int maxhp1;
	static int maxhp2;
	static List<Card> deckHAff = new ArrayList<>();
	static List<Card> deckAIAff = new ArrayList<>();
	static String c1, id1;
	static String c2, id2;
	static String c3, id3;
	static String c4, id4;
	static String c5, id5;
	static String c6, id6;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(turn==0) {
			message = "<p>Cliquez sur une carte pour commencer</p>";
			i=0;
			tour=0;
			def=0;
			end=false;
			h = Game.getInstance().getHuman();
			
			ai = Game.getInstance().getAI();
			deckH = h.deck();
			deckAI = ai.deck();
			maxhp1 = deckH.get(0).getLife() + deckH.get(1).getLife() + deckH.get(2).getLife();
			maxhp2 = deckAI.get(0).getLife() + deckAI.get(1).getLife() + deckAI.get(2).getLife();
			System.out.println(h);
			System.out.println(ai);
			
			int posh = h.getCardDistance(deckH);
			int posai = ai.getCardDistance(deckAI);
			if (posh==1) {
				deckHAff = deckH; c1=id1="1"; c2=id2="2"; c3=id3="3";
			}
			
			else if (posh==2) {
				deckHAff = new ArrayList<>();
				deckHAff.add(deckH.get(1));
				deckHAff.add(deckH.get(2));
				deckHAff.add(deckH.get(0));
				c1="3"; id3="1"; c2="1"; id1="2"; c3="2"; id2="3"; 
			}
			
			else if (posh==3) {
				deckHAff = new ArrayList<>();
				deckHAff.add(deckH.get(2));
				deckHAff.add(deckH.get(0));
				deckHAff.add(deckH.get(1));
				c1="2"; id2="1"; c2="3"; id3="2"; c3="1"; id1="3";
			}
			
			if (posai==3) {
				deckAIAff = deckAI; c4=id4="4"; c5=id5="5"; c6=id6="6";
			}
			
			else if (posai==1) {
				deckAIAff = new ArrayList<>();
				deckAIAff.add(deckAI.get(1));
				deckAIAff.add(deckAI.get(2));
				deckAIAff.add(deckAI.get(0));
				c4="6"; id6="4"; c5="4"; id4="5"; c6="5"; id5="6";
			}
			
			else if (posai==2) {
				deckAIAff = new ArrayList<>();
				deckAIAff.add(deckAI.get(2));
				deckAIAff.add(deckAI.get(0));
				deckAIAff.add(deckAI.get(1));
				c4="5"; id5="4"; c5="6"; id6="5"; c6="4"; id4="6";
			}

			request.getSession().setAttribute("endGame", "start");
			request.getSession().setAttribute("imgField", "img/field/swampField.jpg");
			request.getSession().setAttribute("namec"+c1, "LEGOLAS");
			request.getSession().setAttribute("classc"+c1, "Codeur");
			request.getSession().setAttribute("img"+c1, "img/card/cardAP.png");
			request.getSession().setAttribute("namec"+c2, "ARAGORN");
			request.getSession().setAttribute("classc"+c2, "Hacker");
			request.getSession().setAttribute("img"+c2, "img/card/cardAB.png");
			request.getSession().setAttribute("namec"+c3, "GIMLI");
			request.getSession().setAttribute("classc"+c3, "Debugeur");
			request.getSession().setAttribute("img"+c3, "img/card/cardAV.png");
			request.getSession().setAttribute("namec"+c4, "SMAUG");
			request.getSession().setAttribute("classc"+c4, "Omniscient");
			request.getSession().setAttribute("img"+c4, "img/card/cardTG.png");
			request.getSession().setAttribute("namec"+c5, "SAURON");
			request.getSession().setAttribute("classc"+c5, "Maitre du jeu");
			request.getSession().setAttribute("img"+c5, "img/card/cardJR.png");
			request.getSession().setAttribute("namec"+c6, "SAROUMANE");
			request.getSession().setAttribute("classc"+c6, "Mentaliste");
			request.getSession().setAttribute("img"+c6, "img/card/cardJA.png");

			refresh(request);		
			//}
			this.getServletContext().getRequestDispatcher("/WEB-INF/battleField.jsp").forward(request, response);}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		card = Integer.parseInt(request.getParameter("card"));
		if (turn == 0) {
			int targeth=h.RNG(3);
			deckH.get(targeth).setProtection(true);
			int targetai=h.RNG(3);
			deckAI.get(targetai).setProtection(true);
			message="<p>Fight!!!</p>"+message;
			tour = h.RNG(2);
			if (tour==0) {msgAtk();def=0;}
			else if (tour==1) {msgDef();def=1;}
			turn++;
		}

		else {
			if (!end) {
				if(tour == 0) {
					if (testCard() && def==0) {
						hAttaque();
						if(deckAI.get(i).getLife()>0) {
							if(h.deck().size() != 1) {msgDef();}
							else {
								h.deck().get(0).setProtection(false);
								iaAttaque();
								nextTurn();
							}
						}
						else {nextTurn();}
					}
					else if(!testCard() && def==1) {
						h.protection(deckH,(card));
						iaAttaque();
						nextTurn();
					}
				}
				else {
					if (!testCard() && def==1) {
						h.protection(deckH,(card));
						iaAttaque();

						if(deckH.get(i).getLife()>0) {msgAtk();}
						else {nextTurn();}
					}
					else if(testCard() && def==0) {
						hAttaque();
						nextTurn();	
					}
				}
			}
		}

		if(ai.verifyEnd()) {
			message="<p>Le joueur a gagne!!!</p>"+message;
			turn=0;
			request.getSession().setAttribute("endGame", "win");
		}
		else if(h.verifyEnd()) {
			message="<p>Le joueur a perdu...</p>"+message;
			turn=0;
			Optional<Card> c1 =  daoCard.findById(h.getCard1().getId());
			int phase = h.getPhase(c1.get());
			System.out.println(phase);
			History history = new History(Game.getInstance().getHuman(), phase, false);
			daoHistory.save(history);
			List<History> listh = daoHistory.findAll();
			for (History h : listh) {
				h.setNbWin(daoHistory.countWin(h.getName()));
				daoHistory.save(h);
			}
			deletePlayer(h);
			deletePlayer(ai);
			request.getSession().setAttribute("endGame", "lose");
		}
		refresh(request);
		this.getServletContext().getRequestDispatcher("/WEB-INF/fieldAjax.jsp").forward(request, response);
	}

	public void nextTurn() {
		if(!end) {
			turn++;
			System.out.println("turn="+turn);
			message="<p><u><font color='#FFD700'>Round n°"+Integer.toString(turn)+"</font></u></p>"+message;
			tour = h.RNG(2);
			System.out.println("tour="+tour);
			if (i<2) {i++;}
			else{i=0;}
			if(tour==0) {
				if(deckH.get(i).getLife()>0) {
					msgAtk();
				}
				else {
					if(deckAI.get(i).getLife()>0) {
						if(h.deck().size() != 1) {msgDef();}
						else {h.deck().get(0).setProtection(false); iaAttaque();
						nextTurn();}
					}
					else {nextTurn();}
				}
			}
			else if(tour==1) {
				if(deckAI.get(i).getLife()>0) {
					if(h.deck().size() != 1) {msgDef();}
					else {h.deck().get(0).setProtection(false); iaAttaque();
					if (deckH.get(i).getLife()>0) {
						msgAtk();
					}
					else {nextTurn();}
					}
				}
				else {
					if (deckH.get(i).getLife()>0) {
						msgAtk();
					}
					else {nextTurn();}
				}
			}
		}
	}

	public void iaAttaque() {
		message=ai.attack(deckH, deckAI, h, ai, i)+message;
		end=h.verifyEnd();
	}

	public void hAttaque() {
		message=h.attack(deckH, deckAI, ai, i, (card)-3)+message;
		end=ai.verifyEnd();
	}
	
	public void msgAtk() {
		message="<p>Saisir la carte adverse à attaquer avec c"+Integer.toString(i+1)+"</p>"+message;
		def=0;
	}
	
	public void msgDef() {
		message="<p>Saisir la carte à protéger</p>"+message;
		def=1;
	}

	public Boolean testCard() {
		Boolean catt=false;
		if(def==0) {
			if (card==4 || card==5 || card==6) {
				if(i!=h.getCardDistance(deckH)-1) {
					if((deckAIAff.get(0).getLife()>0 || deckAIAff.get(1).getLife()>0) && card!=(ai.getCardDistance(deckAI)+3)) {catt=true;}
					else if(deckAIAff.get(0).getLife()<=0 && deckAIAff.get(1).getLife()<=0) {catt=true;}
					else {catt=false;}
				}
				else {catt=true;}
			}
			else {catt=false;}
		}
		else {
			if((card==1 || card==2 || card==3) && (card)!=h.getCardProtected(deckH)) {catt=false;}
			else {catt=true;System.out.println("Erreur de saisie");}
		}
		return catt;
	}

	public void refresh(HttpServletRequest request) {
		System.out.println("turn="+turn);
		request.getSession().setAttribute("message", message);
		request.getSession().setAttribute("hpb1", (deckH.get(0).getLife() + deckH.get(1).getLife() + deckH.get(2).getLife()) * 100 / maxhp1);
		request.getSession().setAttribute("hpb2", (deckAI.get(0).getLife() + deckAI.get(1).getLife() + deckAI.get(2).getLife()) * 100 / maxhp2);
		
		if(def==1) {request.setAttribute("cursor", "img/cursor/shield.ico");
		request.getSession().setAttribute("cursorai", "");
		request.getSession().setAttribute("cursorch", "img/cursor/shield.ico");}
		else if(def==0) {request.getSession().setAttribute("cursor", "img/cursor/epeg.ico");
		request.getSession().setAttribute("cursorai", "img/cursor/eped.ico");
		request.getSession().setAttribute("cursorch", "");}
		
		request.getSession().setAttribute("deckH", deckHAff);
		request.getSession().setAttribute("deckAI", deckAIAff);

		request.getSession().setAttribute("idCard1", id1);
		request.getSession().setAttribute("idCard2", id2);
		request.getSession().setAttribute("idCard3", id3);
		request.getSession().setAttribute("idCard4", id4);
		request.getSession().setAttribute("idCard5", id5);
		request.getSession().setAttribute("idCard6", id6);
		
		if(deckHAff.get(0).getLife()>0) {request.getSession().setAttribute("disc1", "visible");}
		else {request.getSession().setAttribute("disc1", "hidden");}
		if(deckHAff.get(1).getLife()>0) {request.setAttribute("disc2", "visible");}
		else {request.getSession().setAttribute("disc2", "hidden");}
		if(deckHAff.get(2).getLife()>0) {request.getSession().setAttribute("disc3", "visible");}
		else {request.getSession().setAttribute("disc3", "hidden");}
		if(deckAIAff.get(0).getLife()>0) {request.getSession().setAttribute("disc4", "visible");}
		else {request.getSession().setAttribute("disc4", "hidden");}
		if(deckAIAff.get(1).getLife()>0) {request.getSession().setAttribute("disc5", "visible");}
		else {request.getSession().setAttribute("disc5", "hidden");}
		if(deckAIAff.get(2).getLife()>0) {request.getSession().setAttribute("disc6", "visible");}
		else {request.getSession().setAttribute("disc6", "hidden");}
		
		String idCardDef = Integer.toString(h.getCardProtected(deckH));
		request.getSession().setAttribute("idCardDef", idCardDef);
		
	}

	public void deletePlayer(Player player) {
		Card c1 = player.getCard1();
		Card c2 = player.getCard2();
		Card c3 = player.getCard3();
		daoPlayer.deleteById(player.getId());
		daoCard.deleteById(c1.getId());
		daoCard.deleteById(c2.getId());
		daoCard.deleteById(c3.getId());
	}

}