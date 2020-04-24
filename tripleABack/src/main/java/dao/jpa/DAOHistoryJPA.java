package dao.jpa;

import java.util.List;

import dao.IDAOHistory;
import model.History;

public class DAOHistoryJPA extends DAOJPA implements IDAOHistory {

	@Override
	public void insert(History entity) {
		this.em.getTransaction().begin();
		
		try {
			this.em.persist(entity);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); 
			this.em.getTransaction().rollback();
		}
	}

	@Override
	public History selectById(Integer id) {
		return this.em.find(History.class, id);
	}

	@Override
	public List<History> selectAll() {
		return this.em
				.createQuery("select h from History h", History.class)
				.getResultList();
	}

	@Override
	public void update(History entity) {
		this.em.getTransaction().begin();
		
		try {
			this.em.merge(entity);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Integer id) {
		this.em.getTransaction().begin();
		
		try {
			History historyToRemove = new History();
			historyToRemove.setId(id);
			
			this.em.remove(
				this.em.merge(historyToRemove)
			);
			
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
	}

}
