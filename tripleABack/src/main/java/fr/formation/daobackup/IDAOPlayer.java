package fr.formation.daobackup;

import fr.formation.model.Player;

public interface IDAOPlayer extends DAO<Player, Integer> {

	public Player checkConnect(String name);
	
	public void deleteAI(Integer id);
}
