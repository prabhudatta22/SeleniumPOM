/**
 * 
 */
package com.framework.testutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Prabhudatta.C
 *
 */
public class Test {

	List<String> header = null;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		List<Map<String, String>> reports = new CopyOnWriteArrayList<Map<String, String>>();
		String Path = System.getProperty("user.dir") + "/src/test/resources/downloads/";

		new Test().readXLSXFile(Path + "Timesheet-Report.xlsx");

	}

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> readXLSXFile(String path) throws IOException {

		List<Map<String, String>> reportMapList = new CopyOnWriteArrayList<Map<String, String>>();
		Test test = new Test();
		InputStream ExcelFileToRead = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();
		String val = null;
		List<String> kes = null;
		List<String> kesFinal = new ArrayList<>();

		int rownum = 0;

		while (rows.hasNext()) {
			kes = new ArrayList<>();
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					val = cell.getStringCellValue();
				}
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					val = String.valueOf(cell.getNumericCellValue());
				}

				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					val = String.valueOf(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						double d = cell.getNumericCellValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						val = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						val = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + val;
					}
				}

				if (!val.contains("Timesheet Report")) {
					kes.add(val);
				}
			}

			if (rownum == 1) {
				this.header = kes;
				test.setHeader(this.header);
				System.out.println(test.getHeader().size());
			}
			rownum++;
			kesFinal.addAll(kes);
		}

		reportMapList = getMappedValues(test.getHeader(), kesFinal);
		return reportMapList;
	}

	/**
	 * @param header2
	 * @param kes
	 * @return
	 */
	private List<Map<String, String>> getMappedValues(List<String> header2, List<String> kes) {
		Map<String, String> reportMap = null;
		List<Map<String, String>> reportMapList = new CopyOnWriteArrayList<Map<String, String>>();
		int z = header2.size();
		int j = 0;
		for (int i = 0; i < kes.size(); i++) {
			reportMap = new ConcurrentHashMap<String, String>();
			for (j = 0; j < header2.size(); j++) {
				reportMap.put(header2.get(j), kes.get(z + j));
			}
			z = z + j;
			i = z;
			reportMapList.add(reportMap);
		}

		return reportMapList;
	}

	/**
	 * @return the header
	 */
	public List<String> getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(List<String> header) {
		this.header = header;
	}
}
