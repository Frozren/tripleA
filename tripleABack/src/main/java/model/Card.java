package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="card")

public class Card {
	//ATTRIBUTS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="life", length = 11, nullable = false)
	protected int life = 100;
	
	@Column(name="atk", length = 11, nullable = false)
	protected int atk = 20;
	
	@Column(name="def", length = 11, nullable = false)
	protected int def = 25;
	
	@Transient
	protected boolean position = false;		//true = ï¿½ distance
	
	@Transient
	protected boolean protection = false;
	
	//CONSTRUCTOR	
	
	public Card(int id, int life, int atk, int def) {
		this.life = life;
		this.atk = atk;
		this.def = def;
	}
	
	public Card(int life, int atk, int def) {
		this.life = life;
		this.atk = atk;
		this.def = def;
	}
	
	public Card() {
	}

	//GETTER & SETTER
	
	
	public int getLife() {
		return life;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}
	
	public void addLife(int n) {
		this.life += n;
	}
	
	public void addAtk(int n) {
		this.atk += n;
	}
	
	public void addDef(int n) {
		this.def += n;
	}
	public boolean getPosition() {
		return position;
	}

	public void setPosition(boolean position) {
		this.position = position;
	}
	
	public boolean isProtection() {
		return protection;
	}

	public void setProtection(boolean protection) {
		this.protection = protection;
	}

	//METHODS
	public int isAttackedBy(Card c) {
		int dmg;
		if (this.isProtection()) {
			dmg=(c.getAtk() - (c.getAtk() * 90) / 100);
			if(this.life>dmg) {
			this.life -= dmg;}
			else {this.life=0;}
			
			System.out.println("Cardatt" + c.getAtk() + " inflige " + dmg + "dgt à cardatt" + this.getAtk());
		} else {
			dmg=(c.getAtk() - (c.getAtk() * this.def) / 100);
			if(this.life>dmg) {
			this.life -= dmg;}
			else {this.life=0;}
			System.out.println("Cardatt" + c.getAtk() + " inflige " + dmg + "dgt à cardatt" + this.getAtk());
		}
		return dmg;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", life=" + life + ", atk=" + atk + ", def=" + def + ", position=" + position
				+ ", protection=" + protection + "]";
	}
}
