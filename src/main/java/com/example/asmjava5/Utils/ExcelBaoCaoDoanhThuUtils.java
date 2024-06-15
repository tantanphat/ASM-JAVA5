package com.example.asmjava5.Utils;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Service.ThongKeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public class ExcelBaoCaoDoanhThuUtils {
    private Workbook workbook;
    private Sheet sheet;
    private Sheet sheetHoaDon;
    @Autowired
    HttpServletResponse response;
    @Autowired
    private ThongKeService thongKeService;

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
    private void writeHeaderLine(int year) {
        sheet = workbook.createSheet("Doanh Thu");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);

        createCell(row, 0, "Tháng/Năm"+year, style);
        createCell(row, 1, "Số đơn hàng", style);
        createCell(row, 2, "Số sản phầm", style);
        createCell(row, 3, "Doanh thu", style);
        createCell(row, 4, "Hóa đơn thấp nhất", style);
        createCell(row, 5, "Hóa đơn cao nhất", style);
        createCell(row, 6, "Trung bình", style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value == null) {
            cell.setCellValue(0);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private void writeDataDoanhThu(int year) {
        int rowCount = 1;


        // Tạo cell style và font
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        // Thiết lập thuộc tính font
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.BLACK.getIndex());

        // Thiết lập văn bản ở giữa của cell
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);

        for (int i = 1; i <=12;i++) {
            List<Object[]> dt = thongKeService.thongKeDoanhThuThangTheoNam(i,year);
            for (Object[] d : dt) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++,"Tháng "+i, style);
                createCell(row, columnCount++,d[0], style);
                createCell(row, columnCount++, d[1], style);
                createCell(row, columnCount++, d[2], style);
                createCell(row, columnCount++, d[3], style);
                createCell(row, columnCount++,d[4], style);
                createCell(row, columnCount++,d[5], style);
            }
        }


    }
    public void export(int year, String excelFilePath) throws IOException {
       try {
           workbook = getWorkbook(excelFilePath);
           writeHeaderLine(year);
           writeDataDoanhThu(year);

           // Ghi workbook vào OutputStream của HTTP response
           ServletOutputStream outputStream = response.getOutputStream();
           workbook.write(outputStream);

           // Đóng outputStream sau khi ghi dữ liệu
           outputStream.close();

           // Ghi workbook vào file
           createOutputFile(workbook, excelFilePath);

           // Đóng workbook sau khi ghi tất cả dữ liệu
           workbook.close();
       } catch (IOException e) {
           System.out.println("Tệp đang được mở");
       }
    }

}




