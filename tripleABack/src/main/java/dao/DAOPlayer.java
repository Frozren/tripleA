package dao;

import model.Player;

public interface DAOPlayer extends DAO<Player, Integer> {

	public Player checkConnect(String name);
}
