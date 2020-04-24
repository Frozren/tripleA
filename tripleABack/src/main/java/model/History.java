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
	
	@Column(name = "dmg_taken")
	private int dmg_taken;
	
	@Column(name = "dmg_dealt")
	private int dmg_dealt;

	@Column(name = "phase")
	private int phase;

	@Column(name = "etat")
	private Boolean etat;

	@Column(name = "date_end")
	private LocalDate dateEnd;
	
	@Column(name = "name")
	private String name;
	
	public History() {}

	public History(int dmg_taken, int dmg_dealt, int phase, Boolean etat, LocalDate dateEnd, Human human) {
		this.dmg_taken = dmg_taken;
		this.dmg_dealt = dmg_dealt;
		this.phase = phase;
		this.etat = etat;
		this.dateEnd = dateEnd;
		this.name = human.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDmg_taken() {
		return dmg_taken;
	}

	public void setDmg_taken(int dmg_taken) {
		this.dmg_taken = dmg_taken;
	}

	public int getDmg_dealt() {
		return dmg_dealt;
	}

	public void setDmg_dealt(int dmg_dealt) {
		this.dmg_dealt = dmg_dealt;
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

	@Override
	public String toString() {
		return "History [id=" + id + ", dmg_taken=" + dmg_taken + ", dmg_dealt=" + dmg_dealt + ", phase=" + phase
				+ ", etat=" + etat + ", dateEnd=" + dateEnd + ", name=" + name + "]";
	}
}
