package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOJPA {
	protected final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TripleAUnit");
	
	protected final EntityManager em = emf.createEntityManager();

	public static void close() {
		emf.close();
	}
}
