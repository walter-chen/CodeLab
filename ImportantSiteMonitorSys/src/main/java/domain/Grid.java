package domain;

public class Grid {
	private	String gridLeader;
	private Long alarmAmount;
	
	public Long getAlarmAmount() {
		return alarmAmount;
	}
	public void setAlarmAmount(Long alarmAmount) {
		this.alarmAmount = alarmAmount;
	}
	public String getGridLeader() {
		return gridLeader;
	}
	public void setGridLeader(String gridLeader) {
		this.gridLeader = gridLeader;
	}
}
