package dao;

import java.util.List;

public interface DAO<T, K> {
	public void insert(T p);
	
	public T selectById(K id);
	
	public List<T> selectAll();
	
	public void update(T p);
	
	public void delete(K id);
}
