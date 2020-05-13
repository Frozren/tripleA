package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="survey")
public class Survey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	@Column(name = "genre")
	protected String genre;
	
	@Column(name = "age")
	protected int age;

	@Column(name = "support")
	protected String support;

	@Column(name = "ville")
	protected String ville;
	
	@Column(name = "pays")
	protected String pays;
	
	@Column(name = "note")
	protected int note;
	
	@Column(name = "ressenti")
	protected String ressenti;
	
	public Survey() {}
	
	public Survey(String genre, int age, String support, String ville, String pays, int note, String ressenti) {
		this.genre=genre;
		this.age=age;
		this.support=support;
		this.ville=ville;
		this.pays=pays;
		this.note=note;
		this.ressenti=ressenti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getRessenti() {
		return ressenti;
	}

	public void setRessenti(String ressenti) {
		this.ressenti = ressenti;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", genre=" + genre + ", age=" + age + ", support=" + support + ", ville=" + ville
				+ ", pays=" + pays + ", note=" + note + ", ressenti=" + ressenti + "]";
	}
	
}
