package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "REPORT_ORDER_INFO")
@NamedQuery(name = "ReportOrderInfo.findAll", query = "SELECT r FROM ReportOrderInfo r")
public class ReportOrderInfo implements Serializable {
	private static final long serialVersionUID = 898437626374221L;
	
	@Id
	private String orderid;
	
	@Column(name = "ACTUAL_WIND")
	private String actualWind;

	private String address;

	@Column(name = "ANT_HEIGHT1")
	private String antHeight1;

	@Column(name = "ANT_HEIGHT2")
	private String antHeight2;

	@Column(name = "ANT_HEIGHT3")
	private String antHeight3;

	@Column(name = "ANT_NUM1")
	private String antNum1;

	@Column(name = "ANT_NUM2")
	private String antNum2;

	@Column(name = "ANT_NUM3")
	private String antNum3;

	@Column(name = "BATTERY_EXTRADURA")
	private String batteryExtradura;

	@Column(name = "BATTERY_EXTRAFEE")
	private String batteryExtrafee;

	@Column(name = "BATTERY_EXTRAFEE_ORI")
	private String batteryExtrafeeOri;

	@Column(name = "BBU_NUM")
	private String bbuNum;

	@Column(name = "BBUINSTALL_ROOMFEE")
	private String bbuinstallRoomfee;

	private String built;

	@Column(name = "CHARGING_SYSNUM1")
	private String chargingSysnum1;

	@Column(name = "CHARGING_SYSNUM2")
	private String chargingSysnum2;

	@Column(name = "CHARGING_SYSNUM3")
	private String chargingSysnum3;

	@Column(name = "CITY_S")
	private String cityS;

	@Column(name = "CON_DEMAND_ORDER")
	private String conDemandOrder;

	private String cuspowerwaste;

	@Column(name = "DATE_RENT")
	private String dateRent;

	@Column(name = "DATE_SIGN")
	private String dateSign;

	@Column(name = "DELIVERY_RESULT")
	private String deliveryResult;

	@Column(name = "DEMAND_BATCH")
	private String demandBatch;

	@Column(name = "DEMAND_COLLECTDATE")
	private String demandCollectdate;

	@Column(name = "DEMAND_SITENAME")
	private String demandSitename;

	private String eleservicefee;

	@Column(name = "ELESERVICEFEE_MODEL")
	private String eleservicefeeModel;

	@Column(name = "ELESERVICEFEE_ORI")
	private String eleservicefeeOri;

	@Column(name = "END_DATE_RENT")
	private String endDateRent;

	@Column(name = "END_DELIVERY_DATE")
	private String endDeliveryDate;

	@Column(name = "FEE_WIND")
	private String feeWind;

	private String fieldfee;

	@Column(name = "FIELDFEE_MODEL")
	private String fieldfeeModel;

	@Column(name = "FIELDFEE_ORI")
	private String fieldfeeOri;

	@Column(name = "FIELDFEE_SHAREDISC")
	private String fieldfeeSharedisc;

	@Column(name = "FIELDFEE_UNITPRICE")
	private String fieldfeeUnitprice;

	@Column(name = "HGRADE_SERVICEFEE")
	private String hgradeServicefee;

	@Column(name = "HGRADE_SERVICEFEE_ORI")
	private String hgradeServicefeeOri;

	@Column(name = "HRAILORMETRO_NAME")
	private String hrailormetroName;

	@Column(name = "INCR_CAPTOR")
	private String incrCaptor;

	@Column(name = "INCR_CAPTORNUM")
	private String incrCaptornum;

	@Column(name = "IS_BBUINTWROOM1")
	private String isBbuintwroom1;

	@Column(name = "IS_BBUINTWROOM2")
	private String isBbuintwroom2;

	@Column(name = "IS_BBUINTWROOM3")
	private String isBbuintwroom3;

	@Column(name = "IS_CHOICEPOWER")
	private String isChoicepower;

	@Column(name = "IS_FIELDFEESHARE")
	private String isFieldfeeshare;

	@Column(name = "IS_HRAILORMETRO")
	private String isHrailormetro;

	@Column(name = "IS_POWER_SERV")
	private String isPowerServ;

	@Column(name = "IS_POWERINFEESHARE")
	private String isPowerinfeeshare;

	@Column(name = "IS_ROOMFIRST")
	private String isRoomfirst;

	@Column(name = "IS_RRUUPTW1")
	private String isRruuptw1;

	@Column(name = "IS_RRUUPTW2")
	private String isRruuptw2;

	@Column(name = "IS_RRUUPTW3")
	private String isRruuptw3;

	@Column(name = "IS_SHAREEXISTSITE")
	private String isShareexistsite;

	@Column(name = "IS_STAND06")
	private String isStand06;

	@Column(name = "IS_THEFIRST")
	private String isThefirst;

