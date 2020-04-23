package dao.jpa;

import java.util.List;

import dao.IDAOPlayer;
import model.AI;
import model.Human;
import model.Player;

public class DAOPlayerJPA extends DAOJPA implements IDAOPlayer {

	@Override
	public void insert(Player entity) {
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
	public Player selectById(Integer id) {
		
		return this.em.find(Player.class, id);
	}

	@Override
	public List<Player> selectAll() {
		return this.em
				.createQuery("select p from Player p", Player.class)
				.getResultList();
	}

	@Override
	public void update(Player entity) {
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
			//this.em.remove(this.SelectById(id));
			Human humanToRemove = new Human();
			humanToRemove.setId(id);
			
			AI aiToRemove = new AI();
			aiToRemove.setId(humanToRemove.getIdOpponent());
			
			this.em.remove(
				this.em.merge(humanToRemove)
			);
			
			this.em.remove(
					this.em.merge(aiToRemove)
			);
			
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
	}
}
