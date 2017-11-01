package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CRM_SettleAccount_Order_Info {
	@Id
	private String id; //产品业务确认单编号
	private String month; //账期
	private String productBusinessConfirmOrder; //产品业务确认单编号
	private String client;
	private String clientCity;
	private String demandConfirmOrder;
	private String stationName;
	private String stationNo;
	private String serviceBeginDate;
	private String generateElec;
	private String tower;
	private String room;
	private String supportingFacility;
	private String placeRentFee;
	private String maintainFee;
	private String powerCable;
	private String allFee;
	private String towerSharedState;
	private String roomSharedState;
	private String supportingFacilitySharedState;
	
	
    public String getId() {
    	return id;
    }
    public void setId(String id) {
    	this.id = id;
    }
    public String getSupportingFacilitySharedState() {
    	return supportingFacilitySharedState;
    }
    public void setSupportingFacilitySharedState(String supportingFacilitySharedState) {
    	this.supportingFacilitySharedState = supportingFacilitySharedState;
    }
    public String getRoomSharedState() {
    	return roomSharedState;
    }
    public void setRoomSharedState(String roomSharedState) {
    	this.roomSharedState = roomSharedState;
    }
    public String getTowerSharedState() {
    	return towerSharedState;
    }
    public void setTowerSharedState(String towerSharedState) {
    	this.towerSharedState = towerSharedState;
    }
    public String getAllFee() {
    	return allFee;
    }
    public void setAllFee(String allFee) {
    	this.allFee = allFee;
    }
    public String getPowerCable() {
    	return powerCable;
    }
    public void setPowerCable(String powerCable) {
    	this.powerCable = powerCable;
    }
    public String getMaintainFee() {
    	return maintainFee;
    }
    public void setMaintainFee(String maintainFee) {
    	this.maintainFee = maintainFee;
    }
    public String getPlaceRentFee() {
    	return placeRentFee;
    }
    public void setPlaceRentFee(String placeRentFee) {
    	this.placeRentFee = placeRentFee;
    }
    public String getSupportingFacility() {
    	return supportingFacility;
    }
    public void setSupportingFacility(String supportingFacility) {
    	this.supportingFacility = supportingFacility;
    }
    public String getRoom() {
    	return room;
    }
    public void setRoom(String room) {
    	this.room = room;
    }
    public String getTower() {
    	return tower;
    }
    public void setTower(String tower) {
    	this.tower = tower;
    }
    public String getGenerateElec() {
    	return generateElec;
    }
    public void setGenerateElec(String generateElec) {
    	this.generateElec = generateElec;
    }
    public String getServiceBeginDate() {
    	return serviceBeginDate;
    }
    public void setServiceBeginDate(String serviceBeginDate) {
    	this.serviceBeginDate = serviceBeginDate;
    }
    public String getStationNo() {
    	return stationNo;
    }
    public void setStationNo(String stationNo) {
    	this.stationNo = stationNo;
    }
    public String getStationName() {
    	return stationName;
    }
    public void setStationName(String stationName) {
    	this.stationName = stationName;
    }
    public String getDemandConfirmOrder() {
    	return demandConfirmOrder;
    }
    public void setDemandConfirmOrder(String demandConfirmOrder) {
    	this.demandConfirmOrder = demandConfirmOrder;
    }
    public String getClientCity() {
    	return clientCity;
    }
    public void setClientCity(String clientCity) {
    	this.clientCity = clientCity;
    }
    public String getClient() {
    	return client;
    }
    public void setClient(String client) {
    	this.client = client;
    }
    public String getProductBusinessConfirmOrder() {
    	return productBusinessConfirmOrder;
    }
    public void setProductBusinessConfirmOrder(String productBusinessConfirmOrder) {
    	this.productBusinessConfirmOrder = productBusinessConfirmOrder;
    }
    public String getMonth() {
    	return month;
    }
    public void setMonth(String month) {
    	this.month = month;
    }
	
}
