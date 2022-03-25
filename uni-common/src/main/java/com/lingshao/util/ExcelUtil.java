package com.lingshao.util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;


/**
 * @author LingShao
 * @description Excel工具类
 * @date 2021/4/8
 */
public class ExcelUtil {
    /**
     * @param filePath 文件路径
     * @description Excel内容读取，迭代器
     * @author LingShao
     * @date 2021/4/8
     */
    public static List<String[]> getDateOfExcel(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<String[]> cellList = new ArrayList<>();
        while (rows.hasNext()) {
            Row row = rows.next();
            String[] cellData = new String[row.getPhysicalNumberOfCells()];
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                Cell cell = row.getCell(i);
                cellData[i] = isBlank(cellToString(cell));
            }
            cellList.add(cellData);
        }
        inputStream.close();
        return cellList;
    }

    /**
     * @description 判断某一格是否为空
     * @author LingShao
     * @date 2021/4/8
     */
    public static boolean isCellEmpty(Cell cell) {
        if (cell == null || cell.getCellType() == _NONE || cell.getCellType() == BLANK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @description 判断Excel某行是不是空行
     * @author LingShao
     * @date 2021/4/8
     */
    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && (cell.getCellType() != _NONE || cell.getCellType() != BLANK)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param str 处理的字符
     * @return NULL or String
     * @description 处理数据，删除前后空格，若处理后为空返回EMP，不然返回处理的元素
     * @author LingShao
     * @date 2021/4/9
     */
    public static String isBlank(String str) {
        if (str.trim().equals("") || str.trim() == null) {
            return "EMP";
        } else {
            return str.trim();
        }
    }

    /**
     * @param cell 格子
     * @return String
     * @description 将格子中的数据类型全部转为String
     * @author LingShao
     * @date 2021/4/9
     */
    public static String cellToString(Cell cell) {
        String cellValue = null;
        if (cell == null || cell.getCellType() == null || cell.getCellType().equals(BLANK) || cell.getCellType().equals(_NONE)) {
            cellValue = "EMP";
        }
        if (cell.getCellType().equals(STRING)) {
            cellValue = cell.getStringCellValue().trim();
        }
        if (cell.getCellType().equals(NUMERIC)) {
            cellValue = String.valueOf((long) cell.getNumericCellValue()).trim();
        }
        if (cell.getCellType().equals(BOOLEAN)) {
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
        }
        if (cell.getCellType().equals(ERROR)) {
            cellValue = String.valueOf(cell.getErrorCellValue()).trim();
        }
        return cellValue;
    }
}