	@Column(name = "IS_TOWERFIRST")
	private String isTowerfirst;

	@Column(name = "JZCB_MODE")
	private String jzcbMode;

	private String latitude;

	@Column(name = "LEAD_TIME")
	private String leadTime;

	private String longitude;

	@Column(name = "LX_APP_TIME")
	private String lxAppTime;

	@Column(name = "MAINSERVICE_LEVEL")
	private String mainserviceLevel;

	@Column(name = "MAINTENANCE_FEE")
	private String maintenanceFee;

	@Column(name = "MGR_DISTRICT")
	private String mgrDistrict;

	private String microwave;

	@Column(name = "MICROWAVE_ANTHIGH")
	private String microwaveAnthigh;

	@Column(name = "MICROWAVE_ORI")
	private String microwaveOri;

	private String operator;

	@Column(name = "OPERATOR_SITECODE")
	private String operatorSitecode;

	@Column(name = "ORDER_ATTRIBUTE")
	private String orderAttribute;

	@Column(name = "ORDER_STATUS")
	private String orderStatus;

	@Column(name = "ORIG_CAPTOR")
	private String origCaptor;

	@Column(name = "ORIG_CAPTORNUM")
	private String origCaptornum;

	@Column(name = "OTHERFEE_FIELD")
	private String otherfeeField;

	@Column(name = "OTHERFEE_FIELD_ORI")
	private String otherfeeFieldOri;

	@Column(name = "OTHERFEE_MAINTAIN")
	private String otherfeeMaintain;

	@Column(name = "OTHERFEE_MAINTAIN_ORI")
	private String otherfeeMaintainOri;

	@Column(name = "OTHERFEE_POWERIN")
	private String otherfeePowerin;

	@Column(name = "OTHERFEE_POWERIN_ORI")
	private String otherfeePowerinOri;

	@Column(name = "OTHERFEE_POWERSER_ORI")
	private String otherfeePowerserOri;

	@Column(name = "OTHERFEE_POWERSERV")
	private String otherfeePowerserv;

	@Column(name = "OTHERFEE_POWERSERV_ORI")
	private String otherfeePowerservOri;

	@Column(name = "OTHERFEE_POWERSERVIDE")
	private String otherfeePowerservide;

	@Column(name = "OTHERFEE_RRU")
	private String otherfeeRru;

	@Column(name = "OTHERFEE_RRU_ORI")
	private String otherfeeRruOri;

	@Column(name = "OTHERFEE_RRUMODEL")
	private String otherfeeRrumodel;

	@Column(name = "OTHERRRU_DISCOUNTS")
	private String otherrruDiscounts;

	@Column(name = "PLATFORMORLAYER_NUM")
	private String platformorlayerNum;

	@Column(name = "POWER_SERVFEE")
	private String powerServfee;

	@Column(name = "POWER_SERVFEE_MODEL")
	private String powerServfeeModel;

	@Column(name = "POWER_SERVFEE_ORI")
	private String powerServfeeOri;

	@Column(name = "POWER_WAY")
	private String powerWay;

	private String powerinfee;

	@Column(name = "POWERINFEE_MODEL")
	private String powerinfeeModel;

	@Column(name = "POWERINFEE_ORI")
	private String powerinfeeOri;

	@Column(name = "POWERINFEE_SHAREDISC")
	private String powerinfeeSharedisc;

	@Column(name = "POWERINFEE_UNITPRICE")
	private String powerinfeeUnitprice;

	@Column(name = "PROCON_STATUS")
	private String proconStatus;

	@Column(name = "PRODUCT_CONFIG")
	private String productConfig;

	@Column(name = "PRODUCT_PRICE")
	private String productPrice;

	@Column(name = "PRODUCTUNIT_NUM")
	private String productunitNum;

	@Column(name = "PROPERTY_ATTRIBUTE")
	private String propertyAttribute;

	@Column(name = "PROTOCOL_NUMBER")
	private String protocolNumber;

	@Column(name = "PROVINCE_S")
	private String provinceS;

	@Column(name = "PT_COST")
	private String ptCost;

	@Column(name = "PT_SHAREDISC")
	private String ptSharedisc;

	@Column(name = "PT_UNITPRICE")
	private String ptUnitprice;

	@Column(name = "PTBUILD_ORICOST")
	private String ptbuildOricost;

	@Column(name = "REAL_END_TIME")
	private String realEndTime;

	@Column(name = "REGION_S")
	private String regionS;

	private String remark;

	@Column(name = "REMARK_NEW")
	private String remarkNew;

	@Column(name = "ROOM_BUILDCOST")
	private String roomBuildcost;

	@Column(name = "ROOM_ID")
	private String roomId;

	@Column(name = "ROOM_NAME")
	private String roomName;

