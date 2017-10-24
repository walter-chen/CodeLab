package hibernateLab;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Bid;
import domain.Item;

public class Workbench {

	public static void useJoin() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HibernateLab");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		String queryStr = "select i, b from Item i left join i.bids b on b.amount > 100";
		
		Query query = em.createQuery(queryStr);
		
		List<Object[]> list = query.getResultList();
		
		System.out.println("size: " + list.size());
		
		for(Object[] objects: list){
			if(objects[1] == null) continue; 
			System.out.println(((Bid)objects[1]).getAmount());
		}
		
		tx.commit();
		em.close();
		factory.close();
	}
	
	public static void useGrouping(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HibernateLab");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		String queryStr = "select i.name, avg(b.amount) from Bid b join b.item i group by i.name";	
		Query query = em.createQuery(queryStr);
		
		List<Object[]> list = query.getResultList();
		
		System.out.println("size: " + list.size());
		
		for(Object[] objects: list){
			System.out.println(((String)objects[0]));
		}
		
		tx.commit();
		em.close();
		factory.close();
	}
	
	public static void useSubselects(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HibernateLab");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		String queryStr = "select i from Item i "
				+ "where 101.00 = any ("
				+ "select b.amount from i.bids b"
				+ ")";	
		Query query = em.createQuery(queryStr);
		
		List<Item> list = query.getResultList();
		
		System.out.println("size: " + list.size());
		
		for(Item item: list){
			System.out.println(item.getName());
		}
		
		tx.commit();
		em.close();
		factory.close();
	}
	
	public static void useAggregation(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HibernateLab");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		String queryStr = "select min(b.amount), max(b.amount) from Bid b";	
		Query query = em.createQuery(queryStr);
		
		List<Object[]> list = query.getResultList();
		
		System.out.println("size: " + list.size());
		
		for(Object[] amount: list){
			System.out.println(""+(BigDecimal)amount[0]+"---"+(BigDecimal)amount[1]);
		}
		
		tx.commit();
		em.close();
		factory.close();
	}
	
	public static void main(String[] args) {
		useAggregation();
	}

}
