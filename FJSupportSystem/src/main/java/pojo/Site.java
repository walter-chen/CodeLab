package pojo;

public class Site {
	public static String staId;
	public static String staName;
	public static String longitude;
	public static String latitude;
	public static String productConfiguration;
	public static String price;

	public Site(String staId, String staName, String longitude, String latitude, String productConfiguration,
			String price) {
		this.staId = staId;
		this.staName = staName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.productConfiguration = productConfiguration;
		this.price = price;
	}
}
