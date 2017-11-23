package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StringPair {

	private static final long serialVersionUID = 449750057903189798L;
	
	@Id
	private String CITY_NAME;
	private String ORG_NAME;
	
	public String getORG_NAME() {
		return ORG_NAME;
	}

	public void setORG_NAME(String ORG_NAME) {
		this.ORG_NAME = ORG_NAME;
	}
	
	public String getCITY_NAME() {
		return CITY_NAME;
	}

	public void setCITY_NAME(String CITY_NAME) {
		this.CITY_NAME = CITY_NAME;
	}
}
