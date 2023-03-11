package Framework;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataExtractor {
    public ArrayList<String> extractData() throws IOException {
        FileInputStream file = new FileInputStream("dataFile/Translations.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = null;
        int column = 0;
        ArrayList list = new ArrayList();


        int sheetsCount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetsCount; i++) {
            if (workbook.getSheetName(0).equalsIgnoreCase("Sheet1")) {
                sheet = workbook.getSheetAt(i);
            }
            Iterator<Row> rows = sheet.iterator();
            Row firstRow = rows.next();
            int k = 0;

            Iterator<Cell> cells = firstRow.cellIterator();
            while (cells.hasNext()) {
                Cell c = cells.next();
                if (c.getStringCellValue().equalsIgnoreCase("en_GB")) {
                    column = k;
                }
                k++;
            }
            while (rows.hasNext()) {
                Row r = rows.next();
//                System.out.println(r.getCell(column).getStringCellValue());
                list.add(r.getCell(column).getStringCellValue());
            }


        }
//        System.out.println(column);
//        System.out.println(list);
        return list;


    }

    public static void main(String[] args) throws IOException {
//        ArrayList list = extractData();
//        System.out.println(list);
    }
}