	@Column(name = "ROOM_SHAREDISC")
	private String roomSharedisc;

	@Column(name = "ROOM_TYPE")
	private String roomType;

	@Column(name = "ROOMBUILD_ORICOST")
	private String roombuildOricost;

	@Column(name = "ROOMCOST_UNITPRICE")
	private String roomcostUnitprice;

	@Column(name = "RRU_GUIDEPRICE")
	private String rruGuideprice;

	@Column(name = "RRU_NUM")
	private String rruNum;

	@Column(name = "RRU_POWERMODE")
	private String rruPowermode;

	private String scene;

	@Column(name = "SEATS_NUM")
	private String seatsNum;

	private String serialid;

	@Column(name = "SITE_SOURCE")
	private String siteSource;

	private String sitecode;

	private String sumfee;

	@Column(name = "SYS_NAME1")
	private String sysName1;

	@Column(name = "SYS_NAME2")
	private String sysName2;

	@Column(name = "SYS_NAME3")
	private String sysName3;

	@Column(name = "SYS_NUM")
	private String sysNum;

	@Column(name = "TB_WYRENT_CWZC")
	private String tbWyrentCwzc;

	@Column(name = "TB_XZF")
	private String tbXzf;

	@Column(name = "TERMINATION_DATE")
	private String terminationDate;

	@Column(name = "TOWER_COSTNAME")
	private String towerCostname;

	@Column(name = "TOWER_HIGH")
	private String towerHigh;

	@Column(name = "TOWER_ID")
	private String towerId;

	@Column(name = "TOWER_NAME")
	private String towerName;

	@Column(name = "TOWER_SITENAME")
	private String towerSitename;

	@Column(name = "TOWER_TYPE")
	private String towerType;

	@Column(name = "TW_BUILDCOST")
	private String twBuildcost;

	@Column(name = "TW_SHARE_DISC")
	private String twShareDisc;

	@Column(name = "TWBUILD_ORICOST")
	private String twbuildOricost;

	@Column(name = "TWCOST_UNITPRICE")
	private String twcostUnitprice;

	@Column(name = "WH_SHAREDISC")
	private String whSharedisc;

	@Column(name = "WH_UNITPRICE")
	private String whUnitprice;

	@Column(name = "WHBUILD_ORI")
	private String whbuildOri;

	private String wlan;

	@Column(name = "WLAN_ORI")
	private String wlanOri;

	@Column(name = "WLANANT_HEIGH")
	private String wlanantHeigh;

	@Column(name = "YEAR_GUIDEPRICE")
	private String yearGuideprice;

	public ReportOrderInfo() {
	}

	public String getActualWind() {
		return this.actualWind;
	}

