package fr.formation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IDAOCard;
import fr.formation.dao.IDAOHistory;
import fr.formation.dao.IDAOPlayer;
import fr.formation.dao.IDAOSurvey;
import fr.formation.model.AI;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.History;
import fr.formation.model.Human;
import fr.formation.model.Player;
import fr.formation.model.Survey;

@Controller
public class BattleFieldController {
	@Autowired
	private IDAOPlayer daoPlayer;
	@Autowired
	private IDAOCard daoCard;
	@Autowired
	private IDAOHistory daoHistory;
	@Autowired
	private IDAOSurvey daoSurvey;
	
	//test: http://localhost:8181/tripleAFront/
	static String message;
	static String msgDef = "<p>Saisir la carte à protéger</p>";
	static int turn=0;
	static int card;
	static int i;
	static int tour;
	static int def;
	static int bossAtk;
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
	static int hp1, hp2, hp3, hp4, hp5, hp6;
	static int phase;
	static Boolean saveHist=true;
	static Boolean boss;


	@GetMapping("/battleField")
	public String startFight(HttpSession session, Model model) {
		turn=Integer.parseInt((String) session.getAttribute("turn"));
		if (turn==0) {
			
			//a enlever
//			Card carte1 = new Card(100,90,25);
//			Card carte2 = new Card(130,35,50);
//			Card carte3 = new Card(130,35,50);
//			Card carte4 = new Card(1500,50,50);
//			Card carte5 = new Card(0,0,0);
//			Card carte6 = new Card(0,0,0);
//			h = Game.getInstance().getHuman();
//			ai = Game.getInstance().getAI();
//			h.setCard1(carte1);
//			h.setCard2(carte2);
//			h.setCard3(carte3);
//			ai.setCard1(carte4);
//			ai.setCard2(carte5);
//			ai.setCard3(carte6);
//			carte1.setPosition(true);
//			carte6.setPosition(true);
//			phase=3;
			//fin a enlever
			
			

			message = "<p>Cliquez sur une carte pour commencer</p>";
			i=0;
			tour=0;
			def=0;
			bossAtk=0;
			saveHist=true;
			end=false;
			h = Game.getInstance().getHuman();
			phase = h.getPhase();
			System.out.println("phase="+phase);
			if (phase == 3) {boss = true;
				session.setAttribute("boss", "1");}
			else {boss = false;
				session.setAttribute("boss", "0");}
			ai = Game.getInstance().getAI();
			deckH = h.deck();
			deckAI = ai.deck();
			if (phase==3) {
				deckAI.add(ai.getCard2());
				deckAI.add(ai.getCard3());
				}
			hp1=deckH.get(0).getLife(); hp2=deckH.get(1).getLife(); hp3=deckH.get(2).getLife();
			hp4=deckAI.get(0).getLife(); hp5=deckAI.get(1).getLife(); hp6=deckAI.get(2).getLife();
			maxhp1 = deckH.get(0).getLife() + deckH.get(1).getLife() + deckH.get(2).getLife();
			maxhp2 = deckAI.get(0).getLife() + deckAI.get(1).getLife() + deckAI.get(2).getLife();
			System.out.println(h);
			System.out.println(ai);

			int posh = h.getCardDistance(deckH);
			int posai;
			if (phase == 3) {posai = 1;
			}
			else {posai = ai.getCardDistance(deckAI);}
			
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
			
		
			
			model.addAttribute("endGame", "start");
			
			session.setAttribute("namec"+c1, "LEGOLAS");
			session.setAttribute("classc"+c1, "Le Codeur");
			session.setAttribute("img"+c1, "assets/img/battlefield/card/cardAP.png");
			session.setAttribute("namec"+c2, "ARAGORN");
			session.setAttribute("classc"+c2, "L'Hacker");
			session.setAttribute("img"+c2, "assets/img/battlefield/card/cardAB.png");
			session.setAttribute("namec"+c3, "GIMLI");
			session.setAttribute("classc"+c3, "Le Debugeur");
			session.setAttribute("img"+c3, "assets/img/battlefield/card/cardAV.png");
			session.setAttribute("namec4", "SMAUG");
			session.setAttribute("classc4", "Le Majestueux");
			session.setAttribute("img4", "assets/img/battlefield/card/cardTG.png");
			session.setAttribute("namec5", "GOLUM");
			session.setAttribute("classc5", "Le Maitre du jeu");
			session.setAttribute("img5", "assets/img/battlefield/card/cardJR.png");
			session.setAttribute("imgField", session.getAttribute("sceneUrl"));
			if (phase == 1) {
				session.setAttribute("namec6", "SAROUMANE");
				session.setAttribute("classc6", "Le Magicien");
				session.setAttribute("img6", "assets/img/battlefield/card/cardJA.png");
			} else {
				session.setAttribute("namec6", "SAURON");
				session.setAttribute("classc6", "L'Omniscient");
				session.setAttribute("img6", "assets/img/battlefield/card/cardJP.png");
			}
			if (phase == 3) {
				session.setAttribute("imgField", "assets/img/battlefield/field/bg2.jpg");
				session.setAttribute("musicBattle", "assets/sound/music6.mp3");
			}
			else {
				session.setAttribute("musicBattle", session.getAttribute("musicUrl"));
			}
			
			session.setAttribute("idCard1", id1);
			session.setAttribute("idCard2", id2);
			session.setAttribute("idCard3", id3);
			session.setAttribute("idCard4", id4);
			session.setAttribute("idCard5", id5);
			session.setAttribute("idCard6", id6);
			
			session.setAttribute("idCardDist", posai+3);

			refresh(model);
		}
		
		return "battleField";
	}

