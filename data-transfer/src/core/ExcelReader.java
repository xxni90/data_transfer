package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private POIFSFileSystem fs;
    private Workbook        wb;
    private Sheet           sheet;
    private Row             titleRow;
    private int             colNum;
    private int             rowNum;
    Object                  data[][] = null;

    private ExcelReader(InputStream is, int version) {
        try {
            if (version == 2003) {
                fs = new POIFSFileSystem(is);
                wb = new HSSFWorkbook(fs);
            } else {

                wb = new XSSFWorkbook(is);
            }
            sheet = wb.getSheetAt(0);
            titleRow = sheet.getRow(0);
            colNum = titleRow.getPhysicalNumberOfCells();
            rowNum = sheet.getLastRowNum();
            data = new Object[rowNum][colNum];
            parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 第一行为标题
     */
    private ExcelReader(InputStream is) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);
            titleRow = sheet.getRow(0);
            colNum = titleRow.getPhysicalNumberOfCells();
            rowNum = sheet.getLastRowNum();
            data = new Object[rowNum][colNum];
            parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse() {
        for (int i = 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            Object[] eachRow = new Object[colNum];
            int j = 0;
            while (j < colNum) {
                Cell cel = row.getCell(j);
                eachRow[j] = getCellValue(cel);
                j++;
            }
            data[i - 1] = eachRow;
        }
    }

    private Object getCellValue(Cell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

    public static ExcelReader read(InputStream is) {
        ExcelReader excelReader = new ExcelReader(is);
        return excelReader;

    }

    public static ExcelReader read(InputStream is, int version) {
        ExcelReader excelReader = new ExcelReader(is, version);
        return excelReader;

    }

    public Object[][] getData() {
        return data;
    }

    public static void main(String[] args) {
        try {
            // 对读取Excel表格标题测试
            InputStream is = new FileInputStream("/Users/mishi/Documents/429.xls");
            ExcelReader excelReader = ExcelReader.read(is);
            System.out.println(excelReader.getData());

        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }
}
