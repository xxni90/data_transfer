package core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonTemplate {

    Map<Integer, Map<Integer, Double>> template = new HashMap<Integer, Map<Integer, Double>>();

    private Workbook                   wb;
    private Sheet                      sheet;
    private Row                        titleRow;
    private int                        colNum;
    private int                        rowNum;
    Object                             data[][] = null;
    String                             path     = "";

    public CommonTemplate(String type) {
        super();
        if ("gmp".equalsIgnoreCase(type)) {
            path = "/Users/mishi/Documents/workspace/data-transfer/files/template_gmp.xlsx";
        } else if ("mtl".equalsIgnoreCase(type)) {
            path = "/Users/mishi/Documents/workspace/data-transfer/files/template_mtl1.xlsx";
        }
    }

    void init() {
        BufferedInputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(path)));
            // 打开HSSFWorkbook
            XSSFWorkbook wb = new XSSFWorkbook(in);
            sheet = wb.getSheetAt(0);
            titleRow = sheet.getRow(0);
            colNum = titleRow.getPhysicalNumberOfCells();
            rowNum = sheet.getLastRowNum();
            data = new Object[rowNum][colNum];
            for (int row = 1; row < 151; row++) {
                Row er = sheet.getRow(row);
                if (row == 1) {
                    template.put(1, new HashMap<Integer, Double>());
                    template.put(2, new HashMap<Integer, Double>());
                    template.put(3, new HashMap<Integer, Double>());
                    template.put(4, new HashMap<Integer, Double>());
                    template.put(5, new HashMap<Integer, Double>());
                    template.put(6, new HashMap<Integer, Double>());
                    template.put(7, new HashMap<Integer, Double>());
                    template.put(8, new HashMap<Integer, Double>());
                }
                Integer zone = Double.valueOf(er.getCell(1)
                                                .getStringCellValue()
                                                .replace("lbs", "")
                                                .replace("lb", "")
                                                .trim()).intValue();
                template.get(1).put(zone, er.getCell(2).getNumericCellValue());
                template.get(2).put(zone, er.getCell(3).getNumericCellValue());
                template.get(3).put(zone, er.getCell(4).getNumericCellValue());
                template.get(4).put(zone, er.getCell(5).getNumericCellValue());
                template.get(5).put(zone, er.getCell(6).getNumericCellValue());
                template.get(6).put(zone, er.getCell(7).getNumericCellValue());
                template.get(7).put(zone, er.getCell(8).getNumericCellValue());
                template.get(8).put(zone, er.getCell(9).getNumericCellValue());
            }

            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Workbook getWb() {
        return wb;
    }

    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Row getTitleRow() {
        return titleRow;
    }

    public void setTitleRow(Row titleRow) {
        this.titleRow = titleRow;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

//    public static void main(String[] args) {
//        new CommonTemplate().init();
//    }

    public Map<Integer, Map<Integer, Double>> getTemplate() {
        return template;
    }

    public void setTemplate(Map<Integer, Map<Integer, Double>> template) {
        this.template = template;
    }

}