	@PostMapping("/battle")
	public String fight(@RequestParam String c, Model model, HttpSession session) {
		card = Integer.parseInt(c);
		if (turn == 0) {
			int targetai=h.RNG(3);
			deckAI.get(targetai).setProtection(true);
			message="<p><u><font color='#FFD700'>Round n°1</font></u></p><p><u><font color='#FFD700'>Fight!!!</font></u></p>"+message;
			tour = h.RNG(2);
			if (tour==0) {msgAtk();def=0;}
			else if (tour==1) {msgDef();def=1;}
			turn++;
			session.setAttribute("turn", "1");
			
		}

		else {
			if(!testCard()) {effetBoss(model);}
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

		if(ai.verifyEnd() && saveHist) {
			turn=0;
			session.setAttribute("turn", "0");
			message="<p><u><font color='#FFD700'>Le joueur a gagne!!!</font></u></p>"+message;
			if (boss) {
				saveHistory(true);
			}
			model.addAttribute("endGame", "win");
		}
		else if(h.verifyEnd() && saveHist) {
			turn=0;
			session.setAttribute("turn", "0");
			message="<p><u><font color='#FF0000'>Le joueur a perdu...</font></u></p>"+message;
			saveHistory(false);
			model.addAttribute("endGame", "lose");
			saveHist=false;
		}
		refresh(model);
		
		return"fieldAjax";
	}
	
	@PostMapping("/valideSurvey")
	public String survey(@Valid @ModelAttribute Survey survey, BindingResult result ) {
		System.out.println(survey);
		if (result.hasErrors()) {
			return "redirect:/home";}
		else {
		daoSurvey.save(survey);
		System.out.println(survey);}
		return "redirect:/home";
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
		System.out.println("vie boss="+deckAI.get(0).getLife());
	}

	public void iaAttaque() {
		if(phase!=3) {
		message=ai.attack(deckH, deckAI, h, ai, i)+message;}
		else {message=ai.bossAttack(deckH, deckAI, h, ai, turn)+message;}
		end=h.verifyEnd();
	}

	public void hAttaque() {
		message=h.attack(deckH, deckAI, ai, i, (card)-3, phase)+message;
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

	public void refresh(Model model) {
		System.out.println("turn="+turn);
		model.addAttribute("message", message);
		model.addAttribute("hpb1", (deckH.get(0).getLife() + deckH.get(1).getLife() + deckH.get(2).getLife()) * 100 / maxhp1);
		model.addAttribute("hpb2", (deckAI.get(0).getLife() + deckAI.get(1).getLife() + deckAI.get(2).getLife()) * 100 / maxhp2);

		if(def==1) {model.addAttribute("cursor", "'assets/img/battlefield/cursor/shield.ico'");
		model.addAttribute("cursorai", " ");
		model.addAttribute("cursorch", "url('assets/img/battlefield/cursor/shield.ico'), ");}
		else if(def==0) {model.addAttribute("cursor", "'assets/img/battlefield/cursor/epeg.ico'");
		model.addAttribute("cursorai", "url('assets/img/battlefield/cursor/eped.ico'), ");
		model.addAttribute("cursorch", " ");}

		model.addAttribute("deckH", deckHAff);
		model.addAttribute("deckAI", deckAIAff);

		if(deckHAff.get(0).getLife()>0) {model.addAttribute("disc1", "alive");}
		else {model.addAttribute("disc1", "dead");}
		if(deckHAff.get(1).getLife()>0) {model.addAttribute("disc2", "alive");}
		else {model.addAttribute("disc2", "dead");}
		if(deckHAff.get(2).getLife()>0) {model.addAttribute("disc3", "alive");}
		else {model.addAttribute("disc3", "dead");}
		if(deckAIAff.get(0).getLife()>0) {model.addAttribute("disc4", "alive");}
		else {model.addAttribute("disc4", "dead");}
		if(deckAIAff.get(1).getLife()>0 && !boss) {model.addAttribute("disc5", "alive");}
		else {model.addAttribute("disc5", "dead");}
		if(deckAIAff.get(2).getLife()>0 && !boss) {model.addAttribute("disc6", "alive");}
		else {model.addAttribute("disc6", "dead");}

		String idCardDef = Integer.toString(h.getCardProtected(deckH));
		String idCardAtk = Integer.toString(i+1);
		if (h.deck().size()==1) {idCardDef="0";}
		model.addAttribute("idCardDef", idCardDef);
		model.addAttribute("idCardAtk", idCardAtk);
		model.addAttribute("def", def);
		model.addAttribute("phase", phase);
		
		affDmg(model);
	}

	public void affDmg(Model model) {
		int dmg1=hp1-deckH.get(0).getLife(); hp1=deckH.get(0).getLife();
		int dmg2=hp2-deckH.get(1).getLife(); hp2=deckH.get(1).getLife();
		int dmg3=hp3-deckH.get(2).getLife(); hp3=deckH.get(2).getLife();
		int dmg4=hp4-deckAI.get(0).getLife(); hp4=deckAI.get(0).getLife();
		model.addAttribute("dmg"+c1, dmg1);
		model.addAttribute("dmg"+c2, dmg2);
		model.addAttribute("dmg"+c3, dmg3);
		if (phase!=3) {
			int dmg5=hp5-deckAI.get(1).getLife(); hp5=deckAI.get(1).getLife();
			int dmg6=hp6-deckAI.get(2).getLife(); hp6=deckAI.get(2).getLife();
			model.addAttribute("dmg"+c4, dmg4);
			model.addAttribute("dmg"+c5, dmg5);
			model.addAttribute("dmg"+c6, dmg6);
		}
		else if (phase==3) {
			int dmg5=0, dmg6=0;
			model.addAttribute("dmgBoss", dmg4);
			model.addAttribute("dmg5", dmg5);
			model.addAttribute("dmg6", dmg6);
		}
	}
	
	public void effetBoss(Model model) {
		double tour=(turn+2)/3;
		System.out.println("Def="+def);
		if((tour+2)%3==0 && def==1) {
			bossAtk=1;
		}
		else if((tour+1)%3==0 && def==1) {
			bossAtk=2;
		}
		else if((tour)%3==0 && def==1) {
			bossAtk=3;
		}
		else {bossAtk=0;}
		model.addAttribute("bossAtk", bossAtk);
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
	
	public void saveHistory (Boolean win) {
		History history = new History(Game.getInstance().getHuman(), phase, win);
		daoHistory.save(history);
		List<History> listh = daoHistory.findAll();
		for (History h : listh) {
			h.setNbWin(daoHistory.countWin(h.getName()));
			daoHistory.save(h);
		}
		deletePlayer(h);
		deletePlayer(ai);
	}

}
