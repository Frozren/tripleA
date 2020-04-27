package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("1")
public class Human extends Player {
	
	@Column(name="dmgDealt")
	private int dmgDealt;
	
	@Column(name="dmgTaken")
	private int dmgTaken;
	
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
	
public String attack(List<Card> deckH, List<Card> deckAI, AI ai, int att, int target) {
		
		if (ai.deck().size() != 1) {
			ai.protection();
		} else {
			ai.deck().get(0).setProtection(false);
		}
		
		int dmg=deckAI.get(target-1).isAttackedBy(deckH.get(att));
		System.out.println("hCard" + att + " attaque aicard" + (target-1));
		String msg="<p>c"+(att+1)+" inflige "+dmg+" d�gats � c"+(target+3)+"</p>";
		
		return msg;
	}
	
public boolean protection(List<Card> deckH, int choix) {
	int p = this.getCardProtected(deckH);
	
	if (choix == p) {
		return false;
	}
	
	deckH.get(p-1).setProtection(false);
	deckH.get(choix-1).setProtection(true);
	System.out.println("L'homme protege card" + (choix-1));
	
	return true;
}
	
}
