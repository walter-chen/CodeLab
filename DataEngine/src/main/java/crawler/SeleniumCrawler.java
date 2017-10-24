package crawler;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import extractor.CRMExcelExtractor;

public class SeleniumCrawler {

	public static void downloadPMS() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		String downloadFilepath = "/home/cc/Downloads/Excels/PMS/"+dateFormat.format(now);
		File dir = new File(downloadFilepath);
		if(dir.exists()){
			for(File f : dir.listFiles())
				f.delete();
			dir.delete();
		}
		dir.mkdir();
		
		
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
		elementUsername.sendKeys("yubj3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("1q2w3e..");
		elementPassword.submit();
		WebElement elementPMS = driver.findElement(By.linkText("项目管理"));
		elementPMS.click();
		Thread.sleep(10000);
		try {
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(2000);
		int cityAmount = 9;
		for (int i = 0; i < cityAmount; i++) {
			driver.get("http://pms.pc.chinatowercom.cn:13456/pms/export/export/exportList.jsp");
			Thread.sleep(2000);
			WebElement element = driver.findElement(By.xpath("//*[@id=\"typeCombo$text\"]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"mini-25$1\"]/td[2]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"beginPrjYear$text\"]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"mini-49$3\"]/td[2]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"province$text\"]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"mini-33$13\"]/td[2]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"city$text\"]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"mini-37$" + (i + 1) + "\"]/td[2]"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.linkText("查询"));
			element.click();
			Thread.sleep(10000);
			element = driver.findElement(By.linkText("导出报表"));
			element.click();
			Thread.sleep(1000);
		}
		Thread.sleep(120000);
		driver.quit();
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
		elementUsername.sendKeys("yubj3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("1q2w3e..");
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
		String downloadFilepath = "/home/cc/Downloads/Excels/Property/RentCard";
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
		elementUsername.sendKeys("yubj3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("1q2w3e..");
		elementPassword.submit();

		WebElement elementPMS = driver.findElement(By.linkText("物业管理"));
		elementPMS.click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
		}

		driver.get("http://pcms.chinatowercom.cn:8880/default/rent/rentAccrued/updateCardInfo.jsp");// 物业信息

		WebElement element = driver.findElement(By.linkText("查询"));
		element.click();
		Thread.sleep((long) 2000);
		element = driver.findElement(By.linkText("EXCEL导出"));
		element.click();

		Thread.sleep((long) 120000);
		driver.quit();
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
		elementUsername.sendKeys("yubj3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("1q2w3e..");
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
		elementUsername.sendKeys("yubj3");

		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("1q2w3e..");
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
		Runnable runnable = new CRMExcelExtractor();
		Thread t1 = new Thread(runnable);
		t1.start();
		while (true) {
			String downloadFilepath = "/home/cc/Downloads/Excels/ActiveAlarms";
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
			elementUsername.sendKeys("yubj3");

			WebElement elementPassword = driver.findElement(By.name("password"));
			elementPassword.sendKeys("1q2w3e..");
			elementPassword.submit();
			Thread.sleep(3000);
			WebElement elementPMS = driver.findElement(By.linkText("运维监控"));
			elementPMS.click();
			Thread.sleep(3000);
			// driver.get("http://180.153.49.130:9000/layout/index.xhtml#");
			// Thread.sleep(20000);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of
														// WebDriver to
														// the next found window
														// handle (that's your
														// newly
														// opened window)
			}

			driver.get("http://180.153.49.216:9000/business/resMge/alarmMge/listAlarm.xhtml");
			Thread.sleep((long) 10000);
			WebElement element = driver.findElement(By.id("queryForm:alarmlevel:0"));
			element.click();
			Thread.sleep((long) 5000);
			element = driver.findElement(By.id("queryForm:alarmlevel:1"));
			element.click();
			Thread.sleep((long) 5000);
			element = driver.findElement(By.id("queryForm:btn1"));
			element.click();
			Thread.sleep((long) 5000);
			element = driver.findElement(By.id("queryForm:j_id157"));
			element.click();
			Thread.sleep((long) 5000);
			element = driver.findElement(By.xpath("//*[@id=\"j_id1225\"]/center/input[1]"));
			element.click();
			Thread.sleep((long) 5000);

			downloadFilepath = "/home/cc/Downloads/Excels/FaultNumberState";
			chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);

			// Create a new instance of the Firefox driver
			// Notice that the remainder of the code relies on the interface,
			// not the implementation.
			WebDriver driver1 = new ChromeDriver(cap);
			parentHandle = driver1.getWindowHandle();
			System.out.println(parentHandle);
			// And now use this to visit Google
			driver1.get("http://eip.chinatowercom.cn");

			elementUsername = driver1.findElement(By.name("username"));
			elementUsername.sendKeys("yubj3");

			elementPassword = driver1.findElement(By.name("password"));
			elementPassword.sendKeys("1q2w3e..");
			elementPassword.submit();
			elementPMS = driver1.findElement(By.linkText("运维监控"));
			elementPMS.click();
			// driver.get("http://180.153.49.130:9000/layout/index.xhtml#");
			// Thread.sleep(20000);
			for (String winHandle : driver1.getWindowHandles()) {
				driver1.switchTo().window(winHandle); // switch focus of
														// WebDriver to
														// the next found window
														// handle (that's your
														// newly
														// opened window)
			}
			driver1.get("http://180.153.49.216:9000/billDeal/monitoring/list/billList.xhtml");
			Thread.sleep((long) 10000);
			element = driver1.findElement(By.xpath("//*[@id=\"queryForm:j_id131\"]"));
			element.click();
			element = driver1.findElement(By.xpath("//*[@id=\"j_id1566:devExport\"]"));
			element.click();
			Thread.sleep((long) 60000);
			driver.quit();
			driver1.quit();
		}

	}

	public static void downloadContract() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/cc/Downloads/chromedriver");
		// Runnable runnable = new ExcelParser();
		// Thread t1 = new Thread(runnable);
		// t1.start();
		while (true) {
			String downloadFilepath = "/home/cc/Downloads/Excels/Contracts";
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
			driver.get("http://eip.chinatowercom.cn");
			WebElement elementUsername = driver.findElement(By.name("username"));
			elementUsername.sendKeys("yubj3");

			WebElement elementPassword = driver.findElement(By.name("password"));
			elementPassword.sendKeys("1q2w3e..");
			elementPassword.submit();
			Thread.sleep(3000);
			WebElement elementPMS = driver.findElement(By.linkText("合同管理"));
			elementPMS.click();
			Thread.sleep(3000);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of
														// WebDriver to
														// the next found window
														// handle (that's your
														// newly
														// opened window)
			}
			driver.get("http://cms.chinatowercom.cn/default//contract/siteContract/querySiteContract.jsp");
			Thread.sleep((long) 5000);
			WebElement element = driver.findElement(By.linkText("查询"));
			element.click();
			Thread.sleep((long) 5000);
			element = driver.findElement(By.linkText("导出Excel"));
			element.click();
			Thread.sleep((long) 3000);
			Alert alt = driver.switchTo().alert();
			alt.accept();
			Thread.sleep((long) 30000);
			driver.quit();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		downloadPMS();
		// downloadMonitor();
		// downloadWuYe();
		// downloadCRM();
		// downloadAlarm();
		// downloadTTAlarm();
		// downloadContract();
	}
}
