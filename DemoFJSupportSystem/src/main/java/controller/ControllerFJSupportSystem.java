package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import domain.CRM;
import pojo.Gps;
import producer.NeighborSeeker;
import utility.DatabaseQueryResult;
import utility.Formula;
import utility.PositionUtil;

@Controller
public class ControllerFJSupportSystem {
	static List<CRM> candidates;
	static {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		candidates = DatabaseQueryResult.getDatabaseQueryResult("CRM");
	}
	private static final Log logger = LogFactory.getLog(ControllerFJSupportSystem.class);

	@RequestMapping(value = "/testAjax")
	public String testAjax(Model model) {

		return "TestAjax";
	}

	@RequestMapping(value = "/testAjaxPost")
	public String testAjaxPost(Model model) {

		return "TestAjaxPost";
	}

	@RequestMapping(value = "/getSearchResultJSON")
	public void getSearchResultJSON(HttpServletRequest request, HttpServletResponse response) {
		
		String bdLngLat = request.getParameter("query");
		System.out.println("ao---" + bdLngLat);
		CRM center = new CRM();
		String ggLngLat = Formula.BdToGcj(Double.parseDouble(bdLngLat.split(",")[0]), 
					    Double.parseDouble(bdLngLat.split(",")[1]));
		
		Gps gps = PositionUtil.bd09_To_Gps84(Double.parseDouble(bdLngLat.split(",")[1]), 
				Double.parseDouble(bdLngLat.split(",")[0]));
		
		center.setLongitude(gps.getWgLon()+"");
		center.setLatitude(gps.getWgLat()+"");
//		List<CRM> candidates = DatabaseQueryResult.getDatabaseQueryResult("CRM");
		JSONObject jsonObject = NeighborSeeker.seekNeighbors(center, candidates, "500");

// construction site   START		
		Iterator<String> keyIter = jsonObject.keys();
		while(keyIter.hasNext()){
			String key = keyIter.next();
			JSONObject tempJSONObject = (JSONObject) jsonObject.get(key);
			String tempLongtitude = tempJSONObject.getString("经度");
			String tempLatitude = tempJSONObject.getString("纬度");
			Gps tempGps = PositionUtil.Gps84_To_bd09(Double.parseDouble(tempLatitude), Double.parseDouble(tempLongtitude));
			tempJSONObject.put("经度", tempGps.getWgLon()+"");
			tempJSONObject.put("纬度", tempGps.getWgLat()+"");
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
	public String getSiteInfo(Model model) {

		return "SiteSelection";
	}
	
	@RequestMapping(value = "/index")
	public String index(Model model) {

		return "Index";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
		try {  
	        //4、登录，即身份验证  
	        subject.login(token);  
	        System.out.println("login successful");
	    } catch (AuthenticationException e) {  
	        //5、身份验证失败
	    	return "Index";
	    }  
		return "LoginPage";
	}
	
}
