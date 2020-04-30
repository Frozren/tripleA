package app;

import dao.IDAOCard;
import dao.IDAOHistory;
import dao.IDAOPlayer;
import fr.formation.model.Card;
import fr.formation.model.Game;
import fr.formation.model.History;
import fr.formation.model.Human;

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
			

			//List<History> history = daoHistory.selectAll();
			
//			for (History h : history) {
//	
//				System.out.println(h);
//	
//			}
//			System.out.println("");
			
//			history.sort(Comparator.comparing(History::getDmg_dealt).reversed());
//			history.sort(Comparator.comparing(History::getEtat).thenComparing(History::getDmg_dealt).reversed()); //DAMAGE DEALT !! OK
//			history.sort(Comparator.comparing(History::getDmg_taken).thenComparing(History::getEtat)); // DAMAGE TAKEN !! OK
//			history.sort(Comparator.comparing(History::getNbWin));
//			
//			for (History h : history) {
//				
//				System.out.println(h);
//				
//			}
			
//			for (int i = history.size()-1; i>0; i--) {
//				if (history.get(i).getName().contentEquals(history.get(i-1).getName())) {
//					history.remove(i);
//				}
//			}
			
//			history.sort(Comparator.comparing(History::getNbWin).reversed());
			
//			for (History h : history) {
//				
//				System.out.println(h);
//				
//			}
			
//			List<String> listH = daoHistory.selectNames();
//			List<Long> listI = new ArrayList<>();
//			int i = 0;
//			
//			for (String h : listH) {
//				listI.add(daoHistory.countWin(listH.get(i)));
//				System.out.print(h);
//				System.out.println(": " + listI.get(i) + " win(s) !!");
//				i++;
//			}
			
//			Human human = new Human();
//			
//			History history = new History(Game.getInstance().getHuman(), 3, true);
//			
//			daoHistory.insert(history);
//			
//
//			List<History> listh = daoHistory.selectAll();
//			
//			for (History h : listh) {
//				h.setNbWin(daoHistory.countWin(h.getName()));
//				daoHistory.update(h);
//			}
//			
//			daoPlayer.delete(human.getId());

			
//			history.setNbWin(daoHistory.countWin(history.getName())+1); // SI WIN
////			history.setNbWin(daoHistory.countWin(history.getName())); // SI LOSE
//			daoHistory.update(history);
//			System.out.println(history);
			
			
			Card c1 = new Card(0, 120, 50, 25);
			Card c2 = new Card(1, 120, 50, 25);
			Card c3 = new Card(2, 120, 50, 25);
			
			daoCard.save(c1);
			daoCard.save(c2);
			daoCard.save(c3);
			
			Human h = new Human("KIKI");
			
			h.setCard1(c1);
			h.setCard2(c2);
			h.setCard3(c3);
			
			System.out.println(h);
			
			daoPlayer.save(h);
			
			System.out.println(h);
			
			
			
			
			
			
			
					
		}
}
