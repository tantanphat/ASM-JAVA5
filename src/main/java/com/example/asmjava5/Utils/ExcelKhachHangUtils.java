package com.example.asmjava5.Utils;

import com.example.asmjava5.Entity.KhachHang;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelKhachHangUtils {
    private static CellStyle cellStyleFormatNumber = null;

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // Create CellStyle for title
    private static CellStyle createStyleForTitle(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 16); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) {
        try {
            // Create parent directories if they do not exist
            File file = new File(excelFilePath);
            file.getParentFile().mkdirs();

            // Create the output file and write the workbook
            try (OutputStream os = new FileOutputStream(excelFilePath)) {
                workbook.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to create output file: " + e.getMessage());
        }
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private static void writeTitle(Sheet sheet, int rowIndex) {
        // Create CellStyle for title
        CellStyle cellStyle = createStyleForTitle(sheet);

        // Create row for title
        Row row = sheet.createRow(rowIndex);

        // Create cell for title
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Danh sách khách hàng");

        // Merge cells from A1 to F1
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
    }

    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ID");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Name");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Address");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Phone");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thành viên");
    }

    // Write data
    private static void writeBook(KhachHang kh, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(kh.getMaKH());

        cell = row.createCell(1);
        cell.setCellValue(kh.getTenKH());

        cell = row.createCell(2);
        cell.setCellValue(kh.getDiaChi());

        cell = row.createCell(3);
        cell.setCellValue(kh.getSdt());

        cell = row.createCell(4);
        cell.setCellValue(kh.getEmail());

        cell = row.createCell(5);
        cell.setCellValue(kh.isThanhVien());
    }

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not an Excel file");
        }

        return workbook;
    }

    public static void writeExcel(List<KhachHang> khachhang, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);

        // Create sheet
        Sheet sheet = workbook.createSheet("KhachHang"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write title
        writeTitle(sheet, rowIndex);

        // Write header
        rowIndex++;
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (KhachHang kh : khachhang) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(kh, row);
            rowIndex++;
        }

        // Auto resize column width
        int numberOfColumn = sheet.getRow(1).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
}
