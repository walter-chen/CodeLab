package extractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

import commons.MaintainExcelIndexCatalog;
import commons.PMSExcelIndexCatalog;
import commons.PathCatalog;
import domain.CRM_Income_Reference;
import pojo.CRMDataMarketRecord;
import pojo.PMSPowerCableRecord;

public class MaintainCostExcelExtractor {
	public Map<String, Map<String, Map<String, String>>> clientMapCityDistrictMapProductConfigMapMaintainCost = new HashMap<String, Map<String, Map<String, String>>>();
	
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
	
	public void run() {
		
		File maintainFile = lastFileModified(PathCatalog.maintainCostExcelPath);
		try {
			InputStream is = new FileInputStream(maintainFile);
			Workbook workbook = StreamingReader.builder()
			        .rowCacheSize(10000)    // number of rows to keep in memory (defaults to 10)
			        .bufferSize(40960000)     // buffer size to use when reading InputStream to file (defaults to 1024)
			        .open(is);            // InputStream or File for XLSX file (required)
			Sheet sheet = workbook.getSheetAt(0);
			cumulateRecordToMap(sheet);
			workbook.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	private void cumulateRecordToMap(Sheet sheet) {
		Map<String ,Map<String,String>> cityDistrictMapProductConfigMapMaintainCost;
		Map<String, String> productConfigMapMaintainCost;
		int tileLineAmount = 1;
		for (Row row : sheet) {
			if (tileLineAmount-- > 0) {
				continue;
			}
			String client = row.getCell(MaintainExcelIndexCatalog.clientIndexMaintainCost).getStringCellValue();
			String cityDistrict = row.getCell(MaintainExcelIndexCatalog.cityDistrictIndexMaintainCost).getStringCellValue();
			String productConfig = row.getCell(MaintainExcelIndexCatalog.productConfigIndexMaintainCost).getStringCellValue();
			String cost = "";
			try{
				cost = String.format("%.2f", Double.parseDouble(row.getCell(MaintainExcelIndexCatalog.costIndexMaintainCost).getStringCellValue()));
			}catch(NumberFormatException e){
				
			}
			if(clientMapCityDistrictMapProductConfigMapMaintainCost.containsKey(client)){
				cityDistrictMapProductConfigMapMaintainCost = clientMapCityDistrictMapProductConfigMapMaintainCost.get(client);
				if(cityDistrictMapProductConfigMapMaintainCost.containsKey(cityDistrict)){
					productConfigMapMaintainCost = cityDistrictMapProductConfigMapMaintainCost.get(cityDistrict);
					if(productConfigMapMaintainCost.containsKey(productConfig)){
						System.out.println("duplicate "+client+" -> "+cityDistrict+" -> "+productConfig+" -> "+cost);
					}else{
						productConfigMapMaintainCost.put(productConfig, cost);
					}
				}else{
					productConfigMapMaintainCost = new HashMap<String, String>();
					productConfigMapMaintainCost.put(productConfig, cost);
					cityDistrictMapProductConfigMapMaintainCost.put(cityDistrict, productConfigMapMaintainCost);
				}
			}else{
				productConfigMapMaintainCost = new HashMap<String, String>();
				productConfigMapMaintainCost.put(productConfig, cost);
				cityDistrictMapProductConfigMapMaintainCost = new HashMap<String, Map<String, String>>();
				cityDistrictMapProductConfigMapMaintainCost.put(cityDistrict, productConfigMapMaintainCost);
				clientMapCityDistrictMapProductConfigMapMaintainCost.put(client, cityDistrictMapProductConfigMapMaintainCost);
			}
		}
	}

	public static void main(String[] args){
		MaintainCostExcelExtractor ex = new MaintainCostExcelExtractor();
		ex.run();
		Map<String, Map<String, Map<String, String>>> clientMapCityDistrictMapProductConfigMapMaintainCost = ex.clientMapCityDistrictMapProductConfigMapMaintainCost;
		System.out.println(clientMapCityDistrictMapProductConfigMapMaintainCost.get("移动").get("福州市长乐").get("23楼面抱杆+一体化机柜+其他一体化柜配套"));
	}
	
}
