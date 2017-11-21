package hibernateLab;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import domain.REPORT_CZCARD_INFO;
import domain.VIEW_STAFF_ROLE;

public class UseOracle {

	public static void main(String[] args){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHibernateLab-Inside");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String queryStr = "select i from REPORT_CZCARD_INFO i Where i.CARD_CODE = '350603019000000117-01'";
		Query query = em.createQuery(queryStr);
		List<REPORT_CZCARD_INFO> list = query.getResultList();
		System.out.println("size: " + list.size());
		for (REPORT_CZCARD_INFO object : list) {
			System.out.println(object.getACC_SUBJECT() + " " + object.getCARD_CODE());
		}
		
		REPORT_CZCARD_INFO info;
		info = em.find(REPORT_CZCARD_INFO.class, "350524908000000333-04"); // remove record by primary key "0"
		System.out.println(info.getACC_SUBJECT() + " " + info.getCARD_CODE());
		
		
		// native query 
		String sql="SELECT * FROM VIEW_STAFF_ROLE s where s.STAFF_NAME='郑春丽'";
		query= em.createNativeQuery(sql, VIEW_STAFF_ROLE.class);
		List<VIEW_STAFF_ROLE> list2 = query.getResultList();
		
		System.out.println("size: " + list2.size());
		for (VIEW_STAFF_ROLE object : list2) {
			System.out.println(object.getSTAFF_NAME() + " " + object.getACCOUNT());
		}
		
		tx.commit();
		em.close();
		factory.close();
	}
}
