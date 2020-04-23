package dao;

import model.Card;
import model.Player;

public interface IDAOCard extends DAO<Card, Integer> {

	public int insert(Card c,boolean returnId);
		
}
