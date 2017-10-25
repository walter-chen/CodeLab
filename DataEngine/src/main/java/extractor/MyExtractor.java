package extractor;

import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.CRM_Income_Reference;
import pojo.CRMDataMarketRecord;
import pojo.CRMSiteRelatedStatistic;
import pojo.PMSPowerCableRecord;
import pojo.PropertyRentCardSiteRelatedStatistic;

public class MyExtractor {
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
				if ((tempRecord.cityStr.contains("厦门")&&tempRecord.clientStr.contains("电信")) || (tempRecord.cityStr.contains("福州")&&tempRecord.clientStr.contains("电信"))) {
					tempRefer.setMaintainStandardCost(
							clientMapCityDistrictMapProductConfigMapMaintainCost.get(tempRecord.clientStr)
									.get(tempRecord.cityStr + tempRecord.districtStr.substring(0, 2))
									.get(tempRecord.productConfigurationStr));
					tempRefer.setGenerateElecStandardCost(clientMapCityDistrictMapGenerateElecCost.get(tempRecord.clientStr)
							.get(tempRecord.cityStr + tempRecord.districtStr.substring(0, 2)));
				} else {
					tempRefer.setMaintainStandardCost(
							clientMapCityDistrictMapProductConfigMapMaintainCost.get(tempRecord.clientStr)
									.get(tempRecord.cityStr).get(tempRecord.productConfigurationStr));
					tempRefer.setGenerateElecStandardCost(clientMapCityDistrictMapGenerateElecCost.get(tempRecord.clientStr)
							.get(tempRecord.cityStr));
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

	public static void main(String[] args) {
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

		MyExtractor myExtr = new MyExtractor();
		myExtr.clientMapCityDistrictMapGenerateElecCost = maintainGenerateElecCostExcelExtractor.clientMapCityDistrictMapGenerateElecCost;
		myExtr.clientMapCityDistrictMapProductConfigMapMaintainCost = maintainCostExcelExtractor.clientMapCityDistrictMapProductConfigMapMaintainCost;
		myExtr.orderIdMapCRMDataMarketRecord = cRMExcelExtractor.orderIdMapCRMDataMarketRecord;
		myExtr.orderIdMapPMSPowerCabelRecord = pMSExcelExtractor.orderIdMapPMSPowerCabelRecord;
		myExtr.staIdMapCRMSiteRelatedStatistic = cRMExcelExtractor.staIdMapCRMSiteRelatedStatistic;
		myExtr.staIdMapPropertyRentCardSiteRelatedStatistic = propertyRentCardExcelExtractor.staIdMapPropertyRentCardSiteRelatedStatistic;
		myExtr.pushIntoDataBase();

	}
}
