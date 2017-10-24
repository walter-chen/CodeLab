package utility;

import java.util.List;

import domain.CRM;

public class Formula {
	private static final double EARTH_RADIUS = 6378.137;// 赤道半径(单位km)
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	public static double GetDistance(double lat1, double lon1, double lat2, double lon2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s*1000);
		return s;
	}
	public static String BdToGcj(double bd_lon, double bd_lat)
	{
		double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
		double x = bd_lon - 0.0065, y = bd_lat - 0.006;    
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);    
        double theta =Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);    
        double gg_lon = z * Math.cos(theta);    
        double gg_lat = z * Math.sin(theta);    
        return gg_lon+","+gg_lat;
	}
}
