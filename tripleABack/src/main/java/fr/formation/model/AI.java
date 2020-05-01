package fr.formation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("0")
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
		List<Integer> l = new ArrayList<>();
		
		l.add(this.getCard1().getAtk());
		l.add(this.getCard2().getAtk());
		l.add(this.getCard3().getAtk());
		
		Collections.sort(l, Collections.reverseOrder());

		if (l.get(0) == this.getCard1().getAtk()) {
			this.getCard1().setPosition(true);
		} else if (l.get(0) == this.getCard2().getAtk()) {
			this.getCard2().setPosition(true);
		} else if (l.get(0) == this.getCard3().getAtk()){
			this.getCard3().setPosition(true);
		}
	}
	
	public String attack(List<Card> deckH, List<Card> deckAI, AI ai, int att) {
		int target = 0;
		
		while (deckH.get(target=ai.RNG(3)).getLife() <= 0) {}
		
		int dmg=deckH.get(target).isAttackedBy(deckAI.get(att));
		Human h = Game.getInstance().getHuman();
		h.setDmgTaken(dmg+h.getDmgTaken());
		System.out.println("aicard" + (att+3) + " attaque hcard" + target);
		String msg="<p>c"+(att+4)+" inflige "+dmg+" dégats à c"+(target+1)+"</p>";
		
		return msg;
	}
	
	public void protection() {
		List<Card> deckAI = this.deck();
		int p = this.getCardProtected(deckAI), protectionAI = 0;
		if(deckAI.size()==3) {
		do {
			protectionAI = this.RNG(3);
		} while(protectionAI == p-1);
		
		deckAI.get(p-1).setProtection(false);
		deckAI.get(protectionAI).setProtection(true);
		}
		else if(deckAI.size()==2) {
			if(p==1) {
				protectionAI=1;
			}
			else {protectionAI=0;}
			deckAI.get(p-1).setProtection(false);
			deckAI.get(protectionAI).setProtection(true);
		}
		else {deckAI.get(p-1).setProtection(false);}
		System.out.println("L'ia protege card" + protectionAI+" et p = "+p);
	}
	
}
