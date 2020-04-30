package fr.formation.daobackup.jpa;

import java.util.List;

import fr.formation.daobackup.IDAOCard;
import fr.formation.model.Card;

public class DAOCardJPA extends DAOJPA implements IDAOCard {

	@Override
	public void save(Card entity) {
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
	public Card findById(Integer id) {
		
		return this.em.find(Card.class, id);
	}

	@Override
	public List<Card> findAll() {
		return null;
	}

	@Override
	public void update(Card entity) {
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
			//this.em.remove(this.SelectById(id));
			Card cardToRemove = new Card();
			cardToRemove.setId(id);
			
			this.em.remove(
				this.em.merge(cardToRemove)
			);
			
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
	}
}
