package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected String staId;
	protected String longitude;
	protected String latitude;
	protected String staName;
	protected String gridLeader;
	protected String bigGridLeader;
	
	public String getStaId() {
		return staId;
	}
	public void setStaId(String staId) {
		this.staId = staId;
	}
	
	public String getLongtitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getStaName() {
		return staName;
	}
	public void setStaName(String staName) {
		this.staName = staName;
	}
	public String getGridLeader() {
		return gridLeader;
	}
	public void setGridLeader(String gridLeader) {
		this.gridLeader = gridLeader;
	}
	public String getBigGridLeader() {
		return bigGridLeader;
	}
	public void setBigGridLeader(String bigGridLeader) {
		this.bigGridLeader = bigGridLeader;
	}
}
