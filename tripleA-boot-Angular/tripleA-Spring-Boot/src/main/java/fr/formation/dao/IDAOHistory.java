package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.History;

public interface IDAOHistory  extends JpaRepository<History, Integer>{
	@Query(value="select distinct name from History h where etat=1")
	public List<String> findNames();
		
	@Query(value="select count(*) from History h where etat=1 and name=?1")
	public Long countWin(String name);
	
}
