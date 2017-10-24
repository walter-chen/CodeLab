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

import commons.PMSExcelIndexCatalog;
import commons.PathCatalog;
import domain.CRM_Income_Reference;
import pojo.CRMDataMarketRecord;
import pojo.CRMSiteRelatedStatistic;
import pojo.PMSPowerCableRecord;
import pojo.PropertyRentCardSiteRelatedStatistic;

public class PMSExcelExtractor {
	public Map<String, CRMDataMarketRecord> orderIdMapCRMDataMarketRecord = new HashMap<String, CRMDataMarketRecord>();
	public Map<String, CRMSiteRelatedStatistic> staIdMapCRMSiteRelatedStatistic = new HashMap<String, CRMSiteRelatedStatistic>();
	public Map<String, PMSPowerCableRecord> orderIdMapPMSPowerCabelRecord = new HashMap<String, PMSPowerCableRecord>();
	public Map<String, PropertyRentCardSiteRelatedStatistic> staIdMapPropertyRentCardSiteRelatedStatistic = new HashMap<String, PropertyRentCardSiteRelatedStatistic>();
	private int howManyTimes = 0;

	public static void print(String msg) {
		System.out.println(msg);
	}

	public static File lastFileModified(String dir) {
		File fl = new File(dir);
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
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
		return choice;
	}

	public void run() {

		File dir = lastFileModified(PathCatalog.pMSExcelPath);
		for (File f : dir.listFiles()) {
			InputStream is;
			try {
				is = new FileInputStream(f);
				Workbook workbook = StreamingReader.builder().rowCacheSize(10000) 
						.bufferSize(40960000) // buffer size to use when reading
												// InputStream to file (defaults
												// to 1024)
						.open(is); // InputStream or File for XLSX file
									// (required)
				Sheet sheet = workbook.getSheetAt(0);
				print("cumulate " + PathCatalog.pMSExcelPath);
				cumulateRecordToMap(sheet);
				System.out.println(PathCatalog.pMSExcelPath + " " + howManyTimes);
				workbook.close();
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void cumulateRecordToMap(Sheet sheet) {
		int tileLineAmount = 4;
		for (Row row : sheet) {
			if (tileLineAmount-- > 0) {
				continue;
			}
			howManyTimes++;
			PMSPowerCableRecord record = new PMSPowerCableRecord();
			record.orderIdStr = f(row.getCell(PMSExcelIndexCatalog.orderIdIndexMobile)) +
					f(row.getCell(PMSExcelIndexCatalog.orderIdIndexTele))+
					f(row.getCell(PMSExcelIndexCatalog.orderIdIndexUnicom));
			record.stationIdStr = f(row.getCell(PMSExcelIndexCatalog.stationIdIndex));
			record.projectProgress = f(row.getCell(PMSExcelIndexCatalog.projectProgressIndex));
			record.powerCableCost = f(row.getCell(PMSExcelIndexCatalog.powerCableCostIndex));
			orderIdMapPMSPowerCabelRecord.put(record.orderIdStr, record);
		}
	}

	private String f(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			return cell.getStringCellValue();
		}
	}

	private void pushIntoDataBase() {
		print("push into database");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CRM_Income_Reference");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Iterator<String> keyIter = orderIdMapCRMDataMarketRecord.keySet().iterator();
		while (keyIter.hasNext()) {
			CRMDataMarketRecord tempRecord = orderIdMapCRMDataMarketRecord.get(keyIter.next());
			if (staIdMapPropertyRentCardSiteRelatedStatistic.containsKey(tempRecord.stationIdStr))
				tempRecord.propertyRentCardSiteRelatedStatistic = staIdMapPropertyRentCardSiteRelatedStatistic
						.get(tempRecord.stationIdStr);
			if (staIdMapCRMSiteRelatedStatistic.containsKey(tempRecord.stationIdStr))
				tempRecord.cRMSiteRelatedStatistic = staIdMapCRMSiteRelatedStatistic.get(tempRecord.stationIdStr);
			if (orderIdMapPMSPowerCabelRecord.containsKey(tempRecord.orderIdStr))
				tempRecord.pMSPowerCabelRecord = orderIdMapPMSPowerCabelRecord.get(tempRecord.orderIdStr);

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
			tempRefer.setAveragePricePerYear(tempRecord.propertyRentCardSiteRelatedStatistic.averagePricePerYear);
			tempRefer.setContractPricePerYear(tempRecord.propertyRentCardSiteRelatedStatistic.contractPricePerYear);
			tempRefer.setPowerCableCost(tempRecord.pMSPowerCabelRecord.powerCableCost);
			tempRefer.setProjectProgress(tempRecord.pMSPowerCabelRecord.projectProgress);
			em.persist(tempRefer);
		}
		tx.commit();
		em.close();
		factory.close();
	}

	public static void main(String[] args) {
		CRMExcelExtractor cRMExcelExtractor = new CRMExcelExtractor();
		cRMExcelExtractor.run();

		PropertyRentCardExcelExtractor propertyRentCardExcelExtractor = new PropertyRentCardExcelExtractor();
		// propertyRentCardExcelExtractor.orderIdMapCRMDataMarketRecord =
		// cRMExcelExtractor.orderIdMapCRMDataMarketRecord;
		// propertyRentCardExcelExtractor.staIdMapCRMSiteRelatedStatistic =
		// cRMExcelExtractor.staIdMapCRMSiteRelatedStatistic;
		propertyRentCardExcelExtractor.run();

		PMSExcelExtractor pMSExcelExtractor = new PMSExcelExtractor();
		pMSExcelExtractor.orderIdMapCRMDataMarketRecord = cRMExcelExtractor.orderIdMapCRMDataMarketRecord;
		pMSExcelExtractor.staIdMapCRMSiteRelatedStatistic = cRMExcelExtractor.staIdMapCRMSiteRelatedStatistic;
		pMSExcelExtractor.staIdMapPropertyRentCardSiteRelatedStatistic = propertyRentCardExcelExtractor.staIdMapPropertyRentCardSiteRelatedStatistic;
		pMSExcelExtractor.run();
		pMSExcelExtractor.pushIntoDataBase();
	}
}
