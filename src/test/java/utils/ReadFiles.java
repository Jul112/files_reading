package utils;

import com.codeborne.pdftest.PDF;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class ReadFiles {

    public static String readTextFromFile(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
    public static String readTextFromFilePath(String path) throws IOException {
        return readTextFromFile(getFile(path));
    }

    public static PDF readPdfFromFilePath(String path) throws IOException {
        return new PDF(getFile(path));
    }

    public static File getFile(String path) {
        return new File(path);
    }

    public static String readXlsxFromFilePath(String path){
        String result = "";
        XSSFWorkbook myExcelBook = null;

        try {
            myExcelBook = new XSSFWorkbook(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        Iterator<Row> rows = myExcelSheet.iterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                result += cell.toString();
            }
        }
        try {
            myExcelBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String readCellOfXlsx(String path) throws IOException, NullPointerException{
        File excelFile = new File(path);
        FileInputStream fis = new FileInputStream(excelFile);
        String actualText = "";
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        while(rowIt.hasNext()) {
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                actualText = cell.toString();
            }
        }
        workbook.close();
        fis.close();
        return actualText;
    }

    public static String readDocxFromFilePath(String path) throws Exception{
        FileInputStream fis = new FileInputStream(getFile(path));
        XWPFDocument document = new XWPFDocument(fis);
        XWPFWordExtractor extractor = new XWPFWordExtractor(document);
        return extractor.getText();
    }
}