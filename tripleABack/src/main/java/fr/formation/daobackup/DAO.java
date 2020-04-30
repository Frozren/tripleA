package fr.formation.daobackup;

import java.util.List;

public interface DAO<T, K> {
	public void save(T t);
	
	public T findById(K id);
	
	public List<T> findAll();
	
	public void update(T t);
	
	public void deleteById(K id);
}
