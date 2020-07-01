package com.js.common.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 姜爽
 * @date: 2019/12/12 14:37
 * @description: Excel基础操作
 */
@Slf4j
public class ExcelUtil {

    private static final String NULL = "null";
    /**
     * 将传入的数据导出excel表并下载
     * @param response 返回的HttpServletResponse
     * @param importlist 要导出的对象的集合
     * @param attributeNames 含有每个对象属性在excel表中对应的标题字符串的数组（请按对象中属性排序调整字符串在数组中的位置）
     */
    public static void export(HttpServletResponse response, List<?> importlist, String[] attributeNames, String fileName) {
        //获取数据集
        List<?> datalist = importlist;

        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        //设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);

        //获取字段名数组
        String[] tableAttributeName = attributeNames;
        //获取对象属性
        Field[] fields = ClassUtil.getClassAttribute(importlist.get(0));
        //获取对象get方法
        List<Method> methodList = ClassUtil.getMethodGet(importlist.get(0));

        //循环字段名数组，创建标题行
        Row row = sheet.createRow(0);
        for (int j = 0; j< tableAttributeName.length; j++){
            //创建列
            Cell cell = row.createCell(j);
            //设置单元类型为String
            cell.setCellType(CellType.STRING);
            cell.setCellValue(transCellType(tableAttributeName[j]));
        }
        //创建普通行
        for (int i = 0;i<datalist.size();i++){
            //因为第一行已经用于创建标题行，故从第二行开始创建
            row = sheet.createRow(i+1);
            //如果是第一行就让其为标题行
            Object targetObj = datalist.get(i);
            for (int j = 0;j<fields.length;j++){
                //创建列
                Cell cell = row.createCell(j);
                cell.setCellType(CellType.STRING);
                //
                try {
                    Object value = methodList.get(j).invoke(targetObj, new Object[]{});
                    cell.setCellValue(transCellType(value));
                } catch (Exception e) {
                    log.info("运行出现的异常为{}",e);
                }
            }
        }
        response.setContentType("application/octet-stream");
        //默认Excel名称
        response.setHeader("Content-Disposition", "attachment;fileName="+ fileName );

        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("系统出现异常为{}",e);
        }

    }

    private static String transCellType(Object value){
        String str = null;
        if (value instanceof Date){
            str = String.valueOf(value);
        }else{
            str = String.valueOf(value);
            if (str == null){
                str = "";
            }
        }
        return str;
    }

    /**
     *
     **/
    public static Workbook getWorkbook(String excelFilePath) throws FileNotFoundException {
        return getWorkbook(new File(excelFilePath));
    }

    /**
     * 从流中读取工作表，并且不会关闭该流，调用者需要自行处理流的关闭等操作
     * 03及以下返回HSSFWorkbook，07版及以上返回XSSFWorkbook
     *
     * @param is
     * @return
     */
    public static Workbook getWorkbook(InputStream is) {
        try {
            return WorkbookFactory.create(is);
        } catch (Exception e) {
            log.info("系统出现异常为{}",e);
            return null;
        }
    }

    /**
     *
     **/
    public static Workbook getWorkbook(File excelFile) throws FileNotFoundException {
        InputStream is = new FileInputStream(excelFile);
        Workbook wb = getWorkbook(is);
        IOUtils.closeQuietly(is);
        return wb;
    }

    /**
     * 获取一个excel中的所有sheet
     *
     * @param wb
     * @return
     */
    public static List<Sheet> getSheets(Workbook wb) {
        List<Sheet> list = new ArrayList<>();
        int num = wb.getNumberOfSheets();
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                list.add(wb.getSheetAt(i));
            }
        }
        return list;
    }

    /**
     * 获取一个excel中的一个sheet
     *
     * @param wb
     * @param index
     * @return org.apache.poi.ss.usermodel.Sheet
     * @method getSheet
     * @author Gavin Gao
     * @date 2018/11/2 16:53
     */
    public static Sheet getSheet(Workbook wb, int index) {
        return wb.getSheetAt(index);
    }

}
