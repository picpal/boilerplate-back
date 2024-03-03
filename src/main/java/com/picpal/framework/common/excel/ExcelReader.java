package com.picpal.framework.common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public List<Map<String, Object>> readExcelFile(InputStream inputStream) {
        List<Map<String, Object>> records = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 선택
            Row headerRow = sheet.getRow(0); // 첫 번째 행을 헤더로 간주

            // 데이터 행 처리 시작 (헤더 다음 행부터)
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue; // 비어있는 행 스킵

                Map<String, Object> record = new HashMap<>();
                // 모든 셀 순회
                for (int colIndex = 0; colIndex < row.getLastCellNum(); colIndex++) {
                    Cell cell = row.getCell(colIndex);
                    if (cell != null) {
                        String headerName = headerRow.getCell(colIndex).getStringCellValue(); // 헤더 이름
                        Object value = getCellValue(cell); // 셀 값 추출
                        record.put(headerName, value); // 맵에 추가
                    }
                }
                records.add(record); // 레코드 리스트에 추가
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    private Object getCellValue(Cell cell) {
        // 셀 타입에 따른 값 처리
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() : cell.getNumericCellValue();
            case BOOLEAN: return cell.getBooleanCellValue();
            case FORMULA: return cell.getCellFormula();
            default: return null;
        }
    }
}
