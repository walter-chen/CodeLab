package producer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.json.JSONObject;

import domain.OrderInfoShortcut;
import domain.ReportOrderInfo;
import utility.Formula;

public class NeighborSeeker {
	public static JSONObject seekNeighbors(OrderInfoShortcut center, List<OrderInfoShortcut> candidates, String requiredDistance) {
		List<OrderInfoShortcut> neighbors = new ArrayList<OrderInfoShortcut>();
		int requiredDistanceInt = Integer.parseInt(requiredDistance);
		for (OrderInfoShortcut candidate : candidates) {
			double distance = Formula.GetDistance(
					Double.parseDouble(center.getLATITUDE()),
					Double.parseDouble(center.getLONGITUDE()), 
					Double.parseDouble(candidate.getLATITUDE()),
					Double.parseDouble(candidate.getLONGITUDE()));
			if (distance <= requiredDistanceInt){
				neighbors.add(candidate);
			}
		}
		JSONObject neighborsJSONObjectRoot = new JSONObject();
		for (OrderInfoShortcut tempSite : neighbors) {
			JSONObject jsonObjectChildLevel1 = new JSONObject();
			
			neighborsJSONObjectRoot.put(tempSite.getORDERID(), jsonObjectChildLevel1);
			jsonObjectChildLevel1.put("站址编码", tempSite.getSITECODE());
			jsonObjectChildLevel1.put("站址名称", tempSite.getDEMAND_SITENAME());
			jsonObjectChildLevel1.put("纬度", tempSite.getLATITUDE());
			jsonObjectChildLevel1.put("经度", tempSite.getLONGITUDE());
			jsonObjectChildLevel1.put("产品配置", tempSite.getPRODUCT_CONFIG());
			jsonObjectChildLevel1.put("价格", tempSite.getSUMFEE());
			jsonObjectChildLevel1.put("地市", tempSite.getCITY_S());
			jsonObjectChildLevel1.put("客户", tempSite.getOPERATOR());
			jsonObjectChildLevel1.put("配套类型", tempSite.getROOM_TYPE());
			jsonObjectChildLevel1.put("铁塔类型", tempSite.getTOWER_TYPE());
			jsonObjectChildLevel1.put("塔高", tempSite.getTOWER_HIGH());
		}
		return neighborsJSONObjectRoot;
	}
	public static void main(String[] args){
		EntityManagerFactory oracleOutsideFactory = Persistence.createEntityManagerFactory("OracleHibernateLab-Inside");
		EntityManager em = oracleOutsideFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		String queryStr = "select * from REPORT_ORDER_INFO i Where i.CITY_S= '厦门市'";
		System.out.println(queryStr);
		Query query = em.createNativeQuery(queryStr, OrderInfoShortcut.class);
		tx.begin();
		List<OrderInfoShortcut> candidates =query.getResultList();
		tx.commit();
		em.close();
		oracleOutsideFactory.close();
		
		OrderInfoShortcut center = new OrderInfoShortcut();
		center.setSITECODE("35020300000002");
		center.setDEMAND_SITENAME("滨北舒友酒楼");
		center.setLONGITUDE("118.079909");
		center.setLATITUDE("24.480661");
		center.setSUMFEE("4148.53");
		JSONObject jsonObject = NeighborSeeker.seekNeighbors(center, candidates, "500");
		System.out.println(jsonObject.toString());

	}
}
