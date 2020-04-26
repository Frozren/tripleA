package dao;


import java.util.List;

import model.History;

public interface IDAOHistory extends DAO<History, Integer> {

	public List<String> selectNames();
	
	public Long countWin(String name);
	
}
