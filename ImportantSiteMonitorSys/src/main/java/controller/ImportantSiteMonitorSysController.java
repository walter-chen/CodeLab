package controller;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Alarm;
import domain.BigGrid;
import domain.Grid;
import domain.Position;
import domain.Station;
import pojo.Gps;
import pojo.User;
import utility.MD5Base64Encrypt;
import utility.MyInfoMapper;
import utility.PositionUtil;

@Controller
public class ImportantSiteMonitorSysController {

	static {
		Factory<org.apache.shiro.mgt.SecurityManager> shiroFactory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = shiroFactory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		MyInfoMapper myInfoMapper = new MyInfoMapper();
	}
	EntityManagerFactory oracleOutsideFactory = Persistence.createEntityManagerFactory("OracleHibernateLab-Inside");
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA");
	private static final Log logger = LogFactory.getLog(ImportantSiteMonitorSysController.class);

	@RequestMapping(value = "/map")
	public String showMap(Model model, HttpServletRequest request) throws UnknownHostException {
		
		User user;
		try{
			user = (User) request.getSession().getAttribute("user");
			if(user.orgCode.contains("35000"))
				user.city = "%";
			else if(MyInfoMapper.codeMapCity.get(user.orgCode.substring(0, 6)) != null){
				user.city = MyInfoMapper.codeMapCity.get(user.orgCode.substring(0, 6));
			}
		}catch(NullPointerException e){
			return "Index";
		}
		String searchFaultUrl = "http://180.153.49.216:9000/billDeal/monitoring/detail/monBillDetail.xhtml?isSearchHis=N&billIdParam=&oriTabId=[object%20HTMLTableCellElement]&billSn=";

		String sql = "select i from Alarm i";
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery(sql);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Alarm> alarms = query.getResultList();
		tx.commit();
		em.close();
		Iterator<Alarm> iter = alarms.iterator();
		Map<String, Alarm> alarmMap = new HashMap<String, Alarm>();
		while (iter.hasNext()) {
			Alarm tempA = iter.next();
			alarmMap.put(tempA.getStaId(), tempA);
		}

		sql="select i from Position i Where i.bigGridLeader like '%"+ user.city + "%'";
		em = factory.createEntityManager();
		query = em.createQuery(sql);
		tx = em.getTransaction();
		tx.begin();
		List<Position> positions = query.getResultList();
		tx.commit();
		em.close();
		Iterator<Position> iterPosi = positions.iterator();
		Map<String, Position> positionMap = new HashMap<String, Position>();
		while (iterPosi.hasNext()) {
			Position tempP = iterPosi.next();
			positionMap.put(tempP.getStaId(), tempP);
		}

		List<Station> stations = new LinkedList<Station>();
		Iterator<String> iterKey = positionMap.keySet().iterator();
		while (iterKey.hasNext()) {
			String staId = iterKey.next();
			Position tempPosition = positionMap.get(staId);

			Station tempStation = new Station();
			tempStation.setName(tempPosition.getStaName());
			tempStation.setId(staId);
			Gps tempGps = PositionUtil.Gps84_To_bd09(Double.parseDouble(tempPosition.getLatitude()),
					Double.parseDouble(tempPosition.getLongtitude()));
			tempStation.setLatitude(tempGps.getWgLat() + "");
			tempStation.setLongitude(tempGps.getWgLon() + "");
			tempStation.setStaId(staId);
			tempStation.setLeaderName(tempPosition.getGridLeader().replaceAll("\n", ""));

			if (alarmMap.containsKey(staId)) {
				Alarm tempAlarm = alarmMap.get(staId);
				String[] alarmsArray = tempAlarm.getAlarm().split(";");
				String[] faultsArray = tempAlarm.getFaultNumber().split(";");
				String taggedAlarm = "";
				try {
					for (int i = 0; i < alarmsArray.length; i++) {
						taggedAlarm = taggedAlarm + "<a href=" + searchFaultUrl + faultsArray[i] + ">" + alarmsArray[i]
								+ "</a>;";
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(tempAlarm.getAlarm());
					System.out.println(tempAlarm.getFaultNumber());
				}
				tempStation.setAlarm(taggedAlarm);
				tempStation.setState(tempAlarm.getState());

			} else {
				tempStation.setAlarm("");
			}
			stations.add(tempStation);
		}
		
		List<String> citiesStr = new LinkedList<String>();
		if(user.city.equals("%")){
			String[] cities = "福州市,厦门市,泉州市,漳州市,宁德市,莆田市,南平市,三明市,龙岩市".split(",");
			for(String city : cities)
				citiesStr.add(city);
			model.addAttribute("citiesStr",  citiesStr);
		}else{
			citiesStr.add(user.city+"市");
			model.addAttribute("citiesStr",  citiesStr);
		}
		
		Gps centerGps = MyInfoMapper.cityMapCenterGps.get(user.city);
		model.addAttribute("centerGps", centerGps);
		model.addAttribute("zoomDegree", MyInfoMapper.cityMapZoomDegree.get(user.city));
		model.addAttribute("stations", stations);
		return "BaiduMap";
	}

	@RequestMapping(value = "/statistic")
	public String showStatistic(Model model, HttpServletRequest request) throws UnknownHostException {
		User user;
		try{
			user = (User) request.getSession().getAttribute("user");
			System.out.println(user.city);
			if(user.orgCode.contains("35000"))
				user.city = "%";
			else if(MyInfoMapper.codeMapCity.get(user.orgCode.substring(0, 6)) != null){
				user.city = MyInfoMapper.codeMapCity.get(user.orgCode.substring(0, 6));
			}
		}catch(NullPointerException e){
			return "Index";
		}
		
		String searchFaultUrl = "http://180.153.49.216:9000/billDeal/monitoring/detail/monBillDetail.xhtml?isSearchHis=N&billIdParam=&oriTabId=[object%20HTMLTableCellElement]&billSn=";
		
		String sql = "select i from Alarm i";
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery(sql);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Alarm> alarms = query.getResultList();
		tx.commit();
		em.close();
		Iterator<Alarm> iter2 = alarms.iterator();
		Map<String, Alarm> alarmMap = new HashMap<String, Alarm>();
		while (iter2.hasNext()) {
			Alarm tempA = iter2.next();
			alarmMap.put(tempA.getStaId(), tempA);
		}

		sql = "select i from Position i Where i.bigGridLeader like '%"+ user.city + "%'";
		em = factory.createEntityManager();
		query = em.createQuery(sql);
		tx = em.getTransaction();
		tx.begin();
		List<Position> positions = query.getResultList();
		tx.commit();
		em.close();
		
		Iterator<Position> iterPosi = positions.iterator();
		Map<String, Position> positionMap = new HashMap<String, Position>();
		while (iterPosi.hasNext()) {
			Position tempP = iterPosi.next();
			positionMap.put(tempP.getStaId(), tempP);
		}

		List<Station> stations = new LinkedList<Station>();
		Map<String, Integer> alarmAmountMap = new HashMap<String, Integer>();
		Iterator<String> iterKey = positionMap.keySet().iterator();
		while (iterKey.hasNext()) {
			String staId = iterKey.next();
			Position tempPosition = positionMap.get(staId);

			Station tempStation = new Station();
			tempStation.setName(tempPosition.getStaName());
			tempStation.setId(staId);
			tempStation.setLatitude(tempPosition.getLatitude());
			tempStation.setLongitude(tempPosition.getLongtitude());
			tempStation.setStaId(staId);
			tempStation.setLeaderName(tempPosition.getGridLeader());
			tempStation.setBigLeaderName(tempPosition.getBigGridLeader());

			if (alarmMap.containsKey(staId)) {
				Alarm tempAlarm = alarmMap.get(staId);
				String[] alarmsArray = tempAlarm.getAlarm().split(";");
				String[] faultsArray = tempAlarm.getFaultNumber().split(";");
				String taggedAlarm = "";
				try {
					for (int i = 0; i < alarmsArray.length; i++) {
						taggedAlarm = taggedAlarm + "<a href=" + searchFaultUrl + faultsArray[i] + ">" + alarmsArray[i]
								+ "</a>;";
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(tempAlarm.getAlarm());
					System.out.println(tempAlarm.getFaultNumber());
				}
				tempStation.setAlarm(taggedAlarm);
				tempStation.setDuration(tempAlarm.getDuration());
				tempStation.setStartTime(tempAlarm.getStartTime());
				tempStation.setState(tempAlarm.getState());
				stations.add(tempStation);
				if (!alarmAmountMap.containsKey(tempAlarm.getAlarm())) {

				}
			} else {
				tempStation.setAlarm("");
			}
		}

		Map<String, String> alarmNameMapAmount = new HashMap<String, String>();
		Iterator<Station> iterSta = stations.iterator();
		while (iterSta.hasNext()) {
			Station tempStation = iterSta.next();
			String[] alarmNameArray = tempStation.getAlarm().split(";");
			for (String tempAlarmName : alarmNameArray) {
				if (!alarmNameMapAmount.containsKey(tempAlarmName))
					alarmNameMapAmount.put(tempAlarmName, "1");
				else {
					String oldAmount = alarmNameMapAmount.get(tempAlarmName);
					alarmNameMapAmount.put(tempAlarmName, "" + (Integer.parseInt(oldAmount) + 1));
				}

			}
		}

		int alarmAmount = 0;
		Iterator<String> iterAlarm = alarmNameMapAmount.keySet().iterator();
		Map<String, String> alarmNameMapRatio = new HashMap<String, String>();
		while (iterAlarm.hasNext()) {
			String tempAlarmName = iterAlarm.next();
			alarmAmount += Integer.parseInt(alarmNameMapAmount.get(tempAlarmName));
		}
		iterAlarm = alarmNameMapAmount.keySet().iterator();
		while (iterAlarm.hasNext()) {
			String tempAlarmName = iterAlarm.next();
			double ratio = Integer.parseInt(alarmNameMapAmount.get(tempAlarmName)) / (alarmAmount * 1.0);
			alarmNameMapRatio.put(tempAlarmName, "" + ratio);
		}

		model.addAttribute("alarmNameMapAmount", alarmNameMapAmount);
		model.addAttribute("alarmNameMapRatio", alarmNameMapRatio);
		model.addAttribute("stations", stations);

		return "Statistic";
	}

	@RequestMapping(value = "/GridState")
	public String showGridState(Model model) throws UnknownHostException {
		String searchFaultUrl = "http://180.153.49.216:9000/billDeal/monitoring/detail/monBillDetail.xhtml?isSearchHis=N&billIdParam=&oriTabId=[object%20HTMLTableCellElement]&billSn=";
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA");
		EntityManager em = factory.createEntityManager();

		Query query = em.createQuery(
				"select c.bigGridLeader, count(c) from CumulativeAlarm c group by c.bigGridLeader having c.bigGridLeader is not null");
		List<Object[]> bigGridStationAmounts = query.getResultList();

		query = em.createQuery(
				"select c.gridLeader, count(c) from CumulativeAlarm c group by c.gridLeader having c.gridLeader is not null");
		List<Object[]> gridStationAmounts = query.getResultList();

		query = em.createQuery("select c.gridLeader, c.bigGridLeader from CumulativeAlarm c");
		List<Object[]> bigAndGridLeader = query.getResultList();

		em.clear();
		em.close();
		factory.close();

		Map<String, String> gridLeaderMapBigGridLeader = new HashMap<String, String>();
		for (Object[] o : bigAndGridLeader) {
			if (o[0] == null || o[1] == null)
				continue;
			String gridLeader = ((String) o[0]).replaceAll("\n", "");
			String bigGridLeader = ((String) o[1]).replaceAll("\n", "");
			gridLeaderMapBigGridLeader.put(gridLeader, bigGridLeader);
		}

		Map<String, Long> bigGridLeaderMapAlarmAmount = new HashMap<String, Long>();
		for (Object[] o : bigGridStationAmounts) {
			String str = ((String) o[0]).replaceAll("\n", "");
			Long count = (Long) o[1];
			bigGridLeaderMapAlarmAmount.put((String) o[0], (Long) o[1]);
			System.out.println("bigGrid: " + str + "\t" + count);
		}

		Map<String, List<Grid>> bigLeaderMapGrids = new HashMap<String, List<Grid>>();
		for (Object[] o : gridStationAmounts) {
			String leader = ((String) o[0]).replaceAll("\n", "");
			Long count = (Long) o[1];

			Grid grid = new Grid();
			grid.setAlarmAmount(count);
			grid.setGridLeader(leader);

			String bigLeader = gridLeaderMapBigGridLeader.get(leader);
			if (bigLeaderMapGrids.containsKey(bigLeader)) {
				bigLeaderMapGrids.get(bigLeader).add(grid);
			} else {
				List<Grid> l = new LinkedList<Grid>();
				l.add(grid);
				bigLeaderMapGrids.put(bigLeader, l);
			}
			System.out.println("grid: " + leader + "\t" + count);
		}

		Iterator<String> iter = bigLeaderMapGrids.keySet().iterator();
		List<BigGrid> bigGridList = new LinkedList<BigGrid>();
		while (iter.hasNext()) {
			String bigGridLeader = iter.next();
			System.out.println(bigGridLeader);
			Long amount = bigGridLeaderMapAlarmAmount.get(bigGridLeader);
			List<Grid> grids = bigLeaderMapGrids.get(bigGridLeader);
			BigGrid bg = new BigGrid();
			bg.setAlarmAmount(amount);
			bg.setBigGridLeader(bigGridLeader);
			bg.setGrids(grids);
			bigGridList.add(bg);
		}

		model.addAttribute("bigGridList", bigGridList);

		return "GridState";
	}

	@RequestMapping(value = "/index")
	public String index(Model model) {

		return "Index";
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Base64Encrypt.encrypt(password);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			// 4、登录，即身份验证
			subject.login(token);
			System.out.println("login successful");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			System.out.println(username + " " + password);
			return "Index";
		}

		EntityManager em = oracleOutsideFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String queryStr = "select i.ORG_CODE from PLATFORM_STAFF i Where i.ACCOUNT = '" + username + "'";
		System.out.println(queryStr);
		Query query = em.createNativeQuery(queryStr);
		String orgCode = (String) query.getSingleResult();
		tx.commit();
		em.close();

		User user = new User(username, "", orgCode);
		request.getSession().setAttribute("user", user);

		return "LoginPage";
	}

}
