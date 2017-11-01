package hibernateLab;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Bid;

public class RemoveAndDeleteRecord {
	private void remove(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReadDatabase");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Bid info;
		info = em.find(Bid.class, "0"); // remove record by primary key "0"
		em.remove(info);
		
		tx.commit();
		em.close();
		factory.close();
	}
	private void update(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReadDatabase");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Bid info = new Bid();
		info.setAmount(new BigDecimal(2.282));
		em.merge(info);// update record by primary key
		
		tx.commit();
		em.close();
		factory.close();
	}
}
