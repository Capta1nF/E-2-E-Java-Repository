package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    
    public static void writeToCSV(String[] headers, String[] data, String fileName) throws IOException {
        File testDataDir = new File("test-data");
        if (!testDataDir.exists()) {
            testDataDir.mkdirs();
        }
        
        String filePath = "test-data/" + fileName;
        File file = new File(filePath);
        
        Workbook workbook;
        Sheet sheet;
        
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Test Results");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
        }
        
        // Add data row
        int lastRowNum = sheet.getLastRowNum();
        Row dataRow = sheet.createRow(lastRowNum + 1);
        for (int i = 0; i < data.length; i++) {
            Cell cell = dataRow.createCell(i);
            cell.setCellValue(data[i]);
        }
        
        // Write to file
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
    
    public static String createTestReport(List<String[]> testResults, String[] headers) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "test_report_" + timestamp + ".xlsx";
        
        File reportsDir = new File("reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
        
        String filePath = "reports/" + fileName;
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Report");
        
        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        
        // Add data rows
        for (int i = 0; i < testResults.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            String[] rowData = testResults.get(i);
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = dataRow.createCell(j);
                cell.setCellValue(rowData[j]);
            }
        }
        
        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
        
        return filePath;
    }
}
