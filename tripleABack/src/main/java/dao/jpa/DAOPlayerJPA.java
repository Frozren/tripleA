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
				.createQuery("select p from Player p where p.typePlayer = 1", Player.class)
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
			Human humanToRemove = new Human();
			humanToRemove.setId(id);
			
			this.em.remove(
				this.em.merge(humanToRemove)
			);
			
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
	}
	
	public void deleteAI(Integer id) {
		this.em.getTransaction().begin();
		
		try {
			AI aiToRemove = new AI();
			aiToRemove.setId(id);
			
			this.em.remove(
				this.em.merge(aiToRemove)
			);
			
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
	}

	@Override
	public Player checkConnect(String name) {
				
		Player p =null;
		
		try {
		p =  this.em
						.createQuery("select p from Player p where p.name = ?1", Player.class)
						.setParameter(1, name)
						.getSingleResult();
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return p;
	}
}
