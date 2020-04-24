package app;

import java.util.List;

import dao.IDAOCard;
import dao.IDAOHistory;
import dao.IDAOPlayer;
import model.Game;
import model.History;

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
					
		}
}
