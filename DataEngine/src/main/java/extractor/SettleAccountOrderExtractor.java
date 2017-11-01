package extractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;

import commons.CRMSettleAccountOrderExcelIndexCatalog;
import commons.PathCatalog;
import domain.CRM_SettleAccount_Order_Info;

public class SettleAccountOrderExtractor implements Runnable {
	
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
		File renamedChoice = new File(choice.getParent() + "/" + System.currentTimeMillis() + ".xlsx");
		choice.renameTo(renamedChoice);
		return renamedChoice;
	}

	@Override
	public void run() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		File maintainFile = lastFileModified(PathCatalog.settleAccountOrder + "/" + dateFormat.format(now));
		try {
			InputStream is = new FileInputStream(maintainFile);
			Workbook workbook = StreamingReader.builder()
			        .rowCacheSize(10000)    // number of rows to keep in memory (defaults to 10)
			        .bufferSize(40960000)     // buffer size to use when reading InputStream to file (defaults to 1024)
			        .open(is);            // InputStream or File for XLSX file (required)
			Sheet sheet = workbook.getSheetAt(0);
			pushIntoDataBase(sheet);
			workbook.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void pushIntoDataBase(Sheet sheet) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CRM_Income_Reference");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		int tileLineAmount = 1;
		int amountA = 0;
		for (Row row : sheet) {
			if (tileLineAmount-- > 0) {
				continue;
			}
			System.out.println(amountA++);
			String month = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.monthIndex));
			String id = month+"|"+f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.productBusinessConfirmOrderIndex));
			String productBusinessConfirmOrder = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.productBusinessConfirmOrderIndex));
			String client = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.clientIndex));
			String clientCity = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.clientCityIndex));
			String demandConfirmOrder = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.demandConfirmOrderIndex));
			String stationName = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.stationNameIndex));
			String stationNo = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.stationNoIndex));
			String serviceBeginDate = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.serviceBeginDateIndex));
			String generateElec = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.generateElecIndex));
			String tower = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.towerIndex));
			String room = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.roomIndex));
			String supportingFacility = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.supportingFacilityIndex));
			String placeRentFee = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.placeRentFeeIndex));
			String maintainFee = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.maintainFeeIndex));
			String powerCable = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.powerCableIndex));
			String allFee = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.allFeeIndex));
			String towerSharedState = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.towerSharedStateIndex));
			String roomSharedState = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.roomSharedStateIndex));
			String supportingFacilitySharedState = f(row.getCell(CRMSettleAccountOrderExcelIndexCatalog.supportingFacilitySharedStateIndex));
			
			CRM_SettleAccount_Order_Info info = new CRM_SettleAccount_Order_Info();
			info.setMonth(month);
			info.setId(id);
			info.setProductBusinessConfirmOrder(productBusinessConfirmOrder);
			info.setClient(client);
			info.setClientCity(clientCity);
			info.setDemandConfirmOrder(demandConfirmOrder);
			info.setStationName(stationName);
			info.setStationNo(stationNo);
			info.setServiceBeginDate(serviceBeginDate);
			info.setGenerateElec(generateElec);
			info.setTower(tower);
			info.setRoom(room);
			info.setSupportingFacility(supportingFacility);
			info.setPlaceRentFee(placeRentFee);
			info.setMaintainFee(maintainFee);
			info.setPowerCable(powerCable);
			info.setAllFee(allFee);
			info.setTowerSharedState(towerSharedState);
			info.setRoomSharedState(roomSharedState);
			info.setSupportingFacilitySharedState(supportingFacilitySharedState);
			
			em.persist(info);
		}
		tx.commit();
		em.close();
		factory.close();
	}
	private String f(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			return cell.getStringCellValue();
		}
	}
	public static void main(String[] args){
//		SettleAccountOrderExtractor sa = new SettleAccountOrderExtractor();
//		sa.run();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReadDatabase");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		CRM_SettleAccount_Order_Info info;
		info = em.find(CRM_SettleAccount_Order_Info.class, "0");
		em.remove(info);
		
		tx.commit();
		em.close();
		factory.close();
	}
}
