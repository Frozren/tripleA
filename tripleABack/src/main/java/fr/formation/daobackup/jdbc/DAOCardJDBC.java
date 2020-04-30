package fr.formation.daobackup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.formation.daobackup.IDAOCard;
import fr.formation.model.Card;
import fr.formation.model.Game;

public class DAOCardJDBC implements IDAOCard {

    @Override
    public void save(Card c) {
    	long key = -1L;
        try (Connection connect = Game.getInstance().getConnection();
             PreparedStatement ps = connect.prepareStatement("INSERT INTO card (life,atk,def) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
            ps.setInt(1, c.getLife());
            ps.setInt(2, c.getAtk());
            ps.setInt(3, c.getDef());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                System.out.println(key = rs.getLong(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int insert(Card c,boolean returnId) {
    	int key = -1;
        try (
        	Connection connect = Game.getInstance().getConnection();
            PreparedStatement ps = connect.prepareStatement("INSERT INTO card (life,atk,def) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ) {
            ps.setInt(1, c.getLife());
            ps.setInt(2, c.getAtk());
            ps.setInt(3, c.getDef());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
        
            if (rs.next()) {
               key= rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return key;
    }

    @Override
	public Card findById(Integer id) {
		Card c = null;
        try (Connection connect = Game.getInstance().getConnection();
                PreparedStatement ps = connect.prepareStatement("SELECT * from card where id=?");) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Card(rs.getInt("id"), rs.getInt("life"), rs.getInt("atk"), rs.getInt("def"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
	}

    @Override
    public List<Card> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Card t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub

    }

	

}