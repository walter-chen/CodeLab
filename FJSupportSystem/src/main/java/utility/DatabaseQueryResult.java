package utility;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.json.JSONObject;

import domain.CRM;


public class DatabaseQueryResult {
	public static List getDatabaseQueryResult(String tableName){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoFJSupportSystem");
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select i from " + tableName + " i");
		List list = query.getResultList();
		em.close();
		factory.close();
		return list;
	}
	public static void main(String[] args){
		List<CRM> list = getDatabaseQueryResult("CRM");
		for(CRM temp : list){
			System.out.println(temp.getStationName());
		}
	}
}
