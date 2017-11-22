package utility;

import java.util.HashMap;
import java.util.Map;

import pojo.Gps;

public class MyInfoMapper {
	public static Map<String, String> codeMapCity;
	public static Map<String, Gps> cityMapCenterGps;
	public static Map<String, String> cityMapZoomDegree;

	public MyInfoMapper() {
		this.codeMapCity = new HashMap<String, String>();
		codeMapCity.put("350100", "福州");
		codeMapCity.put("350200", "厦门");
		codeMapCity.put("350300", "莆田");
		codeMapCity.put("350400", "三明");
		codeMapCity.put("350500", "泉州");
		codeMapCity.put("350600", "漳州");
		codeMapCity.put("350700", "南平");
		codeMapCity.put("350800", "龙岩");
		codeMapCity.put("350900", "宁德");
		codeMapCity.put("35000", "福建");
		
		this.cityMapCenterGps = new HashMap<String, Gps>();
		cityMapCenterGps.put("%", new Gps(26.111578, 118.600348));
		cityMapCenterGps.put("福州", new Gps(26.088736, 119.298871));
		cityMapCenterGps.put("厦门", new Gps(24.620032, 118.136104));
		cityMapCenterGps.put("莆田", new Gps(25.453601, 119.032918));
		cityMapCenterGps.put("三明", new Gps(26.258332, 117.615625));
		cityMapCenterGps.put("泉州", new Gps(25.120452, 118.341521));
		cityMapCenterGps.put("漳州", new Gps(24.36945, 117.713367));
		cityMapCenterGps.put("南平", new Gps(27.300376, 118.344658));
		cityMapCenterGps.put("龙岩", new Gps(25.290485, 116.857872));
		cityMapCenterGps.put("宁德", new Gps(27.016757, 119.641898));
		
		this.cityMapZoomDegree = new HashMap<String, String>();
		cityMapZoomDegree.put("%", "8");
		cityMapZoomDegree.put("福州", "9");
		cityMapZoomDegree.put("厦门", "11");
		cityMapZoomDegree.put("莆田", "10");
		cityMapZoomDegree.put("三明", "9");
		cityMapZoomDegree.put("泉州", "9");
		cityMapZoomDegree.put("漳州", "9");
		cityMapZoomDegree.put("南平", "9");
		cityMapZoomDegree.put("龙岩", "9");
		cityMapZoomDegree.put("宁德", "9");
	}
}
