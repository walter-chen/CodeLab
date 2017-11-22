package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VIEW_STAFF_ROLE implements Serializable{

	private static final long serialVersionUID = 449750057903189798L;
	
	@Id
	private String ACCOUNT;
	private String STAFF_NAME;
	private String ORG_CODE;
	private String ROLE_ID;
	private String ROLE_NAME;
	private String FUNC_ID;
	private String FUNC_NAME;
	private String CONTROLLERNAME;
	private String ACTIONNAME;


	public String getACTIONNAME() {
		return ACTIONNAME;
	}

	public void setACTIONNAME(String ACTIONNAME) {
		this.ACTIONNAME = ACTIONNAME;
	}

	public String getCONTROLLERNAME() {
		return CONTROLLERNAME;
	}

	public void setCONTROLLERNAME(String CONTROLLERNAME) {
		this.CONTROLLERNAME = CONTROLLERNAME;
	}

	public String getFUNC_NAME() {
		return FUNC_NAME;
	}

	public void setFUNC_NAME(String FUNC_NAME) {
		this.FUNC_NAME = FUNC_NAME;
	}

	public String getFUNC_ID() {
		return FUNC_ID;
	}

	public void setFUNC_ID(String FUNC_ID) {
		this.FUNC_ID = FUNC_ID;
	}

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	public void setROLE_NAME(String ROLE_NAME) {
		this.ROLE_NAME = ROLE_NAME;
	}

	public String getROLE_ID() {
		return ROLE_ID;
	}

	public void setROLE_ID(String ROLE_ID) {
		this.ROLE_ID = ROLE_ID;
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
