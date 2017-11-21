package commons;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import crawler.SeleniumCrawler;
import extractor.GenerateCRMOrderInfo;
import extractor.SettleAccountOrderExtractor;

public class Go implements Runnable{

	@Override
	public void run() {
		try {
			SeleniumCrawler.downloadCRM();
			SeleniumCrawler.downloadWuYe();
			SeleniumCrawler.downloadPMS();
			SeleniumCrawler.downloadCRMSettleAccountOrder();
			GenerateCRMOrderInfo.run();
			SettleAccountOrderExtractor ss = new SettleAccountOrderExtractor();
			ss.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws InterruptedException{
		while(true){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			Calendar calendar=Calendar.getInstance();
			long currentMillisTime = calendar.getTimeInMillis();
			calendar.add(Calendar.MINUTE, 30);
			int currentYear = calendar.get(Calendar.YEAR);
			int currentMonth = calendar.get(calendar.MONDAY);
			int currentDay = calendar.get(calendar.DAY_OF_MONTH);
			int currentHour = calendar.get(calendar.HOUR_OF_DAY);
			int currentMinite = calendar.get(calendar.MINUTE);
			calendar.set(currentYear, currentMonth, currentDay, currentHour, currentMinite);
			long targetMillisTime = calendar.getTimeInMillis();
			System.out.println(" "+ currentYear + " " +currentMonth+ " "+ currentDay + " " + currentHour + " "+ currentMinite);
			System.out.println("current: " + sdf.format(currentMillisTime) + " target: " + sdf.format(targetMillisTime) + " delta:" + (targetMillisTime-currentMillisTime));
			System.out.println("current: " + currentMillisTime + " target: " + targetMillisTime + " delta:" + (targetMillisTime-currentMillisTime));
			
			long initialDelay = targetMillisTime - currentMillisTime;
			Thread.sleep(initialDelay);
			
			Go go = new Go();
			Thread t = new Thread(go);
			t.start();
			
			Thread.sleep(60*1000);
		}
	}
}
