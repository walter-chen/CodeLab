package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PLATFORM_STAFF implements Serializable{

	private static final long serialVersionUID = 449750057903189798L;
	
	@Id
	private String ID; 
	private String ACCOUNT; 
	private String STAFF_NAME;
	private String ORG_CODE;
	private String WX_APP;
	private String PASSWORD;
	private String CREATE_DATE;
	private String STATE;

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String STATE) {
		this.STATE = STATE;
	}
	
	public String getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(String CREATE_DATE) {
		this.CREATE_DATE = CREATE_DATE;
	}
	
	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}
	
	public String getWX_APP() {
		return WX_APP;
	}

	public void setWX_APP(String WX_APP) {
		this.WX_APP = WX_APP;
	}
	
	public String getORG_CODE() {
		return ORG_CODE;
	}

	public void setORG_CODE(String ORG_CODE) {
		this.ORG_CODE = ORG_CODE;
	}
	
	public String getSTAFF_NAME() {
		return STAFF_NAME;
	}

	public void setSTAFF_NAME(String STAFF_NAME) {
		this.STAFF_NAME = STAFF_NAME;
	}
	
	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String ACCOUNT) {
		this.ACCOUNT = ACCOUNT;
	}
	
	
}
