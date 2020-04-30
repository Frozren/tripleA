package fr.formation.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Player;

public interface IDAOPlayer extends JpaRepository<Player, Integer> {
	
	@Query("select p from Player p where p.name = ?1")
	public Player findByName(String name);

}