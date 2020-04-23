package dao.jpa;

import java.util.List;

import dao.IDAOCard;
import model.Card;
import model.Game;

public class DAOCardJPA extends DAOJPA implements IDAOCard {

	@Override
	public void insert(Card entity) {
		this.em.getTransaction().begin();
		
		try {
			this.em.persist(entity);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); 
			this.em.getTransaction().rollback();
		}
	}
	
	public Card addCard(Card entity) {
		Game.getInstance().getDaoCard().insert(entity);
		return entity;
	}

	@Override
	public Card selectById(Integer id) {
		
		return this.em.find(Card.class, id);
	}

	@Override
	public List<Card> selectAll() {
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
	public void delete(Integer id) {
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
