package fr.formation.dao;
import fr.formation.daobackup.DAO;
import fr.formation.model.Player;

public interface IDAOPlayer extends DAO<Player, Integer> {
	
	public Player findByName(String name);

}