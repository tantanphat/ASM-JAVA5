package com.example.asmjava5.Utils;

import com.example.asmjava5.Service.ThongKeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public class ExcelBaoCaoSanPhamUtils {
    private Workbook workbook;
    private Sheet sheetBanDuoc;
    private Sheet sheetKhongBanDuoc;
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
    private void writeHeaderLine(int month) {
        sheetBanDuoc = workbook.createSheet("Sản phẩm bán được tháng " + month);
        sheetKhongBanDuoc = workbook.createSheet("Sản phẩm không bán được tháng " + month);

        //Sản phẩm bán được
        Row row = sheetBanDuoc.createRow(0);

        CellStyle style = workbook.createCellStyle();
        Font font = sheetBanDuoc.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);

        createCell(row, 0, "STT", style);
        createCell(row, 1, "Mã SP", style);
        createCell(row, 2, "Tên SP", style);
        createCell(row, 3, "Đơn giá bán", style);
        createCell(row, 4, "Loại sản phẩm", style);
        createCell(row, 5, "Số lượng đã bán", style);

        //Sản phẩm không bán được
        Row rowKo = sheetKhongBanDuoc.createRow(0);

        CellStyle styleKo = workbook.createCellStyle();
        Font fontKo = sheetKhongBanDuoc.getWorkbook().createFont();
        fontKo.setFontName("Times New Roman");
        fontKo.setBold(true);
        fontKo.setFontHeightInPoints((short) 14);
        fontKo.setColor(IndexedColors.BLACK.getIndex());
        styleKo.setFont(fontKo);

        createCell(rowKo, 0, "STT", styleKo);
        createCell(rowKo, 1, "Mã SP", styleKo);
        createCell(rowKo, 2, "Tên SP", styleKo);
        createCell(rowKo, 3, "Đơn giá bán", styleKo);
        createCell(rowKo, 4, "Loại sản phẩm", styleKo);
        createCell(rowKo, 5, "Số lượng tồn kho", styleKo);
    }


    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheetBanDuoc.autoSizeColumn(columnCount);
        sheetKhongBanDuoc.autoSizeColumn(columnCount);
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

    private void writeDataDoanhThu(int month) {
        int rowCountDaBan = 1;
        int rowCountKoDaBan = 1;
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
        int i = 1;
        List<Object[]> spBanDC = thongKeService.thongKeSanPhamBanDuoc(month);

            for (Object[] d : spBanDC) {
                int columnCount = 0;
                Row row = sheetBanDuoc.createRow(rowCountDaBan++);
                createCell(row, columnCount++, i , style);
                createCell(row, columnCount++,d[0], style);
                createCell(row, columnCount++, d[1], style);
                createCell(row, columnCount++, d[2], style);
                createCell(row, columnCount++, d[3], style);
                createCell(row, columnCount++,d[4], style);
                i++;
        }

        List<Object[]> spKoBanDC = thongKeService.thongKeSanPhamKBanDuoc(month);
        System.out.println("Kích cỡ của spKoBanDC là: " + spKoBanDC.size());
        int j = 1;
            for (Object[] d : spKoBanDC) {
                int columnCountKo = 0;
                Row row = sheetKhongBanDuoc.createRow(rowCountKoDaBan++);
                createCell(row, columnCountKo++,j, style);
                createCell(row, columnCountKo++,d[0], style);
                createCell(row, columnCountKo++, d[1], style);
                createCell(row, columnCountKo++, d[2], style);
                createCell(row, columnCountKo++, d[3], style);
                createCell(row, columnCountKo++,d[4], style);
                j++;
        }

    }
    public void export(int month, String excelFilePath) throws IOException {
       try {
           workbook = getWorkbook(excelFilePath);
           writeHeaderLine(month);
           writeDataDoanhThu(month);

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




