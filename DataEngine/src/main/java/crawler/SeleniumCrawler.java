package crawler;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.PathCatalog;
import extractor.CRMExcelExtractor;
import extractor.GenerateCRMOrderInfo;
import extractor.SettleAccountOrderExtractor;

public class SeleniumCrawler {
	/**
	 * @throws InterruptedException
	 */
	public static double getDirSize(File file) {
		// 判断文件是否存在
		if (file.exists()) {
			// 如果是目录则递归计算其内容的总大小
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				double size = 0;
				for (File f : children)
					size += getDirSize(f);
				return size;
			} else {// 如果是文件则直接返回其大小,以“KB”为单位
				double size = (double) file.length() / 1024;
				return size;
			}
		} else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
			return 0.0;
		}
	}

	public static void downloadPMS() throws InterruptedException {
		while (true) {
			System.setProperty("webdriver.chrome.driver", PathCatalog.chromeDriverPath);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

			String downloadFilepath = PathCatalog.pMSExcelPath + "/" + dateFormat.format(now);
			File dir = new File(downloadFilepath);
			if (dir.exists()) {
				for (File f : dir.listFiles())
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
			WebDriverWait wait = new WebDriverWait(driver, 60);

			String parentHandle = driver.getWindowHandle();
			System.out.println(parentHandle);
			// And now use this to visit Google
			driver.get("http://eip.chinatowercom.cn");
			// Alternatively the same thing can be done like this
			// driver.navigate().to("http://www.google.com");

			// Find the text input element by its name
			myClickWait(wait, By.name("username"));
			WebElement elementUsername = driver.findElement(By.name("username"));
			elementUsername.sendKeys("yubj3");

			myClickWait(wait, By.name("password"));
			WebElement elementPassword = driver.findElement(By.name("password"));
			elementPassword.sendKeys("1q2w3e..");
			elementPassword.submit();

			while(true){
				try{
					By tmpBy = By.linkText("运营支撑系统");
					myClickWait(wait, tmpBy);
					WebElement element = driver.findElement(tmpBy);
					element.click();
			
					driver.switchTo().frame("_48_INSTANCE_NZDjN9yNe2zB_iframe");
					tmpBy = By.xpath("//*[@id=\"appModuleDiv\"]/ul/li[2]");
					myClickWait(wait, tmpBy);
					element = driver.findElement(tmpBy);
					element.click();
				}catch(NoSuchElementException e){
					continue;
				}
				break;
			}

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
				while (true) {
					try {
						driver.get("http://pms.pc.chinatowercom.cn:13456/pms/export/export/exportList.jsp");
						Thread.sleep(2000);

						By tmpBy = By.xpath("//*[@id=\"typeCombo$text\"]");
						myClickWait(wait, tmpBy);
						WebElement element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.xpath("//*[@id=\"mini-25$1\"]/td[2]");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.xpath("//*[@id=\"beginPrjYear$text\"]");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.xpath("//*[@id=\"mini-49$3\"]/td[2]");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.xpath("//*[@id=\"province$text\"]");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.xpath("//*[@id=\"mini-33$13\"]/td[2]");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.xpath("//*[@id=\"city$text\"]");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.xpath("//*[@id=\"mini-37$" + (i + 1) + "\"]/td[2]");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						tmpBy = By.linkText("查询");
						myClickWait(wait, tmpBy);
						element = driver.findElement(tmpBy);
						element.click();

						Thread.sleep(2000);
						wait.until(new ExpectedCondition<Boolean>() {
							public Boolean apply(WebDriver driver) {
								WebElement element = driver
										.findElement(By.xpath("//*[@id=\"panel2\"]/div/div[2]/div[2]"));
								return !element.getText().contains("Loading");
							}
						});

						element = driver.findElement(By.linkText("导出报表"));
						element.click();
						Thread.sleep(1000);
					} catch (TimeoutException e) {
						System.out.println("bad luck timeout retry this city");
						continue;
					}
					break;
				}
			}
			boolean success = true;
			int preDirSize = 0;
			int latestDirSize = 0;
			int sleepTime = 60000;
			while (true) {
				Thread.sleep(sleepTime);
				dir = new File(downloadFilepath);
				latestDirSize = (int) getDirSize(dir);

				if (latestDirSize > preDirSize) {
					if((latestDirSize - preDirSize) < (sleepTime*20/1000)) {
						System.out.println("PMS loading speed is too slow -_-||| retry");
						break;
					}
					preDirSize = latestDirSize;
					System.out.println("PMS loading " + latestDirSize + "KB");
				} else
					break;
			}
			if (dir.listFiles().length < 9)
				success = false;
			for (File f : dir.listFiles()) {
				if (f.getName().contains(".crdownload"))
					success = false;
			}
			if (success) {
				System.out.println("PMS download successfull ^_^");
				driver.quit();
				break;
			} else {
				System.out.println("PMS download failed");
			}
		}
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
		while (true) {
			System.setProperty("webdriver.chrome.driver", PathCatalog.chromeDriverPath);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String downloadFilepath = PathCatalog.propertyRentCard + "/" + dateFormat.format(now);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);

			WebDriver driver = new ChromeDriver(cap);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.get("http://eip.chinatowercom.cn");

			myClickWait(wait, By.name("username"));
			WebElement elementUsername = driver.findElement(By.name("username"));
			elementUsername.sendKeys("yubj3");

			myClickWait(wait, By.name("password"));
			WebElement elementPassword = driver.findElement(By.name("password"));
			elementPassword.sendKeys("1q2w3e..");
			elementPassword.submit();
			
			while(true){
				try{
					By tmpBy = By.linkText("运营支撑系统");
					myClickWait(wait, tmpBy);
					WebElement element = driver.findElement(tmpBy);
					element.click();
					
					driver.switchTo().frame("_48_INSTANCE_NZDjN9yNe2zB_iframe");
					tmpBy = By.xpath("//*[@id=\"appModuleDiv\"]/ul/li[6]");
					myClickWait(wait, tmpBy);
					element = driver.findElement(tmpBy);
					element.click();
				}catch(NoSuchElementException e){
					continue;
				}
				break;
			}
			
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of
														// WebDriver to
														// the next found window
														// handle (that's your
														// newly
														// opened window)
			}

			driver.get("http://pcms.chinatowercom.cn:8880/default/rent/rentAccrued/updateCardInfo.jsp");// 物业信息

			myClickWait(wait, By.linkText("查询"));
			WebElement element = driver.findElement(By.linkText("查询"));
			element.click();

			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					WebElement element = driver.findElement(By.xpath("//*[@id=\"center\"]"));
					return !element.getText().contains("Loading");
				}
			});
			element = driver.findElement(By.linkText("EXCEL导出"));
			element.click();

			boolean success = true;
			int preDirSize = 0;
			int latestDirSize = 0;
			File dir = new File(downloadFilepath);
			int sleepTime = 20000;
			while (true) {
				Thread.sleep(sleepTime);
				dir = new File(downloadFilepath);
				latestDirSize = (int) getDirSize(dir);

				if (latestDirSize > preDirSize) {
					if((latestDirSize - preDirSize) < (sleepTime*20/1000)) {
						System.out.println("WuYe loading speed is too slow -_-||| retry");
						break;
					}
					preDirSize = latestDirSize;
					System.out.println("WuYe loading " + latestDirSize + "KB");
				} else
					break;
			}
			if (dir.listFiles().length < 1)
				success = false;
			for (File f : dir.listFiles()) {
				if (f.getName().contains(".crdownload"))
					success = false;
			}
			if (success) {
				System.out.println("WuYe rentCard download successfull ^_^");
				driver.quit();
				break;
			} else {
				System.out.println("WuYe rentCard download failed");
			}
		}
	}

	public static void downloadCRM() throws InterruptedException {
		while (true) {
			System.setProperty("webdriver.chrome.driver", PathCatalog.chromeDriverPath);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String downloadFilepath = PathCatalog.cRMExcelPath + "/" + dateFormat.format(now);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);

			WebDriver driver = new ChromeDriver(cap);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.get("http://eip.chinatowercom.cn");
			
			By tmpBy = null;
			tmpBy = By.name("username");
			myClickWait(wait, tmpBy);
			WebElement elementUsername = driver.findElement(tmpBy);
			elementUsername.sendKeys("yubj3");
			
			tmpBy = By.name("password");
			myClickWait(wait, tmpBy);
			WebElement elementPassword = driver.findElement(tmpBy);
			elementPassword.sendKeys("1q2w3e..");
			elementPassword.submit();
			while(true){
				try{
					tmpBy = By.linkText("运营支撑系统");
					myClickWait(wait, tmpBy);
					WebElement element = driver.findElement(tmpBy);
					element.click();
			
					driver.switchTo().frame("_48_INSTANCE_NZDjN9yNe2zB_iframe");
					tmpBy = By.xpath("//*[@id=\"appModuleDiv\"]/ul/li[1]");
					myClickWait(wait, tmpBy);
					element = driver.findElement(tmpBy);
					element.click();
				}catch(NoSuchElementException e){
					continue;
				}
				break;
			}
			Thread.sleep(5000);
			
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of
														// WebDriver to
														// the next found window
														// handle (that's your
														// newly
														// opened window)
			}

			driver.get("http://crm.chinatowercom.cn:36080/default/sale/queryRequest/QureyRequest.jsp");

			myClickWait(wait, By.linkText("订单文件"));
			WebElement element = driver.findElement(By.linkText("订单文件"));
			element.click();

			boolean success = true;
			int preDirSize = 0;
			int latestDirSize = 0;
			File dir = new File(downloadFilepath);
			int sleepTime = 20000;
			while (true) {
				Thread.sleep(sleepTime);
				dir = new File(downloadFilepath);
				latestDirSize = (int) getDirSize(dir);

				if (latestDirSize > preDirSize) {
					if((latestDirSize - preDirSize) < (sleepTime*20/1000)) {
						System.out.println("CRM loading speed is too slow -_-||| retry");
						break;
					}
					preDirSize = latestDirSize;
					System.out.println("CRM loading " + latestDirSize + "KB");
				} else
					break;
			}
			if (dir.listFiles().length < 1)
				success = false;
			for (File f : dir.listFiles()) {
				if (f.getName().contains(".crdownload"))
					success = false;
			}
			if (success) {
				System.out.println("CRM download successfull ^_^");
				driver.quit();
				break;
			} else {
				System.out.println("CRM download failed");
			}
		}
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
		String downloadFilepath = "/home/cc/Downloads/Excels/Contracts";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriver driver = new ChromeDriver(cap);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.get("http://eip.chinatowercom.cn");

		myClickWait(wait, By.name("username"));
		WebElement elementUsername = driver.findElement(By.name("username"));
		elementUsername.sendKeys("yubj3");

		myClickWait(wait, By.name("password"));
		WebElement elementPassword = driver.findElement(By.name("password"));
		elementPassword.sendKeys("1q2w3e..");
		elementPassword.submit();

		myClickWait(wait, By.linkText("合同管理"));
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

		myClickWait(wait, By.linkText("查询"));
		WebElement element = driver.findElement(By.linkText("查询"));
		element.click();

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath("/html/body/div[2]"));
				return !element.getText().contains("Loading");
			}
		});

		myClickWait(wait, By.linkText("导出Excel"));
		element = driver.findElement(By.linkText("导出Excel"));
		element.click();
		Thread.sleep((long) 3000);
		Alert alt = driver.switchTo().alert();
		alt.accept();
		Thread.sleep((long) 60000);
		driver.quit();
	}

	public static void downloadCRMSettleAccountOrder() throws InterruptedException {
		while (true) {
			System.setProperty("webdriver.chrome.driver", PathCatalog.chromeDriverPath);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String downloadFilepath = PathCatalog.settleAccountOrderPath+"/" + dateFormat.format(now);
			File dir = new File(downloadFilepath);
			if (dir.exists()) {
				for (File f : dir.listFiles())
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

			WebDriver driver = new ChromeDriver(cap);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.get("http://eip.chinatowercom.cn");
			myClickWait(wait, By.name("username"));
			WebElement elementUsername = driver.findElement(By.name("username"));
			elementUsername.sendKeys("yubj3");

			myClickWait(wait, By.name("password"));
			WebElement elementPassword = driver.findElement(By.name("password"));
			elementPassword.sendKeys("1q2w3e..");
			elementPassword.submit();
			while(true){
				try{
				By tmpBy = By.linkText("运营支撑系统");
				myClickWait(wait, tmpBy);
				WebElement element = driver.findElement(tmpBy);
				element.click();
			
				driver.switchTo().frame("_48_INSTANCE_NZDjN9yNe2zB_iframe");
				tmpBy = By.xpath("//*[@id=\"appModuleDiv\"]/ul/li[1]");
				myClickWait(wait, tmpBy);
				element = driver.findElement(tmpBy);
				element.click();
				}catch(NoSuchElementException e){
					continue;
				}
				break;
			}
			Thread.sleep(2000);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of
														// WebDriver to
														// the next found window
														// handle (that's your
														// newly
														// opened window)
			}
			Thread.sleep(2000);
			driver.get("http://crm.chinatowercom.cn:36080/default/billing/acctquery/downloadFee.jsp");
			Thread.sleep(2000);
			By tmpBy = By.xpath("//*[@id=\"provinceCode$text\"]");
			myClickWait(wait, tmpBy);
			WebElement element = driver.findElement(tmpBy);
			element.click();
			
			tmpBy = By.xpath("//*[@id=\"mini-3$0\"]/td[2]");
			myClickWait(wait, tmpBy);
			element = driver.findElement(tmpBy);
			element.click();

			tmpBy = By.xpath("//*[@id=\"billType$text\"]");
			myClickWait(wait, tmpBy);
			element = driver.findElement(tmpBy);
			element.click();
			
			tmpBy = By.xpath("//*[@id=\"mini-9$0\"]/td[2]");
			myClickWait(wait, tmpBy);
			element = driver.findElement(tmpBy);
			element.click();

			tmpBy = By.xpath("//*[@id=\"text_cycleMonth$text\"]");
			myClickWait(wait, tmpBy);
			element = driver.findElement(tmpBy);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			dateFormat = new SimpleDateFormat("yyyy-MM");
			element.clear();
			element.sendKeys(dateFormat.format(cal.getTime()));

			tmpBy = By.xpath("//*[@id=\"searchBtn\"]");
			myClickWait(wait, tmpBy);
			element = driver.findElement(tmpBy);
			element.click();
			Thread.sleep(2000);
			
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					WebElement element = driver
							.findElement(By.xpath("//*[@id=\"datagrid1\"]"));
					return !element.getText().contains("Loading");
				}
			});
			
			tmpBy = By.tagName("a");
			List<WebElement> elements = driver.findElements(tmpBy);
			for(WebElement ele : elements){
				if(ele.getAttribute("href").contains("35000001.xlsx")){
					ele.click();
					break;
				}
			}

			boolean success = true;
			int preDirSize = 0;
			int latestDirSize = 0;
			int sleepTime = 30000;
			while (true) {
				Thread.sleep(sleepTime);
				dir = new File(downloadFilepath);
				latestDirSize = (int) getDirSize(dir);
				
				System.out.println("latestDirSize: " + latestDirSize + " " + dir.getAbsolutePath());
				if (latestDirSize > preDirSize) {
//					if((latestDirSize - preDirSize) < (sleepTime*20/1000)) {
//						System.out.println("CRMSettleAccountOrder loading speed is too slow -_-||| retry");
//						break;
//					}
					preDirSize = latestDirSize;
					System.out.println("CRMSettleAccountOrder loading " + latestDirSize + "KB");
				} else
					break;
			}
			if (dir.listFiles().length < 1)
				success = false;
			for (File f : dir.listFiles()) {
				if (f.getName().contains(".crdownload"))
					success = false;
			}
			if (success) {
				System.out.println("CRMSettleAccountOrder download successfull ^_^");
				driver.quit();
				break;
			} else {
				System.out.println("CRMSettleAccountOrder download failed");
			}
		}
	}

	private static void myClickWait(WebDriverWait wait, By by) {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public static void main(String[] args) throws InterruptedException, IOException {

//		downloadCRM();
//		downloadWuYe();
//		downloadPMS();
		downloadCRMSettleAccountOrder();
		
		GenerateCRMOrderInfo.run();
		SettleAccountOrderExtractor ss = new SettleAccountOrderExtractor();
		ss.run();
		// downloadMonitor();
		// downloadAlarm();
		// downloadTTAlarm();
		// downloadContract();
	}
}
