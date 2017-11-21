package extractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;

import commons.CRMExcelIndexCatalog;
import commons.FileNameCatalog;
import commons.PathCatalog;
import domain.CRM_Income_Reference;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import pojo.CRMDataMarketRecord;
import pojo.CRMSiteRelatedStatistic;

public class CRMExcelExtractor implements Runnable{
	public Map<String, CRMDataMarketRecord> orderIdMapCRMDataMarketRecord = new HashMap<String, CRMDataMarketRecord>();
	public Map<String, CRMSiteRelatedStatistic> staIdMapCRMSiteRelatedStatistic = new HashMap<String, CRMSiteRelatedStatistic>();
	private int howManyTimes = 0;
	
	public static void print(String msg){
		System.out.println(msg);
	}
	
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
		if(choice == null) throw new NullPointerException();
		File renamedChoice = new File(choice.getParent() + "/" + System.currentTimeMillis() + ".zip");
		choice.renameTo(renamedChoice);
		return renamedChoice;
	}
	public void run() {
print("find latest zip");
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String source = lastFileModified(PathCatalog.cRMExcelPath+"/"+dateFormat.format(now)).getAbsolutePath();  // find latest zip 
		String destination = PathCatalog.cRMExcelPath+"/"+dateFormat.format(now)+"/"+System.nanoTime(); // extract zip to a nanoTime named directory
		File dir = new File(destination);
		dir.mkdir();
		try {
			ZipFile zipFile = new ZipFile(source);
			zipFile.extractAll(destination);
print("extract zip");			
		} catch (ZipException e) {
			e.printStackTrace();
		}
		String[] fileNames = FileNameCatalog.cRMExtractedFileNamesFromZip;
		for(String fileName: fileNames){
			String excelPathStr = destination+"/"+fileName;
			try {
				InputStream is = new FileInputStream(new File(excelPathStr));
				Workbook workbook = StreamingReader.builder()
				        .rowCacheSize(10000)    // number of rows to keep in memory (defaults to 10)
				        .bufferSize(40960000)     // buffer size to use when reading InputStream to file (defaults to 1024)
				        .open(is);            // InputStream or File for XLSX file (required)
				Sheet sheet = workbook.getSheetAt(0);
print("cumulate " + excelPathStr);
				cumulateRecordToMap(sheet);
				System.out.println(excelPathStr + " " + howManyTimes);
				workbook.close();
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
print("howManyTimes: " + howManyTimes);
	}
	private void cumulateRecordToMap(Sheet sheet){
		int tileLineAmount = 4;
		for (Row row : sheet) {
			if (tileLineAmount-- > 0) {
				continue;
			}
			howManyTimes++;
			CRMDataMarketRecord record = new CRMDataMarketRecord();
			
			record.antennaAmountOneStr = f(row.getCell(CRMExcelIndexCatalog.antennaAmountOneIndex));
			record.antennaAmountThreeStr = f(row.getCell(CRMExcelIndexCatalog.antennaAmountThreeIndex));
			record.antennaAmountTwoStr = f(row.getCell(CRMExcelIndexCatalog.antennaAmountTwoIndex));
			record.cityStr = f(row.getCell(CRMExcelIndexCatalog.cityIndex));
			record.clientStr = f(row.getCell(CRMExcelIndexCatalog.clientIndex));
			record.districtStr = f(row.getCell(CRMExcelIndexCatalog.districtIndex));
			record.firstClientStr = f(row.getCell(CRMExcelIndexCatalog.firstClientIndex));
			record.hangHeightUnitOneStr = f(row.getCell(CRMExcelIndexCatalog.hangHeightUnitOneIndex));
			record.hangHeightUnitThreeStr = f(row.getCell(CRMExcelIndexCatalog.hangHeightUnitThreeIndex));
			record.hangHeightUnitTwoStr = f(row.getCell(CRMExcelIndexCatalog.hangHeightUnitTwoIndex));
			record.latitudeStr = f(row.getCell(CRMExcelIndexCatalog.latitudeIndex));
			record.longitudeStr = f(row.getCell(CRMExcelIndexCatalog.longitudeIndex));
			record.orderIdStr = f(row.getCell(CRMExcelIndexCatalog.orderIdIndex));
			record.orderTypeStr = f(row.getCell(CRMExcelIndexCatalog.orderTypeIndex));
			record.ownerStr = f(row.getCell(CRMExcelIndexCatalog.ownerIndex));
			record.priceStr = f(row.getCell(CRMExcelIndexCatalog.priceIndex));
			record.productConfigurationStr = f(row.getCell(CRMExcelIndexCatalog.productConfigurationIndex));
			record.rentDateStr = f(row.getCell(CRMExcelIndexCatalog.rentDateIndex));
			record.roomTypeStr = f(row.getCell(CRMExcelIndexCatalog.roomTypeIndex));
			record.sourceStr = f(row.getCell(CRMExcelIndexCatalog.sourceIndex));
			record.stationIdStr = f(row.getCell(CRMExcelIndexCatalog.stationIdIndex));
			record.stationNameStr = f(row.getCell(CRMExcelIndexCatalog.stationNameIndex));
			record.systemAmountOneStr = f(row.getCell(CRMExcelIndexCatalog.systemAmountOneIndex));
			record.systemAmountThreeStr = f(row.getCell(CRMExcelIndexCatalog.systemAmountThreeIndex));
			record.systemAmountTwoStr = f(row.getCell(CRMExcelIndexCatalog.systemAmountTwoIndex));
			record.towerHeightStr = f(row.getCell(CRMExcelIndexCatalog.towerHeightIndex));
			record.towerTypeStr = f(row.getCell(CRMExcelIndexCatalog.towerTypeIndex));
			record.placeRentPriceCRM = f(row.getCell(CRMExcelIndexCatalog.placeRentPriceIndex));
			record.maintainPriceCRM = f(row.getCell(CRMExcelIndexCatalog.maintainPriceCRMIndex));
			record.generateElecPriceCRM = f(row.getCell(CRMExcelIndexCatalog.generateElecPriceCRM));
			record.powerCablePriceCRM = f(row.getCell(CRMExcelIndexCatalog.powerCablePriceCRM));
			record.orderState = f(row.getCell(CRMExcelIndexCatalog.orderStateIndex));
			
			orderIdMapCRMDataMarketRecord.put(record.orderIdStr, record);
			if(staIdMapCRMSiteRelatedStatistic.containsKey(record.stationIdStr)){
				CRMSiteRelatedStatistic tempStatistic = staIdMapCRMSiteRelatedStatistic.get(record.stationIdStr);
				if(!tempStatistic.clientsStr.contains(record.clientStr)){ // add a new client
					tempStatistic.clientsStr = tempStatistic.clientsStr + "," + record.clientStr;
					tempStatistic.clientAmountStr = (Integer.parseInt(tempStatistic.clientAmountStr)+1)+"";
				}
				if(!tempStatistic.towerTypesStr.contains(record.towerTypeStr)){
					tempStatistic.towerTypesStr = tempStatistic.towerTypesStr + "," + record.towerTypeStr;
					tempStatistic.towerTypeAmountStr = (Integer.parseInt(tempStatistic.towerTypeAmountStr)+1)+"";
				}
				
				switch(record.clientStr){
					case "移动":tempStatistic.mobileOrderAmountStr = (Integer.parseInt(tempStatistic.mobileOrderAmountStr)+1)+"";break;
					case "电信":tempStatistic.teleOrderAmountStr = (Integer.parseInt(tempStatistic.teleOrderAmountStr)+1)+"";break;
					case "联通":tempStatistic.unicomOrderAmountStr = (Integer.parseInt(tempStatistic.unicomOrderAmountStr)+1)+"";break;
					default: System.out.println("client name fault: " + record.clientStr);
				}
				staIdMapCRMSiteRelatedStatistic.put(record.stationIdStr, tempStatistic);
			}else{
				CRMSiteRelatedStatistic statistic = new CRMSiteRelatedStatistic();
				statistic.clientAmountStr = "1";
				statistic.clientsStr = record.clientStr;
				statistic.latestRentDateStr = record.rentDateStr;
				statistic.stationIdStr = record.stationIdStr;
				statistic.towerTypeAmountStr = "1";
				statistic.towerTypesStr = record.towerTypeStr;
				
				switch(record.clientStr){
					case "移动":statistic.mobileOrderAmountStr = "1";statistic.teleOrderAmountStr = "0";statistic.unicomOrderAmountStr = "0";break;
					case "电信":statistic.mobileOrderAmountStr = "0";statistic.teleOrderAmountStr = "1";statistic.unicomOrderAmountStr = "0";break;
					case "联通":statistic.mobileOrderAmountStr = "0";statistic.teleOrderAmountStr = "0";statistic.unicomOrderAmountStr = "1";break;
					default: System.out.println("client name fault: " + record.clientStr);
					statistic.mobileOrderAmountStr = "0";statistic.teleOrderAmountStr = "0";statistic.unicomOrderAmountStr = "0";
				}
				staIdMapCRMSiteRelatedStatistic.put(record.stationIdStr, statistic);
			}
		}
	}
	
	private void pushIntoDataBase(){
print("pursh into database");		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CRM_Income_Reference");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Iterator<String> keyIter = orderIdMapCRMDataMarketRecord.keySet().iterator();
		while (keyIter.hasNext()) {
			CRMDataMarketRecord tempRecord = orderIdMapCRMDataMarketRecord.get(keyIter.next());
			tempRecord.cRMSiteRelatedStatistic = staIdMapCRMSiteRelatedStatistic.get(tempRecord.stationIdStr);
			
			CRM_Income_Reference tempRefer = new CRM_Income_Reference();
			tempRefer.setAntennaAmount(tempRecord.antennaAmountOneStr);
			tempRefer.setCity(tempRecord.cityStr);
			tempRefer.setClient(tempRecord.clientStr);
			tempRefer.setClientAmount(tempRecord.cRMSiteRelatedStatistic.clientAmountStr);
			tempRefer.setClients(tempRecord.cRMSiteRelatedStatistic.clientsStr);
			tempRefer.setDistrict(tempRecord.districtStr);
			tempRefer.setFirstClient(tempRecord.firstClientStr);
			tempRefer.setHangHeight(tempRecord.hangHeightUnitOneStr);
			tempRefer.setId(tempRecord.orderIdStr);
			tempRefer.setLatestRentDate(tempRecord.cRMSiteRelatedStatistic.latestRentDateStr);
			tempRefer.setLatitude(tempRecord.latitudeStr);
			tempRefer.setLongitude(tempRecord.longitudeStr);
			tempRefer.setMobileOrderAmount(tempRecord.cRMSiteRelatedStatistic.mobileOrderAmountStr);
			tempRefer.setOrderType(tempRecord.orderTypeStr);
			tempRefer.setOwner(tempRecord.ownerStr);
			tempRefer.setPrice(tempRecord.priceStr);
			tempRefer.setProductConfiguration(tempRecord.productConfigurationStr);
			tempRefer.setProductUnit("1");
			tempRefer.setRentDate(tempRecord.rentDateStr);
			tempRefer.setRoomType(tempRecord.roomTypeStr);
			tempRefer.setSource(tempRecord.sourceStr);
			tempRefer.setStationId(tempRecord.stationIdStr);
			tempRefer.setStationName(tempRecord.stationNameStr);
			tempRefer.setSystemAmount(tempRecord.systemAmountOneStr);
			tempRefer.setTeleOrderAmount(tempRecord.cRMSiteRelatedStatistic.teleOrderAmountStr);
			tempRefer.setTowerHeight(tempRecord.towerHeightStr);
			tempRefer.setTowerType(tempRecord.cRMSiteRelatedStatistic.towerTypesStr);
			tempRefer.setUnicomOrderAmount(tempRecord.cRMSiteRelatedStatistic.unicomOrderAmountStr);
			tempRefer.setClientAmount(tempRecord.cRMSiteRelatedStatistic.clientAmountStr);
			tempRefer.setTowerTypeAmount(tempRecord.cRMSiteRelatedStatistic.towerTypeAmountStr);
			em.persist(tempRefer);
		}
		tx.commit();
		em.close();
		factory.close();
	}
	private String f(Cell cell){
		if(cell == null){
			return "";
		}else{
			return cell.getStringCellValue();
		}
	}
	
	public static void main(String[] args) {
		
		CRMExcelExtractor ext = new CRMExcelExtractor();
		ext.run();
//		ext.pushIntoDataBase();
	}

}
