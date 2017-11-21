package extractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.monitorjbl.xlsx.StreamingReader;

import commons.CRMSettleAccountOrderExcelIndexCatalog;
import commons.PathCatalog;
import domain.CRM_Income_Reference;
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
		if(choice == null) throw new NullPointerException();
		File renamedChoice = new File(choice.getParent() + "/" + System.currentTimeMillis() + ".xlsx");
		choice.renameTo(renamedChoice);
		return renamedChoice;
	}

	@Override
	public void run() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		File maintainFile;
		try{
			maintainFile = lastFileModified(PathCatalog.settleAccountOrderPath + "/" + dateFormat.format(now));
		}catch(NullPointerException e){
			return;
		}
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
			produceXLS();
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
			
			em.merge(info);
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
	
	public static void produceXLS() throws IOException {
		String[] cityNames = { "福州", "厦门", "泉州", "漳州", "宁德", "莆田", "南平", "三明", "龙岩" };
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String ouputFilepath = PathCatalog.settleAccountOutputPath + "/" + dateFormat.format(now);
		File dir = new File(ouputFilepath);
		if (dir.exists()) {
			for (File f : dir.listFiles())
				f.delete();
			dir.delete();
		}
		dir.mkdir();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReadDatabase");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		for (String cityName : cityNames) {
			String queryStr = "select i from CRM_SettleAccount_Order_Info i Where i.clientCity like '%" + cityName + "%'";
			Query query = em.createQuery(queryStr);
			List<CRM_SettleAccount_Order_Info> list = query.getResultList();
			System.out.println("size: " + list.size());
			FileOutputStream fos = new FileOutputStream(
					new File(PathCatalog.settleAccountOutputPath + "/" + dateFormat.format(now) + "/" + cityName + ".xls"));
			int rowNo = 0;
			Workbook wb = new SXSSFWorkbook(1000);
			Sheet sheet = wb.createSheet("sheet1");
			for (CRM_SettleAccount_Order_Info object : list) {
				if (rowNo == 0) {
					String[] titles = { "地市", "帐期月份", "运营商", "产品业务确认单编号", "需求确认单编号", "站址名称", "站址编码",
							"服务起始日期", "费用合计", "塔(出账单)", "机房(出账单)", "配套(出账单)", "场地费(出账单)", 
							"维护费(出账单)", "油机发电(出账单)", "电力引入(出账单)", "塔(共享情况)", "机房(共享情况)", "配套(共享情况)"};
					int columnNo = 0;
					Row row = sheet.createRow(rowNo++);
					for (String colTitle : titles)
						row.createCell(columnNo++).setCellValue(colTitle);
				}
				int columnNo = 0;
				Row row = sheet.createRow(rowNo++);
				row.createCell(columnNo++).setCellValue(object.getClientCity());
				row.createCell(columnNo++).setCellValue(object.getMonth());
				row.createCell(columnNo++).setCellValue(object.getClient());
				row.createCell(columnNo++).setCellValue(object.getProductBusinessConfirmOrder());
				row.createCell(columnNo++).setCellValue(object.getDemandConfirmOrder());
				row.createCell(columnNo++).setCellValue(object.getStationName());
				row.createCell(columnNo++).setCellValue(object.getStationNo());
				row.createCell(columnNo++).setCellValue(object.getServiceBeginDate());
				row.createCell(columnNo++).setCellValue(object.getAllFee());
				row.createCell(columnNo++).setCellValue(object.getTower());
				row.createCell(columnNo++).setCellValue(object.getRoom());
				row.createCell(columnNo++).setCellValue(object.getSupportingFacility());
				row.createCell(columnNo++).setCellValue(object.getPlaceRentFee());
				row.createCell(columnNo++).setCellValue(object.getMaintainFee());
				row.createCell(columnNo++).setCellValue(object.getGenerateElec());
				row.createCell(columnNo++).setCellValue(object.getPowerCable());
				row.createCell(columnNo++).setCellValue(object.getTowerSharedState());
				row.createCell(columnNo++).setCellValue(object.getRoomSharedState());
				row.createCell(columnNo++).setCellValue(object.getSupportingFacilitySharedState());
			}
			wb.write(fos);
			fos.flush();
			wb.close();
			fos.close();
			list.clear();
		}
		String queryStr = "select i from CRM_SettleAccount_Order_Info i";
		Query query = em.createQuery(queryStr);
		List<CRM_SettleAccount_Order_Info> list = query.getResultList();
		System.out.println("size: " + list.size());
		FileOutputStream fos = new FileOutputStream(
				new File(PathCatalog.settleAccountOutputPath + "/" + dateFormat.format(now) + "/福建.xls"));
		int rowNo = 0;
		Workbook wb = new SXSSFWorkbook(1000);
		Sheet sheet = null;
		for (CRM_SettleAccount_Order_Info object : list) {
			if (rowNo % 50000 == 0) {
				sheet = wb.createSheet("sheet" + (rowNo/50000 + 1));
				String[] titles = {"地市", "帐期月份", "运营商", "产品业务确认单编号", "需求确认单编号", "站址名称", "站址编码",
						"服务起始日期", "费用合计", "塔(出账单)", "机房(出账单)", "配套(出账单)", "场地费(出账单)", 
						"维护费(出账单)", "油机发电(出账单)", "电力引入(出账单)", "塔(共享情况)", "机房(共享情况)", "配套(共享情况)" };
				int columnNo = 0;
				Row row = sheet.createRow(rowNo++ % 50000);
				for (String colTitle : titles)
					row.createCell(columnNo++).setCellValue(colTitle);
			}
			int columnNo = 0;
			Row row = sheet.createRow(rowNo++ % 50000);
			row.createCell(columnNo++).setCellValue(object.getClientCity());
			row.createCell(columnNo++).setCellValue(object.getMonth());
			row.createCell(columnNo++).setCellValue(object.getClient());
			row.createCell(columnNo++).setCellValue(object.getProductBusinessConfirmOrder());
			row.createCell(columnNo++).setCellValue(object.getDemandConfirmOrder());
			row.createCell(columnNo++).setCellValue(object.getStationName());
			row.createCell(columnNo++).setCellValue(object.getStationNo());
			row.createCell(columnNo++).setCellValue(object.getServiceBeginDate());
			row.createCell(columnNo++).setCellValue(object.getAllFee());
			row.createCell(columnNo++).setCellValue(object.getTower());
			row.createCell(columnNo++).setCellValue(object.getRoom());
			row.createCell(columnNo++).setCellValue(object.getSupportingFacility());
			row.createCell(columnNo++).setCellValue(object.getPlaceRentFee());
			row.createCell(columnNo++).setCellValue(object.getMaintainFee());
			row.createCell(columnNo++).setCellValue(object.getGenerateElec());
			row.createCell(columnNo++).setCellValue(object.getPowerCable());
			row.createCell(columnNo++).setCellValue(object.getTowerSharedState());
			row.createCell(columnNo++).setCellValue(object.getRoomSharedState());
			row.createCell(columnNo++).setCellValue(object.getSupportingFacilitySharedState());
		}
		wb.write(fos);
		fos.flush();
		wb.close();
		fos.close();
		list.clear();
		
		tx.commit();
		em.close();
		factory.close();
	}
	
	public static void main(String[] args) throws IOException{
		produceXLS();
	}
}
