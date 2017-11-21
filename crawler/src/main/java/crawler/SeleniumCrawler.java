package crawler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCrawler implements Runnable {

	public static void downloadPMS() {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		String downloadFilepath = "/home/cc/Downloads/Excels";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		WebDriver driver = new ChromeDriver(cap);
		String parentHandle = driver.getWindowHandle();
		System.out.println(parentHandle);
		// And now use this to visit Google
		driver.get("http://eip.chinatowercom.cn");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");

		// Find the text input element by its name
		WebElement elementUsername = driver.findElement(By.name("username"));
		elementUsername.sendKeys("chenchang3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("tietacc");
		elementPassword.submit();
		WebElement elementPMS = driver.findElement(By.linkText("项目管理"));
		elementPMS.click();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
		}

		// driver.switchTo().frame("mainframe");
		WebElement elementQuanShiTu = driver.findElement(By.id("maxButton"));
		elementQuanShiTu.click();

		driver.get("http://123.126.34.173:13456/pms/export/export/exportList.jsp");

		WebElement select = driver.findElement(By.id("typeCombo"));
		select.click();
		WebElement element = driver.findElement(By.id("mini-25$1"));
		element.click();

		element = driver.findElement(By.linkText("导出报表"));
		element.click();

		// driver.quit();
	}

	public static void downloadMonitor() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		String downloadFilepath = "/home/cc/Downloads/Excels";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		WebDriver driver = new ChromeDriver(cap);
		String parentHandle = driver.getWindowHandle();
		System.out.println(parentHandle);
		// And now use this to visit Google
		driver.get("http://eip.chinatowercom.cn");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");

		// Find the text input element by its name
		WebElement elementUsername = driver.findElement(By.name("username"));
		elementUsername.sendKeys("chenchang3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("tietacc");
		elementPassword.submit();
		WebElement elementPMS = driver.findElement(By.linkText("运维监控"));
		elementPMS.click();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
		}
		// Thread.sleep((long)5000);
		// WebElement element =
		// driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul[2]"));
		// element.click();//点击下拉菜单
		// element = driver.findElement(By.className("popover-sider"));
		// element =
		// element.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div[1]/ul/li[2]"));
		// Thread.sleep((long)1000); // for element not visible
		// element.click();
		// element =
		// driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div[2]/div[2]/div[2]/dl[1]/dd/a[1]"));
		// element.click();
		driver.get("http://180.153.49.216:9000/business/resMge/siteMge/listSite.xhtml");
		WebElement element = driver
				.findElement(By.xpath("//*[@id=\"queryForm\"]/center[1]/table[2]/tbody/tr[2]/td[1]/button"));
		element.click();
		// driver.switchTo().activeElement();
		element = driver.findElement(By.linkText("全不选"));
		element.click();
		element = driver.findElement(By.xpath("//*[@id=\"queryForm:j_id20\"]"));
		element.click();
		element = driver.findElement(By.xpath("//*[@id=\"j_id518\"]/center/input[1]"));
		element.click();

	}

	public static void downloadWuYe() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		String downloadFilepath = "/home/cc/Downloads/Excels";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		WebDriver driver = new ChromeDriver(cap);
		String parentHandle = driver.getWindowHandle();
		System.out.println(parentHandle);
		// And now use this to visit Google
		driver.get("http://eip.chinatowercom.cn");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");

		// Find the text input element by its name
		WebElement elementUsername = driver.findElement(By.name("username"));
		elementUsername.sendKeys("chenchang3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("tietacc");
		elementPassword.submit();

		WebElement elementPMS = driver.findElement(By.linkText("物业管理"));
		elementPMS.click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
		}

		driver.get("http://123.126.34.164:8880/default/baseinfo/property/QueryPropertyInfo.jsp");// 物业信息

		WebElement element = driver.findElement(By.linkText("查询"));
		element.click();

		element = driver.findElement(By.linkText("Excel导出"));
		element.click();

		driver.switchTo().activeElement();// 解决modal dialog

		Thread.sleep((long) 2000);

		element = driver.findElement(By.xpath("//*[@id=\"mini-59\"]"));
		element.click();
	}

	public static void downloadCRM() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		String downloadFilepath = "/home/cc/Downloads/Excels";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		WebDriver driver = new ChromeDriver(cap);
		String parentHandle = driver.getWindowHandle();
		System.out.println(parentHandle);
		// And now use this to visit Google
		driver.get("http://eip.chinatowercom.cn");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");

		// Find the text input element by its name
		WebElement elementUsername = driver.findElement(By.name("username"));
		elementUsername.sendKeys("chenchang3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("tietacc");
		elementPassword.submit();

		WebElement elementPMS = driver.findElement(By.linkText("CRM系统"));
		elementPMS.click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
		}

		driver.get("http://123.126.34.63:36080/default/main/skins/topbottom/home.jsp");

		WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div[1]/a[1]"));
		element.click();

		driver.switchTo().frame("cont_iframe");

		WebElement select = driver.findElement(By.id("provinceCode$text"));
		select.click();
		element = driver.findElement(By.id("mini-3$0"));
		element.click();

		select = driver.findElement(By.id("regionCode$text"));
		select.click();
		element = driver.findElement(By.id("mini-6$0"));
		element.click();

		element = driver.findElement(By.linkText("订单文件"));
		element.click();

	}

	public static void downloadAlarm() {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		String downloadFilepath = "/home/cc/Downloads/Excels";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		WebDriver driver = new ChromeDriver(cap);
		String parentHandle = driver.getWindowHandle();
		System.out.println(parentHandle);
		// And now use this to visit Google
		driver.get("http://eip.chinatowercom.cn");

		WebElement elementUsername = driver.findElement(By.name("username"));
		elementUsername.sendKeys("chenchang3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("tietacc");
		elementPassword.submit();
		WebElement elementPMS = driver.findElement(By.linkText("运维监控"));
		elementPMS.click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
		}

		driver.get("http://180.153.49.216:9000/business/resMge/alarmHisHbaseMge/listHisAlarmHbase.xhtml");

		WebElement element = driver.findElement(By.id("queryForm:firststarttimeInputDate"));
		element.click();
		element = driver.findElement(By.xpath("//*[@id=\"queryForm:firststarttimeHeader\"]/table/tbody/tr/td[2]/div"));
		element.click();

		WebElement dateWidget = driver.findElement(By.id("queryForm:firststarttime"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
		// comparing the text of cell with today's date and clicking it.

		Iterator<WebElement> iter = columns.iterator();
		while (iter.hasNext()) {
			int temp;
			WebElement cell = iter.next();
			try {
				temp = Integer.parseInt(cell.getText());
			} catch (NumberFormatException e) {
				System.out.println("parse " + cell.getText() + " to Integer failed");
				continue;
			}
			if (temp != 1)
				continue;
			do {
				int target = 1;
				if (temp == target) {
					cell.click();
					break;
				}
				if (iter.hasNext()) {
					cell = iter.next();
					temp = Integer.parseInt(cell.getText());
				} else
					break;
			} while (true);
			break;
		}
		element = driver.findElement(By.id("queryForm:firstendtimeInputDate"));
		element.click();
		element = driver.findElement(By.xpath("//*[@id=\"queryForm:firstendtimeHeader\"]/table/tbody/tr/td[2]/div"));
		element.click();

		element = driver.findElement(By.id("queryForm:firstendtimeInputDate"));
		dateWidget = driver.findElement(By.id("queryForm:firstendtime"));
		columns = dateWidget.findElements(By.tagName("td"));
		iter = columns.iterator();
		while (iter.hasNext()) {
			int temp;
			WebElement cell = iter.next();
			try {
				temp = Integer.parseInt(cell.getText());
			} catch (NumberFormatException e) {
				System.out.println("parse " + cell.getText() + " to Integer failed");
				continue;
			}
			if (temp != 1)
				continue;
			do {
				int target = 2;
				if (temp == target) {
					cell.click();
					break;
				}
				if (iter.hasNext()) {
					cell = iter.next();
					temp = Integer.parseInt(cell.getText());
				} else
					break;
			} while (true);
			break;
		}
		element = driver.findElement(By.id("queryForm:j_id46"));
		element.click();
		element = driver.findElement(By.id("j_id284:devExport"));
		element.click();
	}

	public static void downloadTTAlarm() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		String downloadFilepath = "/home/cc/Downloads/Excels/ActiveAlarms";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		WebDriver driver = new ChromeDriver(cap);
		driver.get("http://eip.chinatowercom.cn");
		oneStepAction(driver,"name,username","sendKeys,chenchang3");
		oneStepAction(driver,"name,password","sendKeys,tietacc");
		oneStepAction(driver,"name,password","submit,0");
		while(true){
			try{
				oneStepAction(driver,"linkText,运营支撑系统","click,0");
				driver.switchTo().frame("_48_INSTANCE_NZDjN9yNe2zB_iframe");
				oneStepAction(driver,"xpath,//*[@id=\"appModuleDiv\"]/ul/li[6]","click,0");
			}catch(NoSuchElementException e){
				continue;
			}
			break;
		}
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.get("http://180.153.49.216:9000/business/resMge/alarmMge/listAlarm.xhtml");
		oneStepAction(driver,"id,queryForm:alarmlevel:0","click,0");//level one
		oneStepAction(driver,"id,queryForm:alarmlevel:1","click,0");//level two
		oneStepAction(driver,"id,queryForm:btn1","click,0");// search
		Thread.sleep(5000);
		oneStepAction(driver,"id,queryForm:j_id157","click,0");// download
		oneStepAction(driver,"xpath,//*[@id=\"j_id1225\"]/center/input[1]","click,0"); // choose all
		
		downloadFilepath = "/home/cc/Downloads/Excels/FaultNumberState";
		chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		WebDriver driver1 = new ChromeDriver(cap);
		driver1.get("http://eip.chinatowercom.cn");
		oneStepAction(driver1,"name,username","sendKeys,chenchang3");
		oneStepAction(driver1,"name,password","sendKeys,tietacc");
		oneStepAction(driver1,"name,password","submit,0");
		while(true){
			try{
				oneStepAction(driver1,"linkText,运营支撑系统","click,0");
				driver1.switchTo().frame("_48_INSTANCE_NZDjN9yNe2zB_iframe");
				oneStepAction(driver1,"xpath,//*[@id=\"appModuleDiv\"]/ul/li[6]","click,0");
			}catch(NoSuchElementException e){
				continue;
			}
			break;
		}
		for (String winHandle : driver1.getWindowHandles()) {
			driver1.switchTo().window(winHandle);
		}
		driver1.get("http://180.153.49.216:9000/billDeal/monitoring/list/billList.xhtml");
		Thread.sleep((long) 10000);
		oneStepAction(driver1,"xpath,//*[@id=\"queryForm:j_id131\"]","click,0");
		oneStepAction(driver1,"xpath,//*[@id=\"j_id1566:devExport\"]","click,0");
		Thread.sleep((long) 10000);
		
		driver.quit();
		driver1.quit();
	}
	private static void myClickWait(WebDriverWait wait, By by) {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	private static void oneStepAction(WebDriver driver, String target, String action){
//		target : "xpath, /s/d/e/s/dfgf"
//		action : "click"
		WebDriverWait wait = new WebDriverWait(driver, 60);
		String targetTag = target.split(",")[0];
		String targetTagValue = target.split(",")[1];
		String actionName = action.split(",")[0];
		String actionValue = action.split(",")[1];
		
		By tmpBy = null;
		switch(targetTag){
			case "xpath": tmpBy = By.xpath(targetTagValue);
						  myClickWait(wait, tmpBy);break;
			case "name":  tmpBy = By.name(targetTagValue);
						  myClickWait(wait, tmpBy);break;
			case "id":    tmpBy = By.id(targetTagValue);
						  myClickWait(wait, tmpBy);break;
			case "linkText": 	tmpBy = By.linkText(targetTagValue);
								myClickWait(wait, tmpBy);break;
		}
		myClickWait(wait, tmpBy);
		WebElement element = driver.findElement(tmpBy);
		switch(actionName){
			case "click": 		element.click();break;
			case "submit":		element.submit();break;
			case "sendKeys":	element.sendKeys(actionValue);break;
		}
	}
	
	public void run() {
		try {
			downloadTTAlarm();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		Runnable runnable = new ExcelParser();
		Thread t1 = new Thread(runnable);
		t1.start();

//		while (true) {
//			Thread t2 = new Thread(new SeleniumCrawler());
//			t2.start();
//			t2.join();
//			Thread.sleep(60000);
//		}
	}
}