	public void setActualWind(String actualWind) {
		this.actualWind = actualWind;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAntHeight1() {
		return this.antHeight1;
	}

	public void setAntHeight1(String antHeight1) {
		this.antHeight1 = antHeight1;
	}

	public String getAntHeight2() {
		return this.antHeight2;
	}

	public void setAntHeight2(String antHeight2) {
		this.antHeight2 = antHeight2;
	}

	public String getAntHeight3() {
		return this.antHeight3;
	}

	public void setAntHeight3(String antHeight3) {
		this.antHeight3 = antHeight3;
	}

	public String getAntNum1() {
		return this.antNum1;
	}

	public void setAntNum1(String antNum1) {
		this.antNum1 = antNum1;
	}

	public String getAntNum2() {
		return this.antNum2;
	}

	public void setAntNum2(String antNum2) {
		this.antNum2 = antNum2;
	}

	public String getAntNum3() {
		return this.antNum3;
	}

	public void setAntNum3(String antNum3) {
		this.antNum3 = antNum3;
	}

	public String getBatteryExtradura() {
		return this.batteryExtradura;
	}

	public void setBatteryExtradura(String batteryExtradura) {
		this.batteryExtradura = batteryExtradura;
	}

	public String getBatteryExtrafee() {
		return this.batteryExtrafee;
	}

	public void setBatteryExtrafee(String batteryExtrafee) {
		this.batteryExtrafee = batteryExtrafee;
	}

	public String getBatteryExtrafeeOri() {
		return this.batteryExtrafeeOri;
	}

	public void setBatteryExtrafeeOri(String batteryExtrafeeOri) {
		this.batteryExtrafeeOri = batteryExtrafeeOri;
	}

	public String getBbuNum() {
		return this.bbuNum;
	}

	public void setBbuNum(String bbuNum) {
		this.bbuNum = bbuNum;
	}

	public String getBbuinstallRoomfee() {
		return this.bbuinstallRoomfee;
	}

	public void setBbuinstallRoomfee(String bbuinstallRoomfee) {
		this.bbuinstallRoomfee = bbuinstallRoomfee;
	}

	public String getBuilt() {
		return this.built;
	}

	public void setBuilt(String built) {
		this.built = built;
	}

	public String getChargingSysnum1() {
		return this.chargingSysnum1;
	}

	public void setChargingSysnum1(String chargingSysnum1) {
		this.chargingSysnum1 = chargingSysnum1;
	}

	public String getChargingSysnum2() {
		return this.chargingSysnum2;
	}

	public void setChargingSysnum2(String chargingSysnum2) {
		this.chargingSysnum2 = chargingSysnum2;
	}

	public String getChargingSysnum3() {
		return this.chargingSysnum3;
	}

	public void setChargingSysnum3(String chargingSysnum3) {
		this.chargingSysnum3 = chargingSysnum3;
	}

	public String getCityS() {
		return this.cityS;
	}

	public void setCityS(String cityS) {
		this.cityS = cityS;
	}

	public String getConDemandOrder() {
		return this.conDemandOrder;
	}

	public void setConDemandOrder(String conDemandOrder) {
		this.conDemandOrder = conDemandOrder;
	}

	public String getCuspowerwaste() {
		return this.cuspowerwaste;
	}

	public void setCuspowerwaste(String cuspowerwaste) {
		this.cuspowerwaste = cuspowerwaste;
	}

	public String getDateRent() {
		return this.dateRent;
	}

	public void setDateRent(String dateRent) {
		this.dateRent = dateRent;
	}

	public String getDateSign() {
		return this.dateSign;
	}

	public void setDateSign(String dateSign) {
		this.dateSign = dateSign;
	}

	public String getDeliveryResult() {
		return this.deliveryResult;
	}

	public void setDeliveryResult(String deliveryResult) {
		this.deliveryResult = deliveryResult;
	}

	public String getDemandBatch() {
		return this.demandBatch;
	}

	public void setDemandBatch(String demandBatch) {
		this.demandBatch = demandBatch;
	}

	public String getDemandCollectdate() {
		return this.demandCollectdate;
	}

	public void setDemandCollectdate(String demandCollectdate) {
		this.demandCollectdate = demandCollectdate;
	}

	public String getDemandSitename() {
		return this.demandSitename;
	}

	public void setDemandSitename(String demandSitename) {
		this.demandSitename = demandSitename;
	}

	public String getEleservicefee() {
		return this.eleservicefee;
	}

	public void setEleservicefee(String eleservicefee) {
		this.eleservicefee = eleservicefee;
	}

	public String getEleservicefeeModel() {
		return this.eleservicefeeModel;
	}

	public void setEleservicefeeModel(String eleservicefeeModel) {
		this.eleservicefeeModel = eleservicefeeModel;
	}

	public String getEleservicefeeOri() {
		return this.eleservicefeeOri;
	}

	public void setEleservicefeeOri(String eleservicefeeOri) {
		this.eleservicefeeOri = eleservicefeeOri;
	}

	public String getEndDateRent() {
		return this.endDateRent;
	}

	public void setEndDateRent(String endDateRent) {
		this.endDateRent = endDateRent;
	}

	public String getEndDeliveryDate() {
		return this.endDeliveryDate;
	}

	public void setEndDeliveryDate(String endDeliveryDate) {
		this.endDeliveryDate = endDeliveryDate;
	}

	public String getFeeWind() {
		return this.feeWind;
	}

	public void setFeeWind(String feeWind) {
		this.feeWind = feeWind;
	}

	public String getFieldfee() {
		return this.fieldfee;
	}

	public void setFieldfee(String fieldfee) {
		this.fieldfee = fieldfee;
	}

	public String getFieldfeeModel() {
		return this.fieldfeeModel;
	}

	public void setFieldfeeModel(String fieldfeeModel) {
		this.fieldfeeModel = fieldfeeModel;
	}

	public String getFieldfeeOri() {
		return this.fieldfeeOri;
	}

	public void setFieldfeeOri(String fieldfeeOri) {
		this.fieldfeeOri = fieldfeeOri;
	}

	public String getFieldfeeSharedisc() {
		return this.fieldfeeSharedisc;
	}

	public void setFieldfeeSharedisc(String fieldfeeSharedisc) {
		this.fieldfeeSharedisc = fieldfeeSharedisc;
	}

	public String getFieldfeeUnitprice() {
		return this.fieldfeeUnitprice;
	}

	public void setFieldfeeUnitprice(String fieldfeeUnitprice) {
		this.fieldfeeUnitprice = fieldfeeUnitprice;
	}

	public String getHgradeServicefee() {
		return this.hgradeServicefee;
	}

	public void setHgradeServicefee(String hgradeServicefee) {
		this.hgradeServicefee = hgradeServicefee;
	}

	public String getHgradeServicefeeOri() {
		return this.hgradeServicefeeOri;
	}

	public void setHgradeServicefeeOri(String hgradeServicefeeOri) {
		this.hgradeServicefeeOri = hgradeServicefeeOri;
	}

	public String getHrailormetroName() {
		return this.hrailormetroName;
	}

	public void setHrailormetroName(String hrailormetroName) {
		this.hrailormetroName = hrailormetroName;
	}

	public String getIncrCaptor() {
		return this.incrCaptor;
	}

	public void setIncrCaptor(String incrCaptor) {
		this.incrCaptor = incrCaptor;
	}

	public String getIncrCaptornum() {
		return this.incrCaptornum;
	}

	public void setIncrCaptornum(String incrCaptornum) {
		this.incrCaptornum = incrCaptornum;
	}

	public String getIsBbuintwroom1() {
		return this.isBbuintwroom1;
	}

	public void setIsBbuintwroom1(String isBbuintwroom1) {
		this.isBbuintwroom1 = isBbuintwroom1;
	}

	public String getIsBbuintwroom2() {
		return this.isBbuintwroom2;
	}

	public void setIsBbuintwroom2(String isBbuintwroom2) {
		this.isBbuintwroom2 = isBbuintwroom2;
	}

	public String getIsBbuintwroom3() {
		return this.isBbuintwroom3;
	}

	public void setIsBbuintwroom3(String isBbuintwroom3) {
		this.isBbuintwroom3 = isBbuintwroom3;
	}

	public String getIsChoicepower() {
		return this.isChoicepower;
	}

	public void setIsChoicepower(String isChoicepower) {
		this.isChoicepower = isChoicepower;
	}

	public String getIsFieldfeeshare() {
		return this.isFieldfeeshare;
	}

	public void setIsFieldfeeshare(String isFieldfeeshare) {
		this.isFieldfeeshare = isFieldfeeshare;
	}

	public String getIsHrailormetro() {
		return this.isHrailormetro;
	}

	public void setIsHrailormetro(String isHrailormetro) {
		this.isHrailormetro = isHrailormetro;
	}

	public String getIsPowerServ() {
		return this.isPowerServ;
	}

	public void setIsPowerServ(String isPowerServ) {
		this.isPowerServ = isPowerServ;
	}

	public String getIsPowerinfeeshare() {
		return this.isPowerinfeeshare;
	}

	public void setIsPowerinfeeshare(String isPowerinfeeshare) {
		this.isPowerinfeeshare = isPowerinfeeshare;
	}

	public String getIsRoomfirst() {
		return this.isRoomfirst;
	}

	public void setIsRoomfirst(String isRoomfirst) {
		this.isRoomfirst = isRoomfirst;
	}

	public String getIsRruuptw1() {
		return this.isRruuptw1;
	}

	public void setIsRruuptw1(String isRruuptw1) {
		this.isRruuptw1 = isRruuptw1;
	}

	public String getIsRruuptw2() {
		return this.isRruuptw2;
	}

	public void setIsRruuptw2(String isRruuptw2) {
		this.isRruuptw2 = isRruuptw2;
	}

	public String getIsRruuptw3() {
		return this.isRruuptw3;
	}

	public void setIsRruuptw3(String isRruuptw3) {
		this.isRruuptw3 = isRruuptw3;
	}

	public String getIsShareexistsite() {
		return this.isShareexistsite;
	}

	public void setIsShareexistsite(String isShareexistsite) {
		this.isShareexistsite = isShareexistsite;
	}

	public String getIsStand06() {
		return this.isStand06;
	}

	public void setIsStand06(String isStand06) {
		this.isStand06 = isStand06;
	}

	public String getIsThefirst() {
		return this.isThefirst;
	}

	public void setIsThefirst(String isThefirst) {
		this.isThefirst = isThefirst;
	}

	public String getIsTowerfirst() {
		return this.isTowerfirst;
	}

	public void setIsTowerfirst(String isTowerfirst) {
		this.isTowerfirst = isTowerfirst;
	}

	public String getJzcbMode() {
		return this.jzcbMode;
	}

	public void setJzcbMode(String jzcbMode) {
		this.jzcbMode = jzcbMode;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLeadTime() {
		return this.leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLxAppTime() {
		return this.lxAppTime;
	}

	public void setLxAppTime(String lxAppTime) {
		this.lxAppTime = lxAppTime;
	}

	public String getMainserviceLevel() {
		return this.mainserviceLevel;
	}

	public void setMainserviceLevel(String mainserviceLevel) {
		this.mainserviceLevel = mainserviceLevel;
	}

	public String getMaintenanceFee() {
		return this.maintenanceFee;
	}

	public void setMaintenanceFee(String maintenanceFee) {
		this.maintenanceFee = maintenanceFee;
	}

	public String getMgrDistrict() {
		return this.mgrDistrict;
	}

	public void setMgrDistrict(String mgrDistrict) {
		this.mgrDistrict = mgrDistrict;
	}

	public String getMicrowave() {
		return this.microwave;
	}

	public void setMicrowave(String microwave) {
		this.microwave = microwave;
	}

	public String getMicrowaveAnthigh() {
		return this.microwaveAnthigh;
	}

	public void setMicrowaveAnthigh(String microwaveAnthigh) {
		this.microwaveAnthigh = microwaveAnthigh;
	}

	public String getMicrowaveOri() {
		return this.microwaveOri;
	}

	public void setMicrowaveOri(String microwaveOri) {
		this.microwaveOri = microwaveOri;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorSitecode() {
		return this.operatorSitecode;
	}

	public void setOperatorSitecode(String operatorSitecode) {
		this.operatorSitecode = operatorSitecode;
	}

	public String getOrderAttribute() {
		return this.orderAttribute;
	}

	public void setOrderAttribute(String orderAttribute) {
		this.orderAttribute = orderAttribute;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrigCaptor() {
		return this.origCaptor;
	}

	public void setOrigCaptor(String origCaptor) {
		this.origCaptor = origCaptor;
	}

	public String getOrigCaptornum() {
		return this.origCaptornum;
	}

	public void setOrigCaptornum(String origCaptornum) {
		this.origCaptornum = origCaptornum;
	}

	public String getOtherfeeField() {
		return this.otherfeeField;
	}

	public void setOtherfeeField(String otherfeeField) {
		this.otherfeeField = otherfeeField;
	}

	public String getOtherfeeFieldOri() {
		return this.otherfeeFieldOri;
	}

	public void setOtherfeeFieldOri(String otherfeeFieldOri) {
		this.otherfeeFieldOri = otherfeeFieldOri;
	}

	public String getOtherfeeMaintain() {
		return this.otherfeeMaintain;
	}

	public void setOtherfeeMaintain(String otherfeeMaintain) {
		this.otherfeeMaintain = otherfeeMaintain;
	}

	public String getOtherfeeMaintainOri() {
		return this.otherfeeMaintainOri;
	}

	public void setOtherfeeMaintainOri(String otherfeeMaintainOri) {
		this.otherfeeMaintainOri = otherfeeMaintainOri;
	}

	public String getOtherfeePowerin() {
		return this.otherfeePowerin;
	}

	public void setOtherfeePowerin(String otherfeePowerin) {
		this.otherfeePowerin = otherfeePowerin;
	}

	public String getOtherfeePowerinOri() {
		return this.otherfeePowerinOri;
	}

	public void setOtherfeePowerinOri(String otherfeePowerinOri) {
		this.otherfeePowerinOri = otherfeePowerinOri;
	}

	public String getOtherfeePowerserOri() {
		return this.otherfeePowerserOri;
	}

	public void setOtherfeePowerserOri(String otherfeePowerserOri) {
		this.otherfeePowerserOri = otherfeePowerserOri;
	}

	public String getOtherfeePowerserv() {
		return this.otherfeePowerserv;
	}

	public void setOtherfeePowerserv(String otherfeePowerserv) {
		this.otherfeePowerserv = otherfeePowerserv;
	}

	public String getOtherfeePowerservOri() {
		return this.otherfeePowerservOri;
	}

	public void setOtherfeePowerservOri(String otherfeePowerservOri) {
		this.otherfeePowerservOri = otherfeePowerservOri;
	}

	public String getOtherfeePowerservide() {
		return this.otherfeePowerservide;
	}

	public void setOtherfeePowerservide(String otherfeePowerservide) {
		this.otherfeePowerservide = otherfeePowerservide;
	}

	public String getOtherfeeRru() {
		return this.otherfeeRru;
	}

	public void setOtherfeeRru(String otherfeeRru) {
		this.otherfeeRru = otherfeeRru;
	}

	public String getOtherfeeRruOri() {
		return this.otherfeeRruOri;
	}

	public void setOtherfeeRruOri(String otherfeeRruOri) {
		this.otherfeeRruOri = otherfeeRruOri;
	}

	public String getOtherfeeRrumodel() {
		return this.otherfeeRrumodel;
	}

	public void setOtherfeeRrumodel(String otherfeeRrumodel) {
		this.otherfeeRrumodel = otherfeeRrumodel;
	}

	public String getOtherrruDiscounts() {
		return this.otherrruDiscounts;
	}

	public void setOtherrruDiscounts(String otherrruDiscounts) {
		this.otherrruDiscounts = otherrruDiscounts;
	}

	public String getPlatformorlayerNum() {
		return this.platformorlayerNum;
	}

	public void setPlatformorlayerNum(String platformorlayerNum) {
		this.platformorlayerNum = platformorlayerNum;
	}

	public String getPowerServfee() {
		return this.powerServfee;
	}

	public void setPowerServfee(String powerServfee) {
		this.powerServfee = powerServfee;
	}

	public String getPowerServfeeModel() {
		return this.powerServfeeModel;
	}

	public void setPowerServfeeModel(String powerServfeeModel) {
		this.powerServfeeModel = powerServfeeModel;
	}

	public String getPowerServfeeOri() {
		return this.powerServfeeOri;
	}

	public void setPowerServfeeOri(String powerServfeeOri) {
		this.powerServfeeOri = powerServfeeOri;
	}

	public String getPowerWay() {
		return this.powerWay;
	}

	public void setPowerWay(String powerWay) {
		this.powerWay = powerWay;
	}

	public String getPowerinfee() {
		return this.powerinfee;
	}

	public void setPowerinfee(String powerinfee) {
		this.powerinfee = powerinfee;
	}

	public String getPowerinfeeModel() {
		return this.powerinfeeModel;
	}

	public void setPowerinfeeModel(String powerinfeeModel) {
		this.powerinfeeModel = powerinfeeModel;
	}

	public String getPowerinfeeOri() {
		return this.powerinfeeOri;
	}

	public void setPowerinfeeOri(String powerinfeeOri) {
		this.powerinfeeOri = powerinfeeOri;
	}

	public String getPowerinfeeSharedisc() {
		return this.powerinfeeSharedisc;
	}

	public void setPowerinfeeSharedisc(String powerinfeeSharedisc) {
		this.powerinfeeSharedisc = powerinfeeSharedisc;
	}

	public String getPowerinfeeUnitprice() {
		return this.powerinfeeUnitprice;
	}

	public void setPowerinfeeUnitprice(String powerinfeeUnitprice) {
		this.powerinfeeUnitprice = powerinfeeUnitprice;
	}

	public String getProconStatus() {
		return this.proconStatus;
	}

	public void setProconStatus(String proconStatus) {
		this.proconStatus = proconStatus;
	}

	public String getProductConfig() {
		return this.productConfig;
	}

	public void setProductConfig(String productConfig) {
		this.productConfig = productConfig;
	}

	public String getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductunitNum() {
		return this.productunitNum;
	}

	public void setProductunitNum(String productunitNum) {
		this.productunitNum = productunitNum;
	}

	public String getPropertyAttribute() {
		return this.propertyAttribute;
	}

	public void setPropertyAttribute(String propertyAttribute) {
		this.propertyAttribute = propertyAttribute;
	}

	public String getProtocolNumber() {
		return this.protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public String getProvinceS() {
		return this.provinceS;
	}

	public void setProvinceS(String provinceS) {
		this.provinceS = provinceS;
	}

	public String getPtCost() {
		return this.ptCost;
	}

	public void setPtCost(String ptCost) {
		this.ptCost = ptCost;
	}

	public String getPtSharedisc() {
		return this.ptSharedisc;
	}

	public void setPtSharedisc(String ptSharedisc) {
		this.ptSharedisc = ptSharedisc;
	}

	public String getPtUnitprice() {
		return this.ptUnitprice;
	}

	public void setPtUnitprice(String ptUnitprice) {
		this.ptUnitprice = ptUnitprice;
	}

	public String getPtbuildOricost() {
		return this.ptbuildOricost;
	}

	public void setPtbuildOricost(String ptbuildOricost) {
		this.ptbuildOricost = ptbuildOricost;
	}

	public String getRealEndTime() {
		return this.realEndTime;
	}

	public void setRealEndTime(String realEndTime) {
		this.realEndTime = realEndTime;
	}

	public String getRegionS() {
		return this.regionS;
	}

	public void setRegionS(String regionS) {
		this.regionS = regionS;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemarkNew() {
		return this.remarkNew;
	}

	public void setRemarkNew(String remarkNew) {
		this.remarkNew = remarkNew;
	}

	public String getRoomBuildcost() {
		return this.roomBuildcost;
	}

	public void setRoomBuildcost(String roomBuildcost) {
		this.roomBuildcost = roomBuildcost;
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomSharedisc() {
		return this.roomSharedisc;
	}

	public void setRoomSharedisc(String roomSharedisc) {
		this.roomSharedisc = roomSharedisc;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoombuildOricost() {
		return this.roombuildOricost;
	}

	public void setRoombuildOricost(String roombuildOricost) {
		this.roombuildOricost = roombuildOricost;
	}

	public String getRoomcostUnitprice() {
		return this.roomcostUnitprice;
	}

	public void setRoomcostUnitprice(String roomcostUnitprice) {
		this.roomcostUnitprice = roomcostUnitprice;
	}

	public String getRruGuideprice() {
		return this.rruGuideprice;
	}

	public void setRruGuideprice(String rruGuideprice) {
		this.rruGuideprice = rruGuideprice;
	}

	public String getRruNum() {
		return this.rruNum;
	}

	public void setRruNum(String rruNum) {
		this.rruNum = rruNum;
	}

	public String getRruPowermode() {
		return this.rruPowermode;
	}

	public void setRruPowermode(String rruPowermode) {
		this.rruPowermode = rruPowermode;
	}

	public String getScene() {
		return this.scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getSeatsNum() {
		return this.seatsNum;
	}

	public void setSeatsNum(String seatsNum) {
		this.seatsNum = seatsNum;
	}

	public String getSerialid() {
		return this.serialid;
	}

	public void setSerialid(String serialid) {
		this.serialid = serialid;
	}

	public String getSiteSource() {
		return this.siteSource;
	}

	public void setSiteSource(String siteSource) {
		this.siteSource = siteSource;
	}

	public String getSitecode() {
		return this.sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getSumfee() {
		return this.sumfee;
	}

	public void setSumfee(String sumfee) {
		this.sumfee = sumfee;
	}

	public String getSysName1() {
		return this.sysName1;
	}

	public void setSysName1(String sysName1) {
		this.sysName1 = sysName1;
	}

	public String getSysName2() {
		return this.sysName2;
	}

	public void setSysName2(String sysName2) {
		this.sysName2 = sysName2;
	}

	public String getSysName3() {
		return this.sysName3;
	}

	public void setSysName3(String sysName3) {
		this.sysName3 = sysName3;
	}

	public String getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(String sysNum) {
		this.sysNum = sysNum;
	}

	public String getTbWyrentCwzc() {
		return this.tbWyrentCwzc;
	}

	public void setTbWyrentCwzc(String tbWyrentCwzc) {
		this.tbWyrentCwzc = tbWyrentCwzc;
	}

	public String getTbXzf() {
		return this.tbXzf;
	}

	public void setTbXzf(String tbXzf) {
		this.tbXzf = tbXzf;
	}

	public String getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTowerCostname() {
		return this.towerCostname;
	}

	public void setTowerCostname(String towerCostname) {
		this.towerCostname = towerCostname;
	}

	public String getTowerHigh() {
		return this.towerHigh;
	}

	public void setTowerHigh(String towerHigh) {
		this.towerHigh = towerHigh;
	}

	public String getTowerId() {
		return this.towerId;
	}

	public void setTowerId(String towerId) {
		this.towerId = towerId;
	}

	public String getTowerName() {
		return this.towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	public String getTowerSitename() {
		return this.towerSitename;
	}

	public void setTowerSitename(String towerSitename) {
		this.towerSitename = towerSitename;
	}

	public String getTowerType() {
		return this.towerType;
	}

	public void setTowerType(String towerType) {
		this.towerType = towerType;
	}

	public String getTwBuildcost() {
		return this.twBuildcost;
	}

	public void setTwBuildcost(String twBuildcost) {
		this.twBuildcost = twBuildcost;
	}

	public String getTwShareDisc() {
		return this.twShareDisc;
	}

	public void setTwShareDisc(String twShareDisc) {
		this.twShareDisc = twShareDisc;
	}

	public String getTwbuildOricost() {
		return this.twbuildOricost;
	}

	public void setTwbuildOricost(String twbuildOricost) {
		this.twbuildOricost = twbuildOricost;
	}

	public String getTwcostUnitprice() {
		return this.twcostUnitprice;
	}

	public void setTwcostUnitprice(String twcostUnitprice) {
		this.twcostUnitprice = twcostUnitprice;
	}

	public String getWhSharedisc() {
		return this.whSharedisc;
	}

	public void setWhSharedisc(String whSharedisc) {
		this.whSharedisc = whSharedisc;
	}

	public String getWhUnitprice() {
		return this.whUnitprice;
	}

	public void setWhUnitprice(String whUnitprice) {
		this.whUnitprice = whUnitprice;
	}

	public String getWhbuildOri() {
		return this.whbuildOri;
	}

	public void setWhbuildOri(String whbuildOri) {
		this.whbuildOri = whbuildOri;
	}

	public String getWlan() {
		return this.wlan;
	}

	public void setWlan(String wlan) {
		this.wlan = wlan;
	}

	public String getWlanOri() {
		return this.wlanOri;
	}

	public void setWlanOri(String wlanOri) {
		this.wlanOri = wlanOri;
	}

	public String getWlanantHeigh() {
		return this.wlanantHeigh;
	}

	public void setWlanantHeigh(String wlanantHeigh) {
		this.wlanantHeigh = wlanantHeigh;
	}

	public String getYearGuideprice() {
		return this.yearGuideprice;
	}

	public void setYearGuideprice(String yearGuideprice) {
		this.yearGuideprice = yearGuideprice;
	}

}
