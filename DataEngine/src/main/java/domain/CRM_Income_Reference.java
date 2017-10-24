package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CRM_Income_Reference {
	private static final long serialVersionUID = 3454523211239003L;
//CRM start
	@Id
	private String id; //订单号
	private String stationId; //站址编码
	private String stationName; //站址名称
	private String source;	// 属性   注入、自建
	private String owner;	// 产权方   电信
	private String district;	// 区县
	private String towerTypeAmount; 	//塔型数量
	private String clientAmount;	//租户数量
	private String mobileOrderAmount;	//移动订单数
	private String unicomOrderAmount;	//联通订单数
	private String teleOrderAmount;	//电信订单数
	private String clients;	//运营商   移动,联通,电信
	private String firstClient;	//首家
	private String latestRentDate;	//最大起租时间
	private String orderType; // 新建
    private String productConfiguration;	//产品配置
    private String hangHeight;	//挂高
    private String towerType;	//塔型
    private String roomType;	//配套类型
    private String towerHeight;	//塔高
    private String antennaAmount;	//天线数
    private String systemAmount;	//系统数
    private String productUnit;	//产品单元
    private String rentDate;	//起租时间
    
    private String city;
    private String client;
    private String price;
    private String longitude;
    private String latitude;
//CRM end
    
//Property Start
    private String contractPricePerYear;
    private String averagePricePerYear;
//Property end
    
//PMS Start
    private String projectProgress;
    private String powerCableCost;
//PMS End
    
    public String getId() {
    	return id;
    }
    public void setId(String id) {
    	this.id = id;
    }
    public String getPowerCableCost() {
    	return powerCableCost;
    }
    public void setPowerCableCost(String powerCableCost) {
    	this.powerCableCost = powerCableCost;
    }
    public String getProjectProgress() {
    	return projectProgress;
    }
    public void setProjectProgress(String projectProgress) {
    	this.projectProgress = projectProgress;
    }
    public String getAveragePricePerYear() {
    	return averagePricePerYear;
    }
    public void setAveragePricePerYear(String averagePricePerYear) {
    	this.averagePricePerYear = averagePricePerYear;
    }
    public String getContractPricePerYear() {
    	return contractPricePerYear;
    }
    public void setContractPricePerYear(String contractPricePerYear) {
    	this.contractPricePerYear = contractPricePerYear;
    }
    public String getUnicomOrderAmount() {
		return unicomOrderAmount;
	}
	public void setUnicomOrderAmount(String unicomOrderAmount) {
		this.unicomOrderAmount = unicomOrderAmount;
	}
    public String getTowerTypeAmount() {
		return towerTypeAmount;
	}
	public void setTowerTypeAmount(String towerTypeAmount) {
		this.towerTypeAmount = towerTypeAmount;
	}
    public String getTeleOrderAmount() {
		return teleOrderAmount;
	}
	public void setTeleOrderAmount(String teleOrderAmount) {
		this.teleOrderAmount = teleOrderAmount;
	}
    public String getSystemAmount() {
		return systemAmount;
	}
	public void setSystemAmount(String systemAmount) {
		this.systemAmount = systemAmount;
	}
    public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
    public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
    public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
    public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
    public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
    public String getMobileOrderAmount() {
		return mobileOrderAmount;
	}
	public void setMobileOrderAmount(String mobileOrderAmount) {
		this.mobileOrderAmount = mobileOrderAmount;
	}
    public String getLatestRentDate() {
		return latestRentDate;
	}
	public void setLatestRentDate(String latestRentDate) {
		this.latestRentDate = latestRentDate;
	}
    public String getHangHeight() {
		return hangHeight;
	}
	public void setHangHeight(String hangHeight) {
		this.hangHeight = hangHeight;
	}
    public String getFirstClient() {
		return firstClient;
	}
	public void setFirstClient(String firstClient) {
		this.firstClient = firstClient;
	}
    public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
    public String getClients() {
		return clients;
	}
	public void setClients(String clients) {
		this.clients = clients;
	}
    public String getClientAmount() {
		return clientAmount;
	}
	public void setClientAmount(String clientAmount) {
		this.clientAmount = clientAmount;
	}
    public String getAntennaAmount() {
		return antennaAmount;
	}
	public void setAntennaAmount(String antennaAmount) {
		this.antennaAmount = antennaAmount;
	}
    public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
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

