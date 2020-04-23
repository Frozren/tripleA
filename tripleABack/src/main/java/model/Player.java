package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "player")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeplayer")
public abstract class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="idopponent")
	protected int idOpponent;
	
	@Column(name="name", length=50, nullable = false)
	protected String name;
	
	@Column(name="typeplayer", insertable = false, updatable = false)
	protected boolean typePlayer;
	
	@OneToOne
	@JoinColumn(name="idcard1")
	protected Card card1;
	
	@OneToOne
	@JoinColumn(name="idcard2")
	protected Card card2;
	
	@OneToOne
	@JoinColumn(name="idcard3")
	protected Card card3;
	
	public Player(int id, String name, Card carte1, Card carte2, Card carte3, boolean typePlayer, int idOpponent) {
		this.id = id;
		this.name = name;
		this.card1 = carte1;
		this.card2 = carte2;
		this.card3 = carte3;
		this.typePlayer = typePlayer;
		this.idOpponent = idOpponent;
	}
	
	public Player(Card carte1, Card carte2, Card carte3) {
		this.card1 = carte1;
		this.card2 = carte2;
		this.card3 = carte3;
	}
	
	public Player() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isTypePlayer() {
		return typePlayer;
	}

	public void setTypePlayer(boolean typePlayer) {
		this.typePlayer = typePlayer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card getCard1() {
		return card1;
	}

	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public Card getCard2() {
		return card2;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}

	public Card getCard3() {
		return card3;
	}

	public void setCard3(Card card3) {
		this.card3 = card3;
	}

	public int getIdOpponent() {
		return idOpponent;
	}

	public void setIdOpponent(int idOpponent) {
		this.idOpponent = idOpponent;
	}
	
	
	public List<Card> createChoice(int n, int pts){
		List<Card> list = new ArrayList();

		for (int i = 0; i < n; i++) {
			list.add(createCardRNG(pts));
		}
		
		return list;
	}

	public Card createCardRNG(int pts) {
		Card c = new Card();
		int n = 0;
		
		n = RNG(pts+1);
		c.addLife(n);
		pts -= n;
		n = RNG(pts+1);
		c.addAtk(n);
		pts -= n;
		c.addDef(pts);

		return c;
	}
	
	public int RNG(int n) {
		Random r = new Random();
		int a = 0;
				
		a = r.nextInt(n);
		
		return a;
	}
	
	public List<Card> deck(){
		List<Card> list = new ArrayList();
		
		if (this.getCard1().getLife() > 0) {
			list.add(this.getCard1());
		}
		
		if (this.getCard2().getLife() > 0) {
			list.add(this.getCard2());
		}
		
		if (this.getCard3().getLife() > 0) {
			list.add(this.getCard3());
		}
		
		return list;
	}
	
	public int getCardDistance() {
		List<Card> deckH = this.deck();
		int i = 0;
		
		for(Card c : deckH) {
			i++;
			if (c.getPosition()) {
				break;
			}
		}
		return i;
	}
	
	public int getCardProtected() {
		List<Card> deckH = this.deck();
		int i = 0;
		
		for(Card c : deckH) {
			i++;
			if (c.isProtection()) {
				break;
			}
		}
		return i;
	}
	
	public boolean verifyEnd() {
		List<Card> deck = this.deck();
		
		if (deck.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", idOpponent=" + idOpponent + ", name=" + name + ", typePlayer=" + typePlayer
				+ ", card1=" + card1 + ", card2=" + card2 + ", card3=" + card3 + "]";
	}

	
}
