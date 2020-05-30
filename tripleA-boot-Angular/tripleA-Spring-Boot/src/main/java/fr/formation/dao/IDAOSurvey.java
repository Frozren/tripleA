package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Survey;

public interface IDAOSurvey  extends JpaRepository<Survey, Integer>{
		
	
}
