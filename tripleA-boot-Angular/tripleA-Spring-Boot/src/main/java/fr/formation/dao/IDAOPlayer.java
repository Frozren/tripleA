package fr.formation.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Player;

public interface IDAOPlayer extends JpaRepository<Player, Integer> {
	
	public Player findByName(String name);
	
	public List<Player> findByTypePlayer(Boolean typePlayer);
}