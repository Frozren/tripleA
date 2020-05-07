package fr.formation.model;

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
	
	@Column(name="phase")
	private int phase = 0;
	
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
	
	public int getDmgDealt() {
		return dmgDealt;
	}

	public void setDmgDealt(int dmgDealt) {
		this.dmgDealt = dmgDealt;
	}

	public int getDmgTaken() {
		return dmgTaken;
	}

	public void setDmgTaken(int dmgTaken) {
		this.dmgTaken = dmgTaken;
	}
	
	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public String attack(List<Card> deckH, List<Card> deckAI, AI ai, int att, int target) {
		
		if (ai.deck().size() != 1) {
			ai.protection();
		} else {
			ai.deck().get(0).setProtection(false);
		}
		
		int dmg=deckAI.get(target-1).isAttackedBy(deckH.get(att),1);
		Human h = Game.getInstance().getHuman();
		h.setDmgDealt(dmg+h.getDmgDealt());
		System.out.println("hCard" + att + " attaque aicard" + (target-1));
		String msg="<p>c"+(att+1)+" inflige "+dmg+" dégâts à c"+(target+3)+"</p>";
		
		return msg;
	}
	
	public void protection(List<Card> deckH, int choix) {
		int p = this.getCardProtected(deckH);
		if (p!=0) {deckH.get(p-1).setProtection(false);}
		deckH.get(choix-1).setProtection(true);
		System.out.println("L'homme protege card" + (choix-1));
	}
	
//	public int getPhase(Card c1) {
//		int passivePoints = Game.getInstance().getPassivePoints();
//		int hp = c1.getLife(), atk = c1.getAtk(), def = c1.getDef();
//		int phase = (hp+atk+def-100-20-25)/passivePoints;
//		System.out.println("phase = "+phase);
//		return phase;
//	}

	@Override
	public String toString() {
		return "Human [dmgDealt=" + dmgDealt + ", dmgTaken=" + dmgTaken + ", phase=" + phase + ", id=" + id
				+ ", idOpponent=" + idOpponent + ", name=" + name + ", typePlayer=" + typePlayer + ", card1=" + card1
				+ ", card2=" + card2 + ", card3=" + card3 + "]";
	}
	
}
