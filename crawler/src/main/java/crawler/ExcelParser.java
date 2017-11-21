package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelParser implements Runnable {

	public static File lastFileModified(String dir) {
		File fl = new File(dir);
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		long lastMod = Long.MIN_VALUE;
		File choice = null;
		for (File file : files) {
			if (file.lastModified() > lastMod) {
				choice = file;
				lastMod = file.lastModified();
			}
		}
		File renamedChoice = new File(choice.getParent() + "/" + System.currentTimeMillis() + ".xls");
		choice.renameTo(renamedChoice);
		return renamedChoice;
	}

	private Map<String, String> getFaultNumberMapState(Map<String, String> map) {
		String DIR = "/home/cc/Downloads/Excels/FaultNumberState";
		File file = lastFileModified(DIR);
		String fileName = file.getPath();
		System.out.println("----------------" + fileName);
		InputStream inp;
		try {
			inp = new FileInputStream(fileName);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			int FaultNumber_Index = 0, State_Index = 1;
			boolean firstLine = true;
			for (Row row : sheet) {
				if (firstLine) {
					firstLine = false;
					continue;
				}
				String faultNumber = row.getCell(FaultNumber_Index).getStringCellValue();
				String state = row.getCell(State_Index).getStringCellValue();
				map.put(faultNumber, state);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		while (true) {
//			try {
//				Thread.sleep((long) 60000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			Map<String, String> faultNumberMapState = new HashMap<String, String>();
			faultNumberMapState = getFaultNumberMapState(faultNumberMapState);

			int FaultNumber_Index = 0, StaId_Index = 9, Alarm_Index = 10, AlarmStartTime_Index = 17,
					AlarmDuration_Index = 18;
			String activeAlarmsDIR = "/home/cc/Downloads/Excels/ActiveAlarms";
			Map<String, String> staIdMapAlarmStr = new HashMap<String, String>();
			Map<String, String> staIdMapFaultNumber = new HashMap<String, String>();
			Map<String, String> staIdMapAlarmStartTime = new HashMap<String, String>();
			Map<String, String> staIdMapAlarmDuration = new HashMap<String, String>();
			Map<String, Alarm> faultNumberMapAlarm = new HashMap<String, Alarm>();
			try {
				File file = lastFileModified(activeAlarmsDIR);

				String fileName = file.getPath();
				System.out.println("----------------" + fileName);
				InputStream inp = new FileInputStream(fileName);
				Workbook wb = new HSSFWorkbook(inp);
				Sheet sheet = wb.getSheetAt(0);
				boolean firstLine = true;
				for (Row row : sheet) {
					if (firstLine) {
						firstLine = false;
						continue;
					}
					String staId = row.getCell(StaId_Index).getStringCellValue();
					String alarm = row.getCell(Alarm_Index).getStringCellValue();
					String faultNumber = row.getCell(FaultNumber_Index).getStringCellValue();
					String alarmStartTime = row.getCell(AlarmStartTime_Index).getStringCellValue();
					String alarmDuration = row.getCell(AlarmDuration_Index).getNumericCellValue() + "";
					if (staIdMapAlarmStr.containsKey(staId)) {
						String mergedAlarm = staIdMapAlarmStr.get(staId) + ";" + alarm;
						String mergedFaultNumber = staIdMapFaultNumber.get(staId) + ";" + faultNumber;
						String mergedAlarmStartTime = staIdMapAlarmStartTime.get(staId) + ";" + alarmStartTime;
						String mergedAlarmDuration = staIdMapAlarmDuration.get(staId) + ";" + alarmDuration;

						staIdMapAlarmStr.put(staId, mergedAlarm);
						staIdMapFaultNumber.put(staId, mergedFaultNumber);
						staIdMapAlarmStartTime.put(staId, mergedAlarmStartTime);
						staIdMapAlarmDuration.put(staId, mergedAlarmDuration);
					} else {
						staIdMapAlarmStr.put(staId, alarm);
						staIdMapFaultNumber.put(staId, faultNumber);
						staIdMapAlarmStartTime.put(staId, alarmStartTime);
						staIdMapAlarmDuration.put(staId, alarmDuration);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			EntityManagerFactory factory = Persistence.createEntityManagerFactory("crawler");
			EntityManager em = factory.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Iterator<String> keyIter = staIdMapAlarmStr.keySet().iterator();
			while (keyIter.hasNext()) {
				Alarm alarm = new Alarm();
				String staId = keyIter.next();
				alarm.setStaId(staId);
				String alarmStr = staIdMapAlarmStr.get(staId);
				String faultNumber = staIdMapFaultNumber.get(staId);
				String alarmStartTime = staIdMapAlarmStartTime.get(staId);
				String alarmDuration = staIdMapAlarmDuration.get(staId);
				if (alarmStr.equals("温度超高")) {
					// continue;
				}
				alarm.setAlarm(alarmStr);
				alarm.setFaultNumber(faultNumber);
				alarm.setStartTime(alarmStartTime);
				alarm.setDuration(alarmDuration);

				String[] faultNumberArray = faultNumber.split(";");
				for (String tempFaultNumber : faultNumberArray) {
					if (faultNumberMapState.containsKey(tempFaultNumber)) {
						alarm.setState(faultNumberMapState.get(tempFaultNumber));
						break;
					}
				}
				faultNumberMapAlarm.put(faultNumber, alarm);
				em.persist(alarm);
				System.out.println(staId + "   " + alarmStr);
			}
			tx.commit();
			em.close();
			factory.close();
//		}
	}


	private Map<String, String> getBigGridLeaderMap(String filePath){
		int BigGridLeader_Index = 6, GridLeader_Index = 2, StaId_Index = 5;
		File file = new File(filePath);
		String fileName = file.getPath();
		InputStream inp;
		Map<String, String> staIdMapBigGridLeader = new HashMap<String, String>();
		try {
			inp = new FileInputStream(fileName);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			boolean firstLine = true;
			for (Row row : sheet) {
				if (firstLine) {
					firstLine = false;
					continue;
				}
				String staId = row.getCell(StaId_Index).getStringCellValue();
				String bigGridLeader = row.getCell(BigGridLeader_Index).getStringCellValue();
				staIdMapBigGridLeader.put(staId, bigGridLeader);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return staIdMapBigGridLeader;
	}
	
	private Map<String, String> getGridLeaderMap(String filePath){
		int BigGridLeader_Index = 6, GridLeader_Index = 2, StaId_Index = 5;
		
		File file = new File(filePath);
		String fileName = file.getPath();
		InputStream inp;
		Map<String, String> staIdMapGridLeader = new HashMap<String, String>();
		try {
			inp = new FileInputStream(fileName);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			boolean firstLine = true;
			for (Row row : sheet) {
				if (firstLine) {
					firstLine = false;
					continue;
				}
				String staId = row.getCell(StaId_Index).getStringCellValue();
				String gridLeader = row.getCell(GridLeader_Index).getStringCellValue();
				staIdMapGridLeader.put(staId, gridLeader);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return staIdMapGridLeader;
	}

	public static void main(String[] args) {
		Runnable runnable = new ExcelParser();
		Thread t1 = new Thread(runnable);
		t1.start();
	}
}
