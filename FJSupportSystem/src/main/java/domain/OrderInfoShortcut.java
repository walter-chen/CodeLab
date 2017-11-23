package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
public class OrderInfoShortcut implements Serializable {
	private static final long serialVersionUID = 898437626374221L;
	
	@Id
	private String ORDERID;
	private String OPERATOR;
	private String CITY_S;
	private String LATITUDE;
	private String LONGITUDE;
	private String SUMFEE;
	private String PRODUCT_CONFIG;
	private String ROOM_TYPE;
	private String SITECODE;
	private String DEMAND_SITENAME;
	private String TOWER_HIGH;
	private String TOWER_TYPE;

	public void setTOWER_TYPE(String TOWER_TYPE) {
		this.TOWER_TYPE = TOWER_TYPE;
	}

	public String getTOWER_TYPE() {
		return this.TOWER_TYPE;
	}
	
	public void setTOWER_HIGH(String TOWER_HIGH) {
		this.TOWER_HIGH = TOWER_HIGH;
	}

	public String getTOWER_HIGH() {
		return this.TOWER_HIGH;
	}
	
	public void setDEMAND_SITENAME(String DEMAND_SITENAME) {
		this.DEMAND_SITENAME = DEMAND_SITENAME;
	}

	public String getDEMAND_SITENAME() {
		return this.DEMAND_SITENAME;
	}
	
	public void setSITECODE(String SITECODE) {
		this.SITECODE = SITECODE;
	}

	public String getSITECODE() {
		return this.SITECODE;
	}
	
	public void setROOM_TYPE(String ROOM_TYPE) {
		this.ROOM_TYPE = ROOM_TYPE;
	}

	public String getROOM_TYPE() {
		return this.ROOM_TYPE;
	}
	
	public void setPRODUCT_CONFIG(String PRODUCT_CONFIG) {
		this.PRODUCT_CONFIG = PRODUCT_CONFIG;
	}

	public String getPRODUCT_CONFIG() {
		return this.PRODUCT_CONFIG;
	}
	
	public void setSUMFEE(String SUMFEE) {
		this.SUMFEE = SUMFEE;
	}

	public String getSUMFEE() {
		return this.SUMFEE;
	}
	
	public void setLONGITUDE(String LONGITUDE) {
		this.LONGITUDE = LONGITUDE;
	}

	public String getLONGITUDE() {
		return this.LONGITUDE;
	}
	
	public void setLATITUDE(String LATITUDE) {
		this.LATITUDE = LATITUDE;
	}

	public String getLATITUDE() {
		return this.LATITUDE;
	}
	
	public void setCITY_S(String CITY_S) {
		this.CITY_S = CITY_S;
	}

	public String getCITY_S() {
		return this.CITY_S;
	}
	
	public void setOPERATOR(String OPERATOR) {
		this.OPERATOR = OPERATOR;
	}

	public String getOPERATOR() {
		return this.OPERATOR;
	}
	
	public void setORDERID(String ORDERID) {
		this.ORDERID = ORDERID;
	}

	public String getORDERID() {
		return this.ORDERID;
	}


}
