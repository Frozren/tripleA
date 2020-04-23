package dao;

import model.Player;

public interface IDAOPlayer extends DAO<Player, Integer> {

	public Player checkConnect(String name);
	
	public void deleteAI(Integer id);
}
