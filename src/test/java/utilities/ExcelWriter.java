package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * Utility class for writing data to Excel sheet
 */
public class ExcelWriter {

    private static Workbook book;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;

    private static int colNumber =0;
    private static FileOutputStream fos = null;


    public static void writeDataToExcel(String fileName,String sheetName,String value) throws IOException {
        FileInputStream ip = new FileInputStream(fileName);


        book = WorkbookFactory.create(ip);
        sheet = book.getSheet(sheetName);
        int rowNum = sheet.getLastRowNum() + 1;
        System.out.println("Row Number"+rowNum);

        row = sheet.createRow(rowNum);
        cell = row.getCell(colNumber);
        cell = row.createCell(colNumber);
        cell.setCellValue(value);

        cell =row.getCell(colNumber+1);
        cell = row.createCell(colNumber+1);
        cell.setCellValue(StringConstants.VM_INITIAL_STATUS);

        fos = new FileOutputStream(fileName);
        book.write(fos);
        fos.close();
    }

    public static void setDestroyStatus(String fileName,String sheetName) throws IOException {
        String currentDate=Helper.getCurrentDateWithoutTime();


        FileInputStream ip = new FileInputStream(fileName);


        book = WorkbookFactory.create(ip);
        sheet = book.getSheet(sheetName);
        int rowNum = sheet.getLastRowNum() + 1;
        System.out.println("Row Number"+rowNum);

        for(int i=1;i<rowNum;i++){

            String status=sheet.getRow(i).getCell(1).getStringCellValue();
            System.out.println("Status is " +status);
            String deploymentName=sheet.getRow(i).getCell(0).getStringCellValue();
            String date =deploymentName.substring(25,33);

            if(status.equals(StringConstants.VM_INITIAL_STATUS)){
                if(!date.equals(currentDate)){
                row = sheet.getRow(i);
                cell =row.getCell(colNumber+1);
                cell.setCellValue(StringConstants.VM_FINAL_STATUS);
            }}
        }


        fos = new FileOutputStream(fileName);
        book.write(fos);
        fos.close();
    }
}
