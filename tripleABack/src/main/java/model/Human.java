package model;

import java.util.List;

public class Human extends Player {
	
	public Human(int id, String name, Card carte1, Card carte2, Card carte3, boolean typePlayer, int idOpponent) {
		super(id, name, carte1, carte2, carte3, typePlayer, idOpponent);
	}
	
	public Human(String name, Card carte1, Card carte2, Card carte3) {
		super(carte1, carte2, carte3);
		this.typePlayer = true;
		this.name = name;
	}
	
	public Human(String name) {
		this.name = name;
		this.typePlayer = true;
	}
	
	public Human() {
		this.typePlayer = true;
	}
	
	public boolean attack(Human h, AI ai, int att, int target) {
		List<Card> deckH = h.deck();
		List<Card> deckAI = ai.deck();
		
		if (deckAI.size() != 1) {
			ai.protection();
		} else {
			deckAI.get(ai.getCardProtected()).setProtection(false);
		}
		
		if ((att + 1) > deckH.size()) {
			att = 0;
		}
		
		if ((target-1) > deckAI.size()) {
			target = 1;
		}
		
		deckAI.get(target-1).isAttackedBy(deckH.get(att));
		System.out.println("hCard" + att + " attaque aicard" + (target-1));
		return ai.verifyEnd();
	}
	
	public boolean protection(int choix) {
		List<Card> deckH = this.deck();
		int p = this.getCardProtected();
		
		if (choix == p) {
			return false;
		}
		
		deckH.get(p-1).setProtection(false);
		deckH.get(choix-1).setProtection(true);
		System.out.println("L'homme protege card" + (choix-1));
		
		return true;
	}
	
}
