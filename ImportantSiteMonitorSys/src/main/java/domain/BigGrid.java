package domain;

import java.util.List;

public class BigGrid {
	private	String bigGridLeader;
	private Long alarmAmount;
	private List<Grid> grids;
	
	public String getBigGridLeader() {
		return bigGridLeader;
	}
	public void setBigGridLeader(String bigGridLeader) {
		this.bigGridLeader = bigGridLeader;
	}
	
	public Long getAlarmAmount() {
		return alarmAmount;
	}
	public void setAlarmAmount(Long alarmAmount) {
		this.alarmAmount = alarmAmount;
	}
	
	public List<Grid> getGrids() {
		return grids;
	}
	public void setGrids(List<Grid> grids) {
		this.grids = grids;
	}
}
