package fr.formation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Game {

	private int passivePoints = 50;
	private int cardChoice = 5;
	private int bonusPts = 10;
	private boolean difficulty = false;
	private Connection connection = null;
	private static Game _instance = null;
	private Human h = new Human();
	private AI ai = new AI();

	private Game() {
	}

	public static Game getInstance() {
		if (_instance == null) {
			_instance = new Game();
		}
		return _instance;
	}
	
	public int getPassivePoints() {
		return passivePoints;
	}
	
	public void setPassivePoints(int passivePoints) {
		this.passivePoints = passivePoints;
	}

	public void setCardChoice(int cardChoice) {
		this.cardChoice = cardChoice;
	}

	public int getCardChoice() {
		return cardChoice;
	}
	
	public boolean getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(boolean difficulty) {
		this.difficulty = difficulty;
	}
	
	public Human getHuman() {
		return h;
	}

	public void setHuman(Human h) {
		this.h = h;
	}

	public AI getAI() {
		return ai;
	}

	public void setAI(AI ai) {
		this.ai = ai;
	}

	public int getBonusPts() {
		return bonusPts;
	}

	public void setBonusPts(int bonusPts) {
		this.bonusPts = bonusPts;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/triple_a", "root", "");
		return connection;
	}
}
