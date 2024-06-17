package com.example.asmjava5.Utils;

import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Repository.KhachHangRepository;
import com.example.asmjava5.Service.KhachHangService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.access.prepost.PreAuthorize;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelKhachHangUtils {


    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
//        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) {
        try {
            File file = new File(excelFilePath);
            file.getParentFile().mkdirs();
            try (OutputStream os = new FileOutputStream(excelFilePath)) {
                workbook.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to create output file: " + e.getMessage());
        }
    }

    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private static void writeHeader(Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);

        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ID");

        cell = row.createCell(1);
        cell.setCellValue("Name");

        cell = row.createCell(2);
        cell.setCellValue("Address");

        cell = row.createCell(3);
        cell.setCellValue("Phone");

        cell = row.createCell(4);
        cell.setCellValue("Email");

        cell = row.createCell(5);
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
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("Tệp được chỉ định không phải là tệp Excel");
        }
        return workbook;
    }

    public static void writeExcel(List<KhachHang> khachhang, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Khách Hàng");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;
        for (KhachHang kh : khachhang) {
            Row row = sheet.createRow(rowIndex);
            writeBook(kh, row);
            rowIndex++;
        }

        // Tự động thay đổi kích thước chiều rộng cột
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);


        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }


}
