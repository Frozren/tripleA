package fr.formation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		
		List<Player> listH = daoPlayer.findAll().stream().filter(player -> player.isTypePlayer()).collect(Collectors.toList());
		
		for (Player p : listH) {
			System.out.println(p);
		}
		
	}
	
}
