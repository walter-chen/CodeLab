package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class CumulativeAlarm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String staId;
	private String faultNumber;
	private String bigGridLeader;
	private String gridLeader;
	
	
	public String getStaId() {
		return staId;
	}
	public void setStaId(String staId) {
		this.staId = staId;
	}
	
	public String getFaultNumber() {
		return faultNumber;
	}
	public void setFaultNumber(String faultNumber) {
		this.faultNumber = faultNumber;
	}
	
	public String getBigGridLeader() {
		return bigGridLeader;
	}
	public void setBigGridLeader(String bigGridLeader) {
		this.bigGridLeader = bigGridLeader;
	}
	
	public String getGridLeader() {
		return gridLeader;
	}
	public void setGridLeader(String gridLeader) {
		this.gridLeader = gridLeader;
	}
	
}
