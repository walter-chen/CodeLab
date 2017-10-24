package pojo;

public class CRMDataMarketRecord {
	public String orderIdStr;	//订单号
	public String stationIdStr; //站址编码
	public String stationNameStr; //站址名称
	public String sourceStr;	// 产权属性   注入、自建
	public String ownerStr;	// 原产权方   电信
	public String districtStr;	// 区县
	
	public String firstClientStr;	//首家
	public String orderTypeStr; // 新建
    public String productConfigurationStr;	//产品配置
    public String towerTypeStr;	//塔型
    public String roomTypeStr;	//配套类型
    public String towerHeightStr;	//塔高
    public String hangHeightUnitOneStr;	//挂高
    public String hangHeightUnitTwoStr;	//挂高
    public String hangHeightUnitThreeStr;//挂高
    public String antennaAmountOneStr;	//天线数
    public String antennaAmountTwoStr;	//天线数
    public String antennaAmountThreeStr;	//天线数
    public String systemAmountOneStr;	//系统数
    public String systemAmountTwoStr;	//系统数
    public String systemAmountThreeStr;	//系统数
    public String rentDateStr;	//起租时间
    
    public String cityStr;
    public String clientStr;
    public String priceStr;
    public String longitudeStr;
    public String latitudeStr;
    
    public CRMSiteRelatedStatistic cRMSiteRelatedStatistic;
    public PropertyRentCardSiteRelatedStatistic propertyRentCardSiteRelatedStatistic;
    public PMSPowerCableRecord pMSPowerCabelRecord;

    
    public CRMDataMarketRecord(){
    	this.cRMSiteRelatedStatistic = new CRMSiteRelatedStatistic();
    	this.propertyRentCardSiteRelatedStatistic = new PropertyRentCardSiteRelatedStatistic();
    	this.pMSPowerCabelRecord = new PMSPowerCableRecord();
    }
}
