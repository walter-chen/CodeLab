package extractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import commons.PathCatalog;
import domain.CRM_Income_Reference;
import pojo.CRMDataMarketRecord;
import pojo.CRMSiteRelatedStatistic;
import pojo.PMSPowerCableRecord;
import pojo.PropertyRentCardSiteRelatedStatistic;

public class GenerateCRMOrderInfo {
	public Map<String, CRMDataMarketRecord> orderIdMapCRMDataMarketRecord;
	public Map<String, CRMSiteRelatedStatistic> staIdMapCRMSiteRelatedStatistic;
	public Map<String, PMSPowerCableRecord> orderIdMapPMSPowerCabelRecord;
	public Map<String, PropertyRentCardSiteRelatedStatistic> staIdMapPropertyRentCardSiteRelatedStatistic;
	public Map<String, Map<String, Map<String, String>>> clientMapCityDistrictMapProductConfigMapMaintainCost;
	public Map<String, Map<String, String>> clientMapCityDistrictMapGenerateElecCost;

	public void pushIntoDataBase() {
		System.out.println("push into database");
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
			tempRefer.setOrderState(tempRecord.orderState);
			tempRefer.setPlaceRentAverageCostPerYearProperty(
					tempRecord.propertyRentCardSiteRelatedStatistic.averagePricePerYear);
			tempRefer.setPlaceRentContractCostPerYearProperty(
					tempRecord.propertyRentCardSiteRelatedStatistic.contractPricePerYear);
			tempRefer.setPowerCableCostPMS(tempRecord.pMSPowerCabelRecord.powerCableCost);
			tempRefer.setProjectProgress(tempRecord.pMSPowerCabelRecord.projectProgress);

			tempRefer.setPlaceRentPriceCRM(tempRecord.placeRentPriceCRM);
			tempRefer.setPlaceRentContractCostPerYearProperty(
					tempRecord.propertyRentCardSiteRelatedStatistic.contractPricePerYear);
			tempRefer.setPlaceRentAverageCostPerYearProperty(
					tempRecord.propertyRentCardSiteRelatedStatistic.averagePricePerYear);

			tempRefer.setPowerCablePriceCRM(tempRecord.powerCablePriceCRM);
			tempRefer.setPowerCableCostPMS(tempRecord.pMSPowerCabelRecord.powerCableCost);
			tempRefer.setProjectProgress(tempRecord.pMSPowerCabelRecord.projectProgress);

			try {
				tempRefer.setMaintainPriceCRM(tempRecord.maintainPriceCRM);
				if ((tempRecord.cityStr.contains("厦门") && tempRecord.clientStr.contains("电信"))
						|| (tempRecord.cityStr.contains("福州") && tempRecord.clientStr.contains("电信"))) {
					tempRefer.setMaintainStandardCost(clientMapCityDistrictMapProductConfigMapMaintainCost
							.get(tempRecord.clientStr).get(tempRecord.cityStr + tempRecord.districtStr.substring(0, 2))
							.get(tempRecord.productConfigurationStr));
					tempRefer.setGenerateElecStandardCost(
							clientMapCityDistrictMapGenerateElecCost.get(tempRecord.clientStr)
									.get(tempRecord.cityStr + tempRecord.districtStr.substring(0, 2)));
				} else {
					tempRefer.setMaintainStandardCost(clientMapCityDistrictMapProductConfigMapMaintainCost
							.get(tempRecord.clientStr).get(tempRecord.cityStr).get(tempRecord.productConfigurationStr));
					tempRefer.setGenerateElecStandardCost(
							clientMapCityDistrictMapGenerateElecCost.get(tempRecord.clientStr).get(tempRecord.cityStr));
				}
				tempRefer.setMaintainCostMatch(
						"" + tempRefer.getMaintainPriceCRM().equals(tempRefer.getMaintainStandardCost()));
			} catch (java.lang.NullPointerException e) {
				System.out.println(tempRecord.clientStr);
				System.out.println(tempRecord.cityStr + tempRecord.districtStr.substring(0, 2));
				System.out.println(tempRecord.productConfigurationStr);
			}
			tempRefer.setGenerateElecPriceCRM(tempRecord.generateElecPriceCRM);
			tempRefer.setGenerateElecCostMatch(
					"" + tempRefer.getGenerateElecPriceCRM().equals(tempRefer.getGenerateElecStandardCost()));

			em.persist(tempRefer);
		}
		tx.commit();
		em.close();
		factory.close();
	}

	public static void produceXLS() throws IOException {
		String[] cityNames = { "福州", "厦门", "泉州", "漳州", "宁德", "莆田", "南平", "三明", "龙岩" };
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String ouputFilepath = PathCatalog.orderInfoMarket + "/" + dateFormat.format(now);
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
			String queryStr = "select i from CRM_Income_Reference i Where i.city like '%" + cityName + "%'";
			Query query = em.createQuery(queryStr);
			List<CRM_Income_Reference> list = query.getResultList();
			System.out.println("size: " + list.size());
			FileOutputStream fos = new FileOutputStream(
					new File(PathCatalog.orderInfoMarket + "/" + dateFormat.format(now) + "/" + cityName + ".xls"));
			int rowNo = 0;
			Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("sheet1");
			for (CRM_Income_Reference object : list) {
				if (rowNo == 0) {
					String[] titles = { "站址编码", "站址名称", "属性", "产权方", "地市", "区县", "塔形数量", "租户数量", "移动订单数", "联通订单数",
							"电信订单数", "运营商", "首家", "最大起祖时间", "订单号", "订单属性", "产品配置", "挂高", "资源塔形", "资源机房", "资源塔高", "天线数",
							"系统数", "产品单元", "起祖时间", "场地费用", "场租合同年租金", "场租长摊年租金", "维护费", "标准维护费校验", "运维成本", "电力引入",
							"项目审定进度", "决算金额", "油机发电", "标准油机发电费用校验", "运维成本" };
					int columnNo = 0;
					Row row = sheet.createRow(rowNo++);
					for (String colTitle : titles)
						row.createCell(columnNo++).setCellValue(colTitle);
				}
				int columnNo = 0;
				Row row = sheet.createRow(rowNo++);
				row.createCell(columnNo++).setCellValue(object.getStationId());
				row.createCell(columnNo++).setCellValue(object.getStationName());
				row.createCell(columnNo++).setCellValue(object.getSource());
				row.createCell(columnNo++).setCellValue(object.getOwner());
				row.createCell(columnNo++).setCellValue(object.getCity());
				row.createCell(columnNo++).setCellValue(object.getDistrict());
				row.createCell(columnNo++).setCellValue(object.getTowerTypeAmount());
				row.createCell(columnNo++).setCellValue(object.getClientAmount());
				row.createCell(columnNo++).setCellValue(object.getMobileOrderAmount());
				row.createCell(columnNo++).setCellValue(object.getUnicomOrderAmount());
				row.createCell(columnNo++).setCellValue(object.getTeleOrderAmount());
				row.createCell(columnNo++).setCellValue(object.getClients());
				row.createCell(columnNo++).setCellValue(object.getFirstClient());
				row.createCell(columnNo++).setCellValue(object.getLatestRentDate());
				row.createCell(columnNo++).setCellValue(object.getId());
				row.createCell(columnNo++).setCellValue(object.getOrderType());
				row.createCell(columnNo++).setCellValue(object.getProductConfiguration());
				row.createCell(columnNo++).setCellValue(object.getHangHeight());
				row.createCell(columnNo++).setCellValue(object.getTowerType());
				row.createCell(columnNo++).setCellValue(object.getRoomType());
				row.createCell(columnNo++).setCellValue(object.getTowerHeight());
				row.createCell(columnNo++).setCellValue(object.getAntennaAmount());
				row.createCell(columnNo++).setCellValue(object.getSystemAmount());
				row.createCell(columnNo++).setCellValue(object.getProductUnit());
				row.createCell(columnNo++).setCellValue(object.getRentDate());
				row.createCell(columnNo++).setCellValue(object.getPlaceRentPriceCRM());
				row.createCell(columnNo++).setCellValue(object.getPlaceRentContractCostPerYearProperty());
				row.createCell(columnNo++).setCellValue(object.getPlaceRentAverageCostPerYearProperty());
				row.createCell(columnNo++).setCellValue(object.getMaintainPriceCRM());
				row.createCell(columnNo++).setCellValue(object.getMaintainCostMatch());
				row.createCell(columnNo++).setCellValue(object.getMaintainStandardCost());
				row.createCell(columnNo++).setCellValue(object.getPowerCablePriceCRM());
				row.createCell(columnNo++).setCellValue(object.getPowerCableCostPMS());
				row.createCell(columnNo++).setCellValue(object.getProjectProgress());
				row.createCell(columnNo++).setCellValue(object.getGenerateElecPriceCRM());
				row.createCell(columnNo++).setCellValue(object.getGenerateElecCostMatch());
				row.createCell(columnNo++).setCellValue(object.getGenerateElecStandardCost());
			}
			wb.write(fos);
			fos.flush();
			wb.close();
			fos.close();
		}

		String queryStr = "select i from CRM_Income_Reference i";
		Query query = em.createQuery(queryStr);
		List<CRM_Income_Reference> list = query.getResultList();
		System.out.println("size: " + list.size());
		FileOutputStream fos = new FileOutputStream(
				new File(PathCatalog.orderInfoMarket + "/" + dateFormat.format(now) + "/福建.xls"));
		int rowNo = 0;
		Workbook wb = new SXSSFWorkbook(1000);
		Sheet sheet = null;
		for (CRM_Income_Reference object : list) {
			if (rowNo % 50000 == 0) {
				sheet = wb.createSheet("sheet" + (rowNo/50000 + 1));
				String[] titles = { "站址编码", "站址名称", "属性", "产权方", "地市", "区县", "塔形数量", "租户数量", "移动订单数", "联通订单数", "电信订单数",
						"运营商", "首家", "最大起祖时间", "订单号", "订单属性", "产品配置", "挂高", "资源塔形", "资源机房", "资源塔高", "天线数", "系统数",
						"产品单元", "起祖时间", "场地费用", "场租合同年租金", "场租长摊年租金", "维护费", "标准维护费校验", "运维成本", "电力引入", "项目审定进度",
						"决算金额", "油机发电", "标准油机发电费用校验", "运维成本" };
				int columnNo = 0;
				Row row = sheet.createRow(rowNo++ % 50000);
				for (String colTitle : titles)
					row.createCell(columnNo++).setCellValue(colTitle);
			}
			int columnNo = 0;
			Row row = sheet.createRow(rowNo++ % 50000);
			row.createCell(columnNo++).setCellValue(object.getStationId());
			row.createCell(columnNo++).setCellValue(object.getStationName());
			row.createCell(columnNo++).setCellValue(object.getSource());
			row.createCell(columnNo++).setCellValue(object.getOwner());
			row.createCell(columnNo++).setCellValue(object.getCity());
			row.createCell(columnNo++).setCellValue(object.getDistrict());
			row.createCell(columnNo++).setCellValue(object.getTowerTypeAmount());
			row.createCell(columnNo++).setCellValue(object.getClientAmount());
			row.createCell(columnNo++).setCellValue(object.getMobileOrderAmount());
			row.createCell(columnNo++).setCellValue(object.getUnicomOrderAmount());
			row.createCell(columnNo++).setCellValue(object.getTeleOrderAmount());
			row.createCell(columnNo++).setCellValue(object.getClients());
			row.createCell(columnNo++).setCellValue(object.getFirstClient());
			row.createCell(columnNo++).setCellValue(object.getLatestRentDate());
			row.createCell(columnNo++).setCellValue(object.getId());
			row.createCell(columnNo++).setCellValue(object.getOrderType());
			row.createCell(columnNo++).setCellValue(object.getProductConfiguration());
			row.createCell(columnNo++).setCellValue(object.getHangHeight());
			row.createCell(columnNo++).setCellValue(object.getTowerType());
			row.createCell(columnNo++).setCellValue(object.getRoomType());
			row.createCell(columnNo++).setCellValue(object.getTowerHeight());
			row.createCell(columnNo++).setCellValue(object.getAntennaAmount());
			row.createCell(columnNo++).setCellValue(object.getSystemAmount());
			row.createCell(columnNo++).setCellValue(object.getProductUnit());
			row.createCell(columnNo++).setCellValue(object.getRentDate());
			row.createCell(columnNo++).setCellValue(object.getPlaceRentPriceCRM());
			row.createCell(columnNo++).setCellValue(object.getPlaceRentContractCostPerYearProperty());
			row.createCell(columnNo++).setCellValue(object.getPlaceRentAverageCostPerYearProperty());
			row.createCell(columnNo++).setCellValue(object.getMaintainPriceCRM());
			row.createCell(columnNo++).setCellValue(object.getMaintainCostMatch());
			row.createCell(columnNo++).setCellValue(object.getMaintainStandardCost());
			row.createCell(columnNo++).setCellValue(object.getPowerCablePriceCRM());
			row.createCell(columnNo++).setCellValue(object.getPowerCableCostPMS());
			row.createCell(columnNo++).setCellValue(object.getProjectProgress());
			row.createCell(columnNo++).setCellValue(object.getGenerateElecPriceCRM());
			row.createCell(columnNo++).setCellValue(object.getGenerateElecCostMatch());
			row.createCell(columnNo++).setCellValue(object.getGenerateElecStandardCost());
		}
		wb.write(fos);
		fos.flush();
		wb.close();
		fos.close();

		tx.commit();
		em.close();
		factory.close();
	}

	public static void main(String[] args) throws IOException {
		CRMExcelExtractor cRMExcelExtractor = new CRMExcelExtractor();
		cRMExcelExtractor.run();
		PropertyRentCardExcelExtractor propertyRentCardExcelExtractor = new PropertyRentCardExcelExtractor();
		propertyRentCardExcelExtractor.run();
		PMSExcelExtractor pMSExcelExtractor = new PMSExcelExtractor();
		pMSExcelExtractor.run();
		MaintainGenerateElecCostExcelExtractor maintainGenerateElecCostExcelExtractor = new MaintainGenerateElecCostExcelExtractor();
		maintainGenerateElecCostExcelExtractor.run();
		MaintainCostExcelExtractor maintainCostExcelExtractor = new MaintainCostExcelExtractor();
		maintainCostExcelExtractor.run();

		GenerateCRMOrderInfo myExtr = new GenerateCRMOrderInfo();
		myExtr.clientMapCityDistrictMapGenerateElecCost = maintainGenerateElecCostExcelExtractor.clientMapCityDistrictMapGenerateElecCost;
		myExtr.clientMapCityDistrictMapProductConfigMapMaintainCost = maintainCostExcelExtractor.clientMapCityDistrictMapProductConfigMapMaintainCost;
		myExtr.orderIdMapCRMDataMarketRecord = cRMExcelExtractor.orderIdMapCRMDataMarketRecord;
		myExtr.orderIdMapPMSPowerCabelRecord = pMSExcelExtractor.orderIdMapPMSPowerCabelRecord;
		myExtr.staIdMapCRMSiteRelatedStatistic = cRMExcelExtractor.staIdMapCRMSiteRelatedStatistic;
		myExtr.staIdMapPropertyRentCardSiteRelatedStatistic = propertyRentCardExcelExtractor.staIdMapPropertyRentCardSiteRelatedStatistic;
		myExtr.pushIntoDataBase();
		produceXLS();
	}
}
