package model;

import java.util.ArrayList;
import java.util.List;

public class AI extends Player {

	public AI(int id, String name, Card carte1, Card carte2, Card carte3, boolean typePlayer, int idOpponent) {
		super(id, name, carte1, carte2, carte3, typePlayer, idOpponent);
	}
	
	public AI(Card carte1, Card carte2, Card carte3) {
		super(carte1, carte2, carte3);
		this.typePlayer = false;
		this.name = "AI";
	}
	
	public AI() {
		this.typePlayer = false;
		this.name = "AI";
	}

	public void choiceDistance() {
		List<Integer> l = new ArrayList();
		
		l.add(this.getCard1().getLife() + this.getCard1().getDef());
		l.add(this.getCard2().getLife() + this.getCard2().getDef());
		l.add(this.getCard3().getLife() + this.getCard3().getDef());

		if (l.get(0) < l.get(1) && l.get(0) < l.get(2)) {
			this.getCard1().setPosition(true);
		} else if (l.get(1) < l.get(2)) {
			this.getCard2().setPosition(true);
		} else {
			this.getCard3().setPosition(true);
		}
	}
	
	public boolean attack(Human h, AI ai, int att) {
		List<Card> deckH = h.deck();
		List<Card> deckAI = ai.deck();
		int target = 0;
		
		if ((att + 1) > deckAI.size()) {
			att = 0;
		}
		
		while ((target = ai.RNG(3)) > (deckH.size()-1)) {}
		
		deckH.get(target).isAttackedBy(deckAI.get(att));
		System.out.println("aicard" + att + " attaque hcard" + target);
		return h.verifyEnd();
	}
	
	public void protection() {
		List<Card> deckAI = this.deck();
		int p = this.getCardProtected(), protectionAI = 0;
		
		do {
			protectionAI = this.RNG(3);
		} while(protectionAI == p || protectionAI > (deckAI.size()-1));
		
		deckAI.get(p-1).setProtection(false);
		deckAI.get(protectionAI).setProtection(true);
		System.out.println("L'ia protege card" + protectionAI);
	}
	
}
