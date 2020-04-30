package fr.formation.daobackup;


import java.util.List;

import fr.formation.model.History;

public interface IDAOHistory extends DAO<History, Integer> {

	public List<String> selectNames();
	
	public Long countWin(String name);
	
}
