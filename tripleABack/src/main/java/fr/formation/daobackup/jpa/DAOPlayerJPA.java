package fr.formation.daobackup.jpa;

import java.util.List;

import fr.formation.daobackup.IDAOPlayer;
import fr.formation.model.AI;
import fr.formation.model.Human;
import fr.formation.model.Player;

public class DAOPlayerJPA extends DAOJPA implements IDAOPlayer {

	@Override
	public void save(Player entity) {
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
	public Player findById(Integer id) {
		
		return this.em.find(Player.class, id);
	}

	@Override
	public List<Player> findAll() {
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
	public void deleteById(Integer id) {
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
