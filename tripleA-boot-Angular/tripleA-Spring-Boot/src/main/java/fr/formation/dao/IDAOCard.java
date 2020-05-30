package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Card;

public interface IDAOCard extends JpaRepository<Card, Integer> {
	
}
