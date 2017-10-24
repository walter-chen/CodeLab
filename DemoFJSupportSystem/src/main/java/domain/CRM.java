package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CRM {
	private static final long serialVersionUID = 3444257623231239003L;
	@Id
	private String id;
	private String stationName;
    private String latitude;
    private String longitude;
    private String productConfiguration;
    private String price;
    private String towerType;
    private String towerHeight;
    private String roomType;
    private String client;
    private String city;
    private String stationId;
    
    public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getProductConfiguration() {
		return productConfiguration;
	}
	public void setProductConfiguration(String productConfiguration) {
		this.productConfiguration = productConfiguration;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTowerType() {
		return towerType;
	}
	public void setTowerType(String towerType) {
		this.towerType = towerType;
	}
	public String getTowerHeight() {
		return towerHeight;
	}
	public void setTowerHeight(String towerHeight) {
		this.towerHeight = towerHeight;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
}

