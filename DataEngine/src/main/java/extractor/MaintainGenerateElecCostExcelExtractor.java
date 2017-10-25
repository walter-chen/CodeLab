package extractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;

import commons.MaintainExcelIndexCatalog;
import commons.PathCatalog;

public class MaintainGenerateElecCostExcelExtractor {
	public Map<String, Map<String, String>> clientMapCityDistrictMapGenerateElecCost = new HashMap<String, Map<String, String>>();
	
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
		
		File maintainFile = lastFileModified(PathCatalog.maintainGenerateElecCostExcelPath);
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
		Map<String, String> cityDistrictMapGenerateElecCost = new HashMap<String, String>();
		int tileLineAmount = 1;
		for (Row row : sheet) {
			if (tileLineAmount-- > 0) {
				continue;
			}
			String client = row.getCell(MaintainExcelIndexCatalog.clientIndexGenerateElec).getStringCellValue();
			String cityDistrict = row.getCell(MaintainExcelIndexCatalog.cityDistrictIndexGenerateElec).getStringCellValue();
			String generateElecCost = "";
			try{
				generateElecCost =  String.format("%.2f", Double.parseDouble(row.getCell(MaintainExcelIndexCatalog.costIndexGenerateElec).getStringCellValue()));
			}catch(NumberFormatException e){
				
			}
			
			
			if(clientMapCityDistrictMapGenerateElecCost.containsKey(client)){
				cityDistrictMapGenerateElecCost = clientMapCityDistrictMapGenerateElecCost.get(client);
				if(cityDistrictMapGenerateElecCost.containsKey(cityDistrict)){
					System.out.println("duplicate generateElec cost: client -> cityDistrict -> generateElecCost");
				}else{
					cityDistrictMapGenerateElecCost.put(cityDistrict, generateElecCost);
				}
			}else{
				cityDistrictMapGenerateElecCost = new HashMap<String, String>();
				cityDistrictMapGenerateElecCost.put(cityDistrict, generateElecCost);
				clientMapCityDistrictMapGenerateElecCost.put(client, cityDistrictMapGenerateElecCost);
			}
			
			
		}
	}
	public static void main(String args[]){
		MaintainGenerateElecCostExcelExtractor ex = new MaintainGenerateElecCostExcelExtractor();
		ex.run();
		Map<String, Map<String, String>> clientMapCityDistrictMapGenerateElecCost = ex.clientMapCityDistrictMapGenerateElecCost;
		System.out.println("ss");
	}
}
