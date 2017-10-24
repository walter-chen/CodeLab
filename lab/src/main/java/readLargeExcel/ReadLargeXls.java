package readLargeExcel;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadLargeXls {
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

	public static void main(String args[]) {
		String DIR = "/home/cc/Downloads/Excels/Property";
		File file = lastFileModified(DIR);
		String fileName = file.getPath();
		InputStream inp;
		try {
			inp = new FileInputStream(fileName);
			Workbook wb = new HSSFWorkbook(inp);
			
//			Sheet sheet = wb.getSheetAt(0);
			int FaultNumber_Index = 0, State_Index = 1;
			boolean firstLine = true;
			int count = 0;
			for(int sheetIndex = 0; sheetIndex<wb.getNumberOfSheets(); sheetIndex++){
				Sheet sheet = wb.getSheetAt(sheetIndex);
				for (Row row : sheet) {
					System.out.println(++count +" : " + row);
					if (firstLine) {
						firstLine = false;
						continue;
					}
					String faultNumber = row.getCell(FaultNumber_Index).getStringCellValue();
					String state = row.getCell(State_Index).getStringCellValue();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
