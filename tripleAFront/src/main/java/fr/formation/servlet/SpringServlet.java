package fr.formation.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.formation.dao.IDAOCard;
import fr.formation.dao.IDAOHistory;
import fr.formation.dao.IDAOPlayer;

//@WebServlet("/SpringServlet")
public class SpringServlet extends HttpServlet{

	@Autowired
	protected IDAOCard daoCard;
	
	@Autowired
	protected IDAOHistory daoHistory;
	
	@Autowired
	protected IDAOPlayer daoPlayer;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
}
