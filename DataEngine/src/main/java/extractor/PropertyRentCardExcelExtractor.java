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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import commons.PathCatalog;
import commons.PropertyRentCardExcelIndexCatalog;
import domain.CRM_Income_Reference;
import pojo.CRMDataMarketRecord;
import pojo.CRMSiteRelatedStatistic;
import pojo.PropertyRentCardSiteRelatedStatistic;

public class PropertyRentCardExcelExtractor implements Runnable {
	public Map<String, CRMDataMarketRecord> orderIdMapCRMDataMarketRecord = new HashMap<String, CRMDataMarketRecord>();
	public Map<String, PropertyRentCardSiteRelatedStatistic> staIdMapPropertyRentCardSiteRelatedStatistic = new HashMap<String, PropertyRentCardSiteRelatedStatistic>();
	public Map<String, CRMSiteRelatedStatistic> staIdMapCRMSiteRelatedStatistic = new HashMap<String, CRMSiteRelatedStatistic>();
	
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
		File renamedChoice = new File(choice.getParent() + "/" + System.currentTimeMillis() + ".xls");
		choice.renameTo(renamedChoice);
		return renamedChoice;
	}

	public void run() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String source = lastFileModified(PathCatalog.propertyRentCard+"/"+dateFormat.format(now)).getAbsolutePath();
		InputStream inp;
		try {
			inp = new FileInputStream(source);
			Workbook wb = new HSSFWorkbook(inp);
			for (Sheet sheet : wb) {
				cumulateRecordToMap(sheet);
			}
			wb.close();
			inp.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator<String> iter  = orderIdMapCRMDataMarketRecord.keySet().iterator();
		while(iter.hasNext()){
			CRMDataMarketRecord tempRecord = orderIdMapCRMDataMarketRecord.get(iter.next());
			if(staIdMapPropertyRentCardSiteRelatedStatistic.containsKey(tempRecord.stationIdStr)){
				PropertyRentCardSiteRelatedStatistic tempStatis = staIdMapPropertyRentCardSiteRelatedStatistic.get(tempRecord.stationIdStr);
				tempRecord.propertyRentCardSiteRelatedStatistic.contractPricePerYear = tempStatis.contractPricePerYear;
				tempRecord.propertyRentCardSiteRelatedStatistic.averagePricePerYear = tempStatis.averagePricePerYear;
			}
		}
	}

	private void cumulateRecordToMap(Sheet sheet) {
		int tileLineAmount = 1;
		for (Row row : sheet) {
			if (tileLineAmount-- > 0) {
				continue;
			}
			PropertyRentCardSiteRelatedStatistic statistic = new PropertyRentCardSiteRelatedStatistic();
			statistic.stationIdStr = row.getCell(PropertyRentCardExcelIndexCatalog.stationIdIndex).getStringCellValue();
			String cardSource = row.getCell(PropertyRentCardExcelIndexCatalog.cardSourceIndex).getStringCellValue();
			if (cardSource.equals("存量接收长摊"))
				statistic.averagePricePerYear = Double.parseDouble(
						row.getCell(PropertyRentCardExcelIndexCatalog.pricePerMonthIndex).getStringCellValue()) * 12
						+ "";
			if (cardSource.equals("合同"))
				statistic.contractPricePerYear = Double.parseDouble(
						row.getCell(PropertyRentCardExcelIndexCatalog.pricePerMonthIndex).getStringCellValue()) * 12
						+ "";

			if (staIdMapPropertyRentCardSiteRelatedStatistic.containsKey(statistic.stationIdStr)) {
				PropertyRentCardSiteRelatedStatistic tempStatis = staIdMapPropertyRentCardSiteRelatedStatistic
						.get(statistic.stationIdStr);
				tempStatis.averagePricePerYear = (Double.parseDouble(tempStatis.averagePricePerYear)
						+ Double.parseDouble(statistic.averagePricePerYear)) + "";
				tempStatis.contractPricePerYear = (Double.parseDouble(tempStatis.contractPricePerYear)
						+ Double.parseDouble(statistic.contractPricePerYear)) + "";
			} else
				staIdMapPropertyRentCardSiteRelatedStatistic.put(statistic.stationIdStr, statistic);
		}
	}
	private String f(Cell cell){
		if(cell == null){
			return "";
		}else{
			return cell.getStringCellValue();
		}
	}
	private void pushIntoDataBase(){
		print("push into database");		
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("CRM_Income_Reference");
				EntityManager em = factory.createEntityManager();
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				Iterator<String> keyIter = orderIdMapCRMDataMarketRecord.keySet().iterator();
				while (keyIter.hasNext()) {
					CRMDataMarketRecord tempRecord = orderIdMapCRMDataMarketRecord.get(keyIter.next());
					if(staIdMapPropertyRentCardSiteRelatedStatistic.containsKey(tempRecord.stationIdStr))
						tempRecord.propertyRentCardSiteRelatedStatistic = staIdMapPropertyRentCardSiteRelatedStatistic.get(tempRecord.stationIdStr);
					if(staIdMapCRMSiteRelatedStatistic.containsKey(tempRecord.stationIdStr))
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
					tempRefer.setPlaceRentAverageCostPerYearProperty(tempRecord.propertyRentCardSiteRelatedStatistic.averagePricePerYear);
					tempRefer.setPlaceRentContractCostPerYearProperty(tempRecord.propertyRentCardSiteRelatedStatistic.contractPricePerYear);
					em.persist(tempRefer);
				}
				tx.commit();
				em.close();
				factory.close();
			}

	public static void main(String[] args) {
//		CRMExcelExtractor cRMExcelExtractor = new CRMExcelExtractor();
//		cRMExcelExtractor.run();
		PropertyRentCardExcelExtractor ext = new PropertyRentCardExcelExtractor();
//		ext.orderIdMapCRMDataMarketRecord = cRMExcelExtractor.orderIdMapCRMDataMarketRecord;
//		ext.staIdMapCRMSiteRelatedStatistic = cRMExcelExtractor.staIdMapCRMSiteRelatedStatistic;
		ext.run();
//		ext.pushIntoDataBase();
	}

}
