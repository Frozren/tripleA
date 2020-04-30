//package fr.formation.daobackup.jdbc;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import fr.formation.daobackup.IDAOPlayer;
//import fr.formation.model.AI;
//import fr.formation.model.Card;
//import fr.formation.model.Game;
//import fr.formation.model.Human;
//import fr.formation.model.Player;
//
//public class DAOPlayerJDBC implements IDAOPlayer {
//	
//
//	@Override
//	public void save(Player p) {
////		try (Connection connect = Game.getInstance().getConnection();
////				PreparedStatement ps = connect.prepareStatement("INSERT INTO player (name,idCard1,idCard2,idCard3,typePlayer,idOpponent) VALUES (?,?,?,?,?,?)");) {
////			int idCard1 = Game.getInstance().getDaoCard().insert(p.getCard1(), true);
////			int idCard2 = Game.getInstance().getDaoCard().insert(p.getCard2(), true);
////			int idCard3 = Game.getInstance().getDaoCard().insert(p.getCard3(), true);
////
////			ps.setString(1, p.getName());
////			ps.setInt(2, idCard1);
////			ps.setInt(3, idCard2);
////			ps.setInt(4, idCard3);
////			ps.setBoolean(5, p.isTypePlayer());
////			ps.setInt(6, p.getIdOpponent());
////			ps.executeUpdate();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}
//
//	@Override
//	public Player findById(Integer id) {
//		Player p = null;
//		try (
//				Connection connect = Game.getInstance().getConnection();
//				PreparedStatement ps = connect.prepareStatement("SELECT * from player where id=?");
//			) {
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Card c1 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard1"));
//				Card c2 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard2"));
//				Card c3 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard3"));
//				if (rs.getBoolean("typePlayer")) {
//					p = new Human(rs.getInt("id"), rs.getString("name"), c1, c2, c3, rs.getBoolean("typePlayer"),
//							rs.getInt("idOpponent"));
//				}
//				if (!rs.getBoolean("typePlayer")) {
//					p = new AI(rs.getInt("id"), rs.getString("name"), c1, c2, c3, rs.getBoolean("typePlayer"),
//							rs.getInt("idOpponent"));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return p;
//	}
//
//	@Override
//	public List<Player> findAll() {
//		List<Player> p = new ArrayList();
//		try (
//				Connection connect = Game.getInstance().getConnection();
//				PreparedStatement ps = connect.prepareStatement("SELECT * from player where typePlayer=1");
//			) {
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Card c1 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard1"));
//				Card c2 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard2"));
//				Card c3 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard3"));
//				
//				p.add(new Human(rs.getInt("id"), rs.getString("name"), c1, c2, c3, rs.getBoolean("typePlayer"), rs.getInt("idOpponent")));
//			
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return p;
//	}
//
//	@Override
//	public void update(Player p) {
//		
//		try(
//				Connection connect = Game.getInstance().getConnection();
//				PreparedStatement ps = connect.prepareStatement("UPDATE player SET name=?, idOpponent=? WHERE id=?");
//			){
//			ps.setString(1, p.getName());
//			ps.setInt(2, p.getIdOpponent());
//			ps.setInt(3, p.getId());
//			ps.executeUpdate();
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	public Player checkConnect(String name) {
//		Player p = null;
//		try (
//				Connection connect = Game.getInstance().getConnection();
//				PreparedStatement ps = connect.prepareStatement("SELECT * from player where name=?");
//			) {
//			ps.setString(1, name);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Card c1 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard1"));
//				Card c2 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard2"));
//				Card c3 = Game.getInstance().getDaoCard().findById(rs.getInt("idCard3"));
//				if (rs.getBoolean("typePlayer")) {
//					p = new Human(rs.getInt("id"), rs.getString("name"),c1,c2,c3, rs.getBoolean("typePlayer"),
//							rs.getInt("idOpponent"));
//				}
//				if (!rs.getBoolean("typePlayer")) {
//					p = new AI(rs.getInt("id"), rs.getString("name"),c1,c2,c3, rs.getBoolean("typePlayer"),
//							rs.getInt("idOpponent"));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return p;
//	}
//
//	@Override
//	public void deleteAI(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}