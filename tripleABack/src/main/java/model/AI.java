package model;

import java.util.ArrayList;
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
	
	public String attack(List<Card> deckH, List<Card> deckAI, AI ai, int att) {
		int target = 0;
		
		while (deckH.get(target=ai.RNG(3)).getLife() <= 0) {}
		
		int dmg=deckH.get(target).isAttackedBy(deckAI.get(att));
		System.out.println("aicard" + (att+3) + " attaque hcard" + target);
		String msg="<p>c"+(att+4)+" inflige "+dmg+" d�gats � c"+(target+1)+"</p>";
		
		return msg;
	}
	
	public void protection() {
		List<Card> deckAI = this.deck();
		int p = this.getCardProtected(deckAI), protectionAI = 0;
		if(deckAI.size()>1) {
		do {
			protectionAI = this.RNG(3);
		} while(protectionAI == p || protectionAI > (deckAI.size()-1));
		
		deckAI.get(p-1).setProtection(false);
		
		deckAI.get(protectionAI).setProtection(true);
		}
		else {deckAI.get(0).setProtection(false);}
		System.out.println("L'ia protege card" + protectionAI);
	}
	
}
