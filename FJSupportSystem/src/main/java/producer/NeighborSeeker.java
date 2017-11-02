package producer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import domain.CRM;
import domain.CRM_Income_Reference;
import utility.DatabaseQueryResult;
import utility.Formula;

public class NeighborSeeker {
	public static JSONObject seekNeighbors(CRM_Income_Reference center, List<CRM_Income_Reference> candidates, String requiredDistance) {
		List<CRM_Income_Reference> neighbors = new ArrayList<CRM_Income_Reference>();
		int requiredDistanceInt = Integer.parseInt(requiredDistance);
		for (CRM_Income_Reference candidate : candidates) {
			double distance = Formula.GetDistance(
					Double.parseDouble(center.getLatitude()),
					Double.parseDouble(center.getLongitude()), 
					Double.parseDouble(candidate.getLatitude()),
					Double.parseDouble(candidate.getLongitude()));
			if (distance <= requiredDistanceInt)
				neighbors.add(candidate);
		}
		JSONObject neighborsJSONObjectRoot = new JSONObject();
		for (CRM_Income_Reference tempSite : neighbors) {
			JSONObject jsonObjectChildLevel1 = new JSONObject();
			neighborsJSONObjectRoot.put(tempSite.getId(), jsonObjectChildLevel1);
			jsonObjectChildLevel1.put("站址编码", tempSite.getStationId());
			jsonObjectChildLevel1.put("站址名称", tempSite.getStationName());
			jsonObjectChildLevel1.put("纬度", tempSite.getLatitude());
			jsonObjectChildLevel1.put("经度", tempSite.getLongitude());
			jsonObjectChildLevel1.put("产品配置", tempSite.getProductConfiguration());
			jsonObjectChildLevel1.put("价格", tempSite.getPrice());
			jsonObjectChildLevel1.put("地市", tempSite.getCity());
			jsonObjectChildLevel1.put("客户", tempSite.getClient());
			jsonObjectChildLevel1.put("配套类型", tempSite.getRoomType());
			jsonObjectChildLevel1.put("铁塔类型", tempSite.getTowerType());
			jsonObjectChildLevel1.put("塔高", tempSite.getTowerHeight());
		}
		return neighborsJSONObjectRoot;
	}
	public static void main(String[] args){
		CRM_Income_Reference center = new CRM_Income_Reference();
		center.setId("35020300000002");
		center.setStationName("滨北舒友酒楼");
		center.setLongitude("118.079909");
		center.setLatitude("24.480661");
		center.setPrice("4148.53");
		List<CRM_Income_Reference> candidates = DatabaseQueryResult.getDatabaseQueryResult("CRM");
		JSONObject jsonObject = NeighborSeeker.seekNeighbors(center, candidates, "500");
		System.out.println(jsonObject.toString());
	}
}
