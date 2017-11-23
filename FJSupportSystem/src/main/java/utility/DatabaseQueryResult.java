package utility;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.OrderInfoShortcut;
import domain.ReportOrderInfo;


public class DatabaseQueryResult {
	public static List<OrderInfoShortcut> getDatabaseQueryResult(String sql){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHibernateLab-Inside");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query = em.createNativeQuery(sql, OrderInfoShortcut.class);
		List<OrderInfoShortcut> list = query.getResultList();
		System.out.println("size: " + list.size());
		tx.commit();
		em.close();
		factory.close();
		return list;
	}
	public static void main(String[] args){
		List<OrderInfoShortcut> list = getDatabaseQueryResult("select * from REPORT_ORDER_INFO i WHERE i.ORDER_STATUS='已起租'");
//		for(ReportOrderInfo temp : list){
//			System.out.println(temp.getTowerSitename());
//		}
	}
}
