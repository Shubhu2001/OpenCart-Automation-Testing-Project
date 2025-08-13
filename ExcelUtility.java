package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private FileInputStream fi;
    private FileOutputStream fo;
    private XSSFWorkbook wb;
    private XSSFSheet ws;
    private XSSFRow row;
    private Cell cell;
    private CellStyle style;
    private String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        int rowCount = ws.getPhysicalNumberOfRows(); // Corrected: use getPhysicalNumberOfRows() for row count
        wb.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        int cellCount = row == null ? 0 : row.getPhysicalNumberOfCells(); // Corrected: use getPhysicalNumberOfCells()
        wb.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        row = ws.getRow(rowNum);
        if (row == null) {
            wb.close();
            fi.close();
            return ""; // or return a specific message like "Row not found"
        }

        cell = row.getCell(colNum);
        String data = (cell == null) ? "" : cell.toString();

        wb.close();
        fi.close();
        return data;
    }


    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        if (row == null) {
            row = ws.createRow(rowNum);
        }
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
}
