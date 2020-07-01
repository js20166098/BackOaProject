package com.js.common.util.excel;

import com.js.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 姜爽
 * @date: 2019/12/12 14:37
 * @description: excel内容导入，支持xls和xlsx文件
 */
@Slf4j
public class ExcelImport {

    /**
     * 获取excel文件的内容，以list形式返回，
     *
     * @param dir
     * @param sheetNum
     * @return List<List < String>>  List是每一行的作息，里面List是对应各列的内容
     * @throws FileNotFoundException
     */
    public static List<List<String>> getList(String dir, int sheetNum) throws FileNotFoundException {
        return getList(new File(dir), sheetNum);
    }

    /**
     * 获取excel文件的内容，以list形式返回，
     *
     * @param file
     * @param sheetNum
     * @return List<List < String>>  List是每一行的作息，里面List是对应各列的内容
     * @throws FileNotFoundException
     */
    public static List<List<String>> getList(File file, int sheetNum) throws FileNotFoundException {
        Workbook wb = ExcelUtil.getWorkbook(file);
        return getList(wb.getSheetAt(sheetNum));
    }

    /**
     * 获取excel文件的某一行的内容，以list形式返回，rowNum从1开始,如果小于或者等于0返回null,没有获取到内容也返回null
     *
     * @param dir
     * @param sheetNum
     * @param rowNum
     * @return List<String>  List是获得的一行的信息
     * @throws FileNotFoundException
     */
    public static List<String> getList(String dir, int sheetNum, int rowNum) throws FileNotFoundException {
        return getList(new File(dir), sheetNum, rowNum);
    }

    /**
     * 获取excel文件的某一行的内容，以list形式返回，rowNum从1开始,如果小于或者等于0返回null,没有获取到内容也返回null
     *
     * @param file
     * @param sheetNum
     * @param rowNum
     * @return List<String>  List是获得的一行的信息
     * @throws FileNotFoundException
     */
    public static List<String> getList(File file, int sheetNum, int rowNum) throws FileNotFoundException {
        Workbook wb = ExcelUtil.getWorkbook(file);
        return getList(wb.getSheetAt(sheetNum), rowNum);
    }

    /**
     * 读取一个sheet中的所有单元格内容
     *
     * @param sheet
     * @return
     */
    public static List<List<String>> getList(Sheet sheet) {
        return priGetList(sheet, 0, true);
    }

    /**
     *
     **/
    public static List<List<String>> getListSimple(Sheet sheet) {
        return priGetList(sheet, 0, false);
    }

    /**
     * 读取一个sheet中一行单元格的内容
     *
     * @param sheet
     * @param rowNum
     * @return
     */
    public static List<String> getList(Sheet sheet, int rowNum) {
        List<List<String>> list = priGetList(sheet, rowNum, true);
        return (list != null && list.size() == 1) ? list.get(0) : null;
    }

    /**
     *
     **/
    private static List<List<String>> priGetList(Sheet sheet, int rowNum, boolean normal) {
        if (sheet == null) {
            return null;
        }
        List<List<String>> list = new ArrayList<>();
        // 获取表内的所有内容
        int maxcellnum = 0;
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row != null && row.getLastCellNum() > maxcellnum) {
                maxcellnum = row.getLastCellNum();
            }
        }
        if (rowNum <= 0) {
            int rownum = sheet.getPhysicalNumberOfRows();
            if (sheet.getLastRowNum() > rownum) {
                rownum = sheet.getLastRowNum();
            }
            for (int i = 0; i <= rownum; i++) {
                list.add(getRowMap(sheet, i, maxcellnum, normal));
            }
        } else {
            list.add(getRowMap(sheet, rowNum - 1, maxcellnum, normal));
        }
        if (!list.isEmpty() && list.get(list.size() - 1) == null) {
            list.remove(list.size() - 1);
        }
        return list;
    }

    /**
     * 根据sheet获取一行的内容，以List形式返回，行号从0开始
     **/
    private static List<String> getRowMap(Sheet sheet, int rowNum, int maxcellnum, boolean normal) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return null;
        } else {
            List<String> rowList = new ArrayList<>();
            for (int j = 0; j < maxcellnum; j++) {
                Cell cell = row.getCell(j);
                rowList.add(getValue(cell, normal));
            }
            return rowList;
        }
    }

    /**
     *
     **/
    public static String getValue(Cell cell, boolean normal) {
        if (cell == null) {
            return null;
        } else {
            if (normal) {
                CellStyle cellStyle = cell.getCellStyle();
                try {
                    String str = null;
                    String dateF = "####\\/##\\/##";
                    String celltypename = cell.getCellTypeEnum().name();
                    if (CellType.STRING.name().equals(celltypename)) {
                        str = cell.getStringCellValue();
                    } else if (CellType.NUMERIC.name().equals(celltypename)) {
                        if (cellStyle.getDataFormatString().equals(dateF)) {
                            double dTemp = Double.parseDouble(cell.toString());
                            Integer iTemp = (int) dTemp;
                            String sTemp = iTemp.toString();

                            String month = "";
                            if ("0".equals(sTemp.substring(4, 5))) {
                                month = sTemp.substring(5, 6);
                            } else {
                                month = sTemp.substring(4, 6);
                            }
                            str = sTemp.substring(0, 4) + "-" + month + "-" + sTemp.substring(6);
                        } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date dTemp = cell.getDateCellValue();
                            String format = cellStyle.getDataFormatString();
                            if ("m/d/yy".equals(format)) {
                                str = DateUtil.dateToStringByPattern(dTemp,"yyyy-MM-dd");
                            } else {
                                str = DateUtil.dateToStringByPattern(dTemp,"yyyy-MM-dd HH:mm:ss");
                            }
                        } else {
                            // 把科学计算法去掉
                            Double dTemp = cell.getNumericCellValue();
                            str = dTemp.toString();
                        }
                    } else if (CellType.FORMULA.name().equals(celltypename)) {
                        str = String.valueOf(cell.getNumericCellValue());
                        if (str.indexOf(".") > -1) {
                            if ("".equals(str.substring(str.indexOf(".") + 1).replaceAll("0", ""))) {
                                str = str.substring(0, str.indexOf("."));
                            }
                        }
                    } else if (CellType.BLANK.name().equals(celltypename)) {
                        str = "";
                    } else if (CellType.BOOLEAN.name().equals(celltypename)) {
                        str = String.valueOf(cell.getBooleanCellValue());
                    }
                    return str;
                } catch (Exception e) {
                    log.info("系统出现异常{}",e);
                    return null;
                }
            } else {
                cell.setCellType(CellType.STRING);
                return cell.getStringCellValue();
            }
        }
    }
}
