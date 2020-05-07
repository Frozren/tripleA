package fr.formation;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.formation.config.AppConfig;
import fr.formation.dao.IDAOCard;
import fr.formation.dao.IDAOHistory;
import fr.formation.dao.IDAOPlayer;
import fr.formation.model.Player;

public class Test {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext =
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		IDAOPlayer daoPlayer = myContext.getBean(IDAOPlayer.class);
		IDAOCard daoCard = myContext.getBean(IDAOCard.class);
		IDAOHistory daoHistory = myContext.getBean(IDAOHistory.class);
		
//		System.out.println(daoCard.findById(370));	//OK
		
//		List<Card> listC = daoCard.findAll();	//OK
		
//		System.out.println(daoHistory.findById(1));	//OK
		
//		List<History> listH = daoHistory.findAll(); //OK
		
//		System.out.println(daoPlayer.findById(104));	//OK
		
//		System.out.println(daoPlayer.findByName("JEREMY"));	// OK
		
//		for (int i = listP.size(); i > 0; i--) {
//			
//		}
		
//		List<Player> listH = daoPlayer.findAll().stream().filter(player -> player.isTypePlayer()).collect(Collectors.toList());
//		
//		for (Player p : listH) {
//			System.out.println(p);
//		}
		
//		List<History> list = daoHistory.findAll();
//		
//		list = list.stream().sorted(
//                comparing(History::getName)
//                .thenComparing(History::getDateEnd).reversed())
//               .collect(Collectors.toList());		//TRI PAR NOM
//		
//		//SUPPRIMER LES DOUBLONS POUR QU'IL N'Y AI QU'UNE SEULE FOIS LE MÊME
//		for (int i = list.size() - 1; i > 0; i--) {
//			if (list.get(i).getName().contentEquals(list.get(i - 1).getName())) {
//				list.remove(i);
//			}
//		}
//		
//		list = list.stream().sorted(
//                comparing(History::getNbWin).reversed())
//               .collect(Collectors.toList());	
//		
//		
//		
//		list.forEach(l -> System.out.println(l));
		
		List<Player> list = daoPlayer.findByTypePlayer(false);
		
		list.forEach(l -> System.out.println(l));
		
		
	}
	
}
