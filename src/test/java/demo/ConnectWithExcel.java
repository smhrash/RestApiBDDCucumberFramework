package demo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ConnectWithExcel {

    public static void main(String[] args) throws IOException {

       FileInputStream fis = new FileInputStream("/Users/user/Desktop/ConnectionCheck.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheetNum = workbook.getNumberOfSheets();
        System.out.println(sheetNum);

        for (int i = 0; i < sheetNum; i++){
            if (workbook.getSheetName(i).equalsIgnoreCase("Testdata")){
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator <Row> row = sheet.iterator();
                Row firstRow = row.next();

                Iterator<Cell> cell = firstRow.cellIterator();
                int k = 0;
                 int column = 0;
                while (cell.hasNext()){
                    Cell value =  cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Data3")){
                        column = k;


                    }
                    k++;

                }
                System.out.println(column);


                while (row.hasNext()){
                   Row r =row.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase("deletedata")) {

                       
                       Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {



                            System.out.println(cv.next().getStringCellValue());
                        }




                    }
                }
            }
        }


    }
}
