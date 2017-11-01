package pojo;

import javax.persistence.Id;

public class SettleAccountOrderInfo {
	@Id
	public String id; //产品业务确认单编号
	public String month; //账期
	public String productBusinessConfirmOrder; //产品业务确认单编号
	public String client;
	public String clientCity;
	public String demandConfirmOrder;
	public String stationName;
	public String stationNo;
	public String serviceBeginDate;
	public String generateElec;
	public String tower;
	public String room;
	public String supportingFacility;
	public String placeRentFee;
	public String maintainFee;
	public String powerCable;
	public String allFee;
	public String towerSharedState;
	public String roomSharedState;
	public String supportingFacilitySharedState;
}
