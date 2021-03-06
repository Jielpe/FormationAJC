package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import metier.IMessage;

public class DAO implements IDAO {

	// Design pattern Singleton
	private static IDAO instance;

	private static String TABLE_NAME = "messages";

	private static String CLASS_NAME = "Message";

	public static IDAO getInstance() {
		if (null == instance) {
			instance = new DAO();
		}
		return instance;
	}

	// Recuperer un entity managerFactory a partir de l'unite de persistance
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("ChatBCBG");
	// r�cup�rer un EntityManager � partir de l'EntityManagerFactory
	private static EntityManager em = emf.createEntityManager();
	// d�but transaction
	private static EntityTransaction tx = em.getTransaction();

	@Override
	public void resetDB() {
		System.out.println("Reseting...");

		tx.begin();
		// supprimer les �l�ments de la table formationhibernatetest01_formation
		em.createNativeQuery("delete from " + TABLE_NAME).executeUpdate();
		// fin transaction
		tx.commit();
		System.out.println("Table Cleared.");
	}

	// Start commit
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IDAO#startTx()
	 */
	@Override
	public void startTx() {
		tx.begin();
		System.out.println("Starting transaction...");
	}

	// End commit
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IDAO#endTx()
	 */
	@Override
	public void endTx() {
		tx.commit();
		System.out.println("Table synced.");
	}

	// End all
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IDAO#closeAll()
	 */
	@Override
	public void closeAll() {

		// fin EntityManager
		em.close();
		// fin EntityManagerFactory
		emf.close();
		// log
		System.out.println("Termin� ...");
	}

	// Save Object
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IDAO#insert(org.aspectj.bridge.IMessage)
	 */
	@Override
	public void insert(IMessage pMessage) {
		em.persist(pMessage);
		System.out.println(pMessage + " : saved into persistance domain. ");
	}

	
	// Remove Object
	@Override
	public void remove(IMessage pM) {
		em.remove(pM);
		System.out.println(pM + " : remove from persistance domain.");
	}

	// Select and prompt in console all objects inside the table
	@Override
	public ArrayList<IMessage> selectAll() {
		ArrayList<IMessage> lstMessage = new ArrayList<IMessage>();
		System.out.println("[" + CLASS_NAME + "]");
		for (Object s : em.createQuery("select f from " + CLASS_NAME + " f")
				.getResultList()) {
			lstMessage.add((IMessage) s);
		}
		return lstMessage;
	}
	// du gros caca
}
