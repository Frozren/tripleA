package model;

public class Card {
	//ATTRIBUTS
	protected int life = 100, atk = 20, def = 25;
	protected boolean position = false;		//true = à distance
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
	public void isAttackedBy(Card c) {
		if (this.isProtection()) {
			this.life -= (c.getAtk() - (c.getAtk() * 90) / 100);
			System.out.println("Cardatt" + c.getAtk() + " inflige " + (c.getAtk() - (c.getAtk() * 90) / 100) + "dgt à cardatt" + this.getAtk());
		} else {
			this.life -= (c.getAtk() - (c.getAtk() * this.def) / 100);
			System.out.println("Cardatt" + c.getAtk() + " inflige " + (c.getAtk() - (c.getAtk() * this.def) / 100) + "dgt à cardatt" + this.getAtk());
		}
	}

	@Override
	public String toString() {
		return "Card [life=" + life + ", atk=" + atk + ", def=" + def + ", position=" + position + "]";
	}

	
}
