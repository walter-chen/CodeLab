package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import commons.PathCatalog;
import domain.OrderInfoShortcut;
import domain.ReportOrderInfo;
import pojo.Gps;
import pojo.User;
import producer.NeighborSeeker;
import utility.DatabaseQueryResult;
import utility.Formula;
import utility.MD5Base64Encrypt;
import utility.PositionUtil;

@Controller
public class ControllerFJSupportSystem {
	List<OrderInfoShortcut> candidates = DatabaseQueryResult.getDatabaseQueryResult("select * from REPORT_ORDER_INFO i WHERE i.ORDER_STATUS='已起租'");
	 static {
	//	  1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		 Factory<org.apache.shiro.mgt.SecurityManager> factory = new
		 IniSecurityManagerFactory("classpath:shiro.ini");
	//	  2、得到SecurityManager实例 并绑定给SecurityUtils
		 org.apache.shiro.mgt.SecurityManager securityManager =
		 factory.getInstance();
		 SecurityUtils.setSecurityManager(securityManager);
//		 candidates = DatabaseQueryResult.getDatabaseQueryResult("select * from REPORT_ORDER_INFO i WHERE i.ORDER_STATUS='已起租'");
	 }
	private static final Log logger = LogFactory.getLog(ControllerFJSupportSystem.class);
	EntityManagerFactory oracleOutsideFactory = Persistence.createEntityManagerFactory("OracleHibernateLab-Inside");
	
	@RequestMapping(value = "/getSearchResultJSON")
	public void getSearchResultJSON(HttpServletRequest request, HttpServletResponse response) {

		String bdLngLat = request.getParameter("query");
		System.out.println("ao---" + bdLngLat);
		OrderInfoShortcut center = new OrderInfoShortcut();
		String ggLngLat = Formula.BdToGcj(Double.parseDouble(bdLngLat.split(",")[0]),
				Double.parseDouble(bdLngLat.split(",")[1]));
		Gps gps = PositionUtil.bd09_To_Gps84(Double.parseDouble(bdLngLat.split(",")[1]),
				Double.parseDouble(bdLngLat.split(",")[0]));
		center.setLONGITUDE(gps.getWgLon() + "");
		center.setLATITUDE(gps.getWgLat() + "");
		
		JSONObject jsonObject = NeighborSeeker.seekNeighbors(center, candidates, "500");

		// construction site START
		Iterator<String> keyIter = jsonObject.keys();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			JSONObject tempJSONObject = (JSONObject) jsonObject.get(key);
			String tempLongtitude = tempJSONObject.getString("经度");
			String tempLatitude = tempJSONObject.getString("纬度");
			Gps tempGps = PositionUtil.Gps84_To_bd09(Double.parseDouble(tempLatitude),
					Double.parseDouble(tempLongtitude));
			tempJSONObject.put("经度", tempGps.getWgLon() + "");
			tempJSONObject.put("纬度", tempGps.getWgLat() + "");
		}
		// construction site END

		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getAjaxInfo")
	public String getAjaxInfo(Model model) {
		model.addAttribute("user", "Joe Dirt");
		model.addAttribute("time", new Date());
		return "SubView";
	}

	@RequestMapping(value = "/siteSelection")
	public String getSiteSelection(Model model) {

		return "SiteSelection";
	}
	@RequestMapping(value = "/siteSelectionMap")
	public String getSiteSelectionMap(Model model) {

		return "SiteSelectionMap";
	}

	@RequestMapping(value = "/index")
	public String index(Model model) {

		return "Index";
	}
	@RequestMapping(value = "/loginPage")
	public String loginPage(Model model) {

		return "LoginPage";
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
			return "LoginPage";
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

		return "Index";
	}

	@RequestMapping(value = "/download")
	public String download(HttpServletRequest request, HttpServletResponse response) {
		Path file = Paths.get("/home/cc/Downloads/Excels/Property", "1508401298144.xls");
		if (Files.exists(file)) {
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=secret.xls");
			ServletOutputStream out;
			try {
				out = response.getOutputStream();
				Files.copy(file, out);
				out.close();
			} catch (IOException ex) {
			}
		}
		return null;
	}

	@RequestMapping(value = "/orderInfoTable")
	public String orderInfoTable(HttpServletRequest request, HttpServletResponse response) {
		return "OrderInfoTable";
	}

	@RequestMapping(value = "/downloadOrderInfoTable")
	public String downloadOrderInfoTable(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String selectedCity = request.getParameter("selectedCity");
		String datetimepicker = request.getParameter("datetimepicker");
		datetimepicker = datetimepicker.split("-")[0] + datetimepicker.split("-")[1] + datetimepicker.split("-")[2];
		Path file = Paths.get(PathCatalog.orderInfoMarketOutputPath + "/" + datetimepicker, selectedCity + ".xls");
		String encodedSelectedCity = URLEncoder.encode(selectedCity, "utf-8");// 防止下载文件名乱码
		if (Files.exists(file)) {
			response.setContentType("application/json;charset=UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition",
					"attachment; filename=" + encodedSelectedCity + datetimepicker + ".xls");
			ServletOutputStream out;
			try {
				out = response.getOutputStream();
				Files.copy(file, out);
				out.close();
			} catch (IOException ex) {
			}
		}
		return null;
	}

	@RequestMapping(value = "/settleAccountOrder")
	public String settleAccountOrder(HttpServletRequest request, HttpServletResponse response) {
		return "SettleAccountOrder";
	}

	@RequestMapping(value = "/downloadSettleAccountOrder")
	public String downloadSettleAccountOrder(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String selectedCity = request.getParameter("selectedCity");
		String datetimepicker = request.getParameter("datetimepicker");
		datetimepicker = datetimepicker.split("-")[0] + datetimepicker.split("-")[1] + datetimepicker.split("-")[2];
		Path file = Paths.get(PathCatalog.settleAccountOutputPath + "/" + datetimepicker, selectedCity + ".xls");
		String encodedSelectedCity = URLEncoder.encode(selectedCity, "utf-8");// 防止下载文件名乱码
		if (Files.exists(file)) {
			response.setContentType("application/json;charset=UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition",
					"attachment; filename=" + encodedSelectedCity + datetimepicker + ".xls");
			ServletOutputStream out;
			try {
				out = response.getOutputStream();
				Files.copy(file, out);
				out.close();
			} catch (IOException ex) {
			}
		}
		return null;
	}

}
