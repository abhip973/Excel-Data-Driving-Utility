package Framework;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
    public static void main(String[] args) throws IOException {


    }

    public ArrayList<String> getData(String testCaseName) throws IOException {
        ArrayList list = new ArrayList<>();

        //Step 1: Creating object of XSSFWorkbook sheet
        FileInputStream fis = new FileInputStream("dataFile/DemoData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //Step 2: Get access to the sheet
        int sheetsCount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetsCount; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator(); //Sheet is collection of rows
                // We need to find the column name from first row, so for that we need to first get our first row
                Row firstRow = rows.next();

                //Now we need to iterate through first row, to find the column where testCase is present.
                Iterator<Cell> cells = firstRow.cellIterator(); //Row is collection of cells
                int k = 0;
                int column = 0;
                while (cells.hasNext()) {
                    Cell value = cells.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        //We found our desired column
                        column = k;
                    }
                    k++;

                }
                System.out.println(column);
                //Once column is identified, then scan entire column to identify purchase row
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        //Now we need to grab all the data of row
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                list.add(c.getStringCellValue());
                            } else if (c.getCellType() == CellType.NUMERIC) {
                                list.add(c.getNumericCellValue());

                            }
                        }
                    }
                }


            }
        }
        return list;
    }
}
