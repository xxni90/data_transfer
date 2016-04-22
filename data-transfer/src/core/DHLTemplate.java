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

public class DHLTemplate {

    Map<Integer, Double>               pe       = new HashMap<Integer, Double>();
    Map<Integer, Map<Integer, Double>> ppe      = new HashMap<Integer, Map<Integer, Double>>();
    Map<Integer, Double>               pg       = new HashMap<Integer, Double>();
    Map<Integer, Map<Integer, Double>> ppg      = new HashMap<Integer, Map<Integer, Double>>();

    private Workbook                   wb;
    private Sheet                      sheet;
    private Row                        titleRow;
    private int                        colNum;
    private int                        rowNum;
    Object                             data[][] = null;

    void init() {
        BufferedInputStream in;
        try {
            in = new BufferedInputStream(
                                         new FileInputStream(
                                                             new File(
                                                                      "/Users/mishi/Documents/workspace/data-transfer/files/V4INK DHL ECOMMERCE RATE 0806.xlsx")));
            // 打开HSSFWorkbook
            XSSFWorkbook wb = new XSSFWorkbook(in);
            sheet = wb.getSheetAt(0);
            titleRow = sheet.getRow(0);
            colNum = titleRow.getPhysicalNumberOfCells();
            rowNum = sheet.getLastRowNum();
            data = new Object[rowNum][colNum];
            Row r3 = sheet.getRow(3);
            pe.put(Double.valueOf(r3.getCell(1).getNumericCellValue()).intValue(), r3.getCell(2).getNumericCellValue());

            for (int row = 4; row < 19; row++) {
                Row er = sheet.getRow(row);
                pe.put(Double.valueOf(er.getCell(1).getNumericCellValue()).intValue(), er.getCell(2)
                                                                                         .getNumericCellValue());
                if (row == 4) {
                    ppe.put(1, new HashMap<Integer, Double>());
                    ppe.put(3, new HashMap<Integer, Double>());
                    ppe.put(4, new HashMap<Integer, Double>());
                    ppe.put(5, new HashMap<Integer, Double>());
                    ppe.put(6, new HashMap<Integer, Double>());
                    ppe.put(7, new HashMap<Integer, Double>());
                    ppe.put(8, new HashMap<Integer, Double>());
                }

                ppe.get(1).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(5).getNumericCellValue());
                ppe.get(3).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(6).getNumericCellValue());
                ppe.get(4).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(7).getNumericCellValue());
                ppe.get(5).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(8).getNumericCellValue());
                ppe.get(6).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(9).getNumericCellValue());
                ppe.get(7).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(10).getNumericCellValue());
                ppe.get(8).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(11).getNumericCellValue());
            }

            //

            //
            Row r39 = sheet.getRow(39);
            pg.put(Double.valueOf(r39.getCell(1).getNumericCellValue()).intValue(), r39.getCell(2)
                                                                                       .getNumericCellValue());

            for (int row = 24; row < 39; row++) {
                Row er = sheet.getRow(row);
                pg.put(Double.valueOf(er.getCell(1).getNumericCellValue()).intValue(), er.getCell(2)
                                                                                         .getNumericCellValue());
                if (row == 24) {
                    ppg.put(1, new HashMap<Integer, Double>());
                    ppg.put(3, new HashMap<Integer, Double>());
                    ppg.put(4, new HashMap<Integer, Double>());
                    ppg.put(5, new HashMap<Integer, Double>());
                    ppg.put(6, new HashMap<Integer, Double>());
                    ppg.put(7, new HashMap<Integer, Double>());
                    ppg.put(8, new HashMap<Integer, Double>());
                }

                ppg.get(1).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(5).getNumericCellValue());
                ppg.get(3).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(6).getNumericCellValue());
                ppg.get(4).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(7).getNumericCellValue());
                ppg.get(5).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(8).getNumericCellValue());
                ppg.get(6).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(9).getNumericCellValue());
                ppg.get(7).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(10).getNumericCellValue());
                ppg.get(8).put(Double.valueOf(er.getCell(4).getNumericCellValue()).intValue(),
                               er.getCell(11).getNumericCellValue());
            }
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Double> getPe() {
        return pe;
    }

    public void setPe(Map<Integer, Double> pe) {
        this.pe = pe;
    }

    public Map<Integer, Map<Integer, Double>> getPpe() {
        return ppe;
    }

    public void setPpe(Map<Integer, Map<Integer, Double>> ppe) {
        this.ppe = ppe;
    }

    public Map<Integer, Double> getPg() {
        return pg;
    }

    public void setPg(Map<Integer, Double> pg) {
        this.pg = pg;
    }

    public Map<Integer, Map<Integer, Double>> getPpg() {
        return ppg;
    }

    public void setPpg(Map<Integer, Map<Integer, Double>> ppg) {
        this.ppg = ppg;
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

    public static void main(String[] args) {
        new DHLTemplate().init();
    }
}
