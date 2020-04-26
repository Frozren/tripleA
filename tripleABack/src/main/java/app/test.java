package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dao.IDAOCard;
import dao.IDAOHistory;
import dao.IDAOPlayer;
import model.Card;
import model.Game;
import model.History;
import model.Human;
import model.Player;

public class test {

		public static void main(String[] args) {
			
			IDAOPlayer daoPlayer = Game.getInstance().getDaoPlayer();
			IDAOCard daoCard = Game.getInstance().getDaoCard();
			IDAOHistory daoHistory = Game.getInstance().getDaoHistory();
			
//			Card c1 = daoCard.selectById(385);
//			Card c2 = daoCard.selectById(386);
//			Card c3 = daoCard.selectById(387);
			
//			
//			Human h = new Human("TOTO", c1, c2, c3);
			
			//daoPlayer.insert(h);
			
		
//			daoPlayer.delete(102);
			
//			List<Player> listp = daoPlayer.selectAll();
//			
//			for (Player p : listp) {
//				System.out.println(p);
//			}
			
//			Card c1 = new Card(100, 50, 25);
//			Card c2 = new Card(100, 50, 25);
//			Card c3 = new Card(100, 50, 25);
//			
//			daoCard.insert(c1);
//			daoCard.insert(c2);
//			daoCard.insert(c3);
//			
//			Human h = new Human("Jeremy", c1, c2, c3);
//			
//			daoPlayer.insert(h);
			

//			List<History> history = daoHistory.selectAll();
//			
//			for (History h : history) {
//	
//				System.out.println(h);
//	
//			}
//			System.out.println("");
			
//			history.sort(Comparator.comparing(History::getDmg_dealt).reversed());
//			history.sort(Comparator.comparing(History::getEtat).thenComparing(History::getDmg_dealt).reversed()); //DAMAGE DEALT !! OK
//			history.sort(Comparator.comparing(History::getDmg_taken).thenComparing(History::getEtat)); // DAMAGE TAKEN !! OK
//			
//			for (History h : history) {
//				
//				System.out.println(h);
//				
//			}
			
			List<String> listH = daoHistory.selectNames();
			List<Long> listI = new ArrayList<>();
			int i = 0;
			
			for (String h : listH) {
				listI.add(daoHistory.countWin(listH.get(i)));
				System.out.print(h);
				System.out.println(": " + listI.get(i) + " win(s) !!");
				i++;
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
					
		}
}
