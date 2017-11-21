package domain;

public class Station {
	private static final long serialVersionUID = 1234442357674354353L;
	private String name;
    private String id;
    private String staId;
    private String latitude;
    private String longitude;
    
    private String alarm;
    private String state;
    private String leaderName;
    private String bigLeaderName;
    private String startTime;
    private String duration;
    

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getStaId() {
		return staId;
	}
	public void setStaId(String staId) {
		this.staId = staId;
	}
    public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
    public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
    public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
    public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
    public String getBigLeaderName() {
		return bigLeaderName;
	}
	public void setBigLeaderName(String bigLeaderName) {
		this.bigLeaderName = bigLeaderName;
	}
}

