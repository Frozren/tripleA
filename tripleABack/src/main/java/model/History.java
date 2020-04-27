package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class History {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "dmgTaken")
	private int dmgTaken;
	
	@Column(name = "dmgDealt")
	private int dmgDealt;

	@Column(name = "phase")
	private int phase;

	@Column(name = "etat")
	private Boolean etat;

	@Column(name = "dateEnd")
	private LocalDate dateEnd;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nbWin")
	private long nbWin;
	
	public History() {}

	public History(int dmgTaken, int dmgDealt, int phase, Boolean etat, LocalDate dateEnd, Human human) {
		this.dmgTaken = dmgTaken;
		this.dmgDealt = dmgDealt;
		this.phase = phase;
		this.etat = etat;
		this.dateEnd = dateEnd;
		this.name = human.getName();
	}
	
	public History(Human human, int phase, Boolean etat, int dmg_taken, int dmg_dealt) {
		this.name = human.getName();
		this.phase = phase;
		this.etat = etat;
		this.dmgDealt = dmg_dealt;
		this.dmgTaken = dmg_taken;
		this.dateEnd = LocalDate.now();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDmgTaken() {
		return dmgTaken;
	}

	public void setDmgTaken(int dmgTaken) {
		this.dmgTaken = dmgTaken;
	}

	public int getDmgDealt() {
		return dmgDealt;
	}

	public void setDmgDealt(int dmgDealt) {
		this.dmgDealt = dmgDealt;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public Boolean getEtat() {
		return etat;
	}

	public void setEtat(Boolean etat) {
		this.etat = etat;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNbWin() {
		return nbWin;
	}

	public void setNbWin(long nbWin) {
		this.nbWin = nbWin;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", dmg_taken=" + dmgTaken + ", dmg_dealt=" + dmgDealt + ", phase=" + phase
				+ ", etat=" + etat + ", dateEnd=" + dateEnd + ", name=" + name + ", nbWin=" + nbWin + "]";
	}
	
	
}
