package readLargeExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;


public class XlsxStreamer {
	
	public static void main(String[] args) throws Exception {
		int count = 0;
		InputStream is = new FileInputStream(new File("/home/cc/Downloads/Excels/CRM/16214794064435/350000+350100.xlsx"));
		Workbook workbook = StreamingReader.builder()
		        .rowCacheSize(10000)    // number of rows to keep in memory (defaults to 10)
		        .bufferSize(40960000)     // buffer size to use when reading InputStream to file (defaults to 1024)
		        .open(is);            // InputStream or File for XLSX file (required)
		Sheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			count++;
//			System.out.println(row.getCell(5).getStringCellValue());
			System.out.println(count);
		}
		
	}
}
