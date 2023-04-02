package com.xzcoder.kaoshi.common.utils;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xzcoder.kaoshi.common.po.ColumnInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * ExportExcelUtils
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ExportExcelUtils {

    @SuppressWarnings("all")
    public static void writeExcelFromList(String title, List<ColumnInfo> columns, List<Object> list, HttpServletResponse response) throws Exception {
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet();
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue(title);
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平   
        /**字体begin*/
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 14);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体增粗
        // 把字体应用到当前的样式
        style.setFont(font);
        cell.setCellStyle(style);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        //在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
        //创建单元格并设置单元格内容
        int cellIndex = 0;
        for (ColumnInfo columnInfo : columns) {
            row2.createCell(cellIndex).setCellValue(columnInfo.getColumn());
            cellIndex++;
        }

        int rowIndex = 2;

        for (Object data : list) {
            HSSFRow row3 = sheet.createRow(rowIndex);
            cellIndex = 0;
            for (ColumnInfo columnInfo : columns) {
                String field = columnInfo.getField();
                String methodName = "get" + toUpperCaseFirstOne(field);
                Method method = data.getClass().getDeclaredMethod(methodName, null);
                String fieldValue = (String) method.invoke(data, null);
                row3.createCell(cellIndex).setCellValue(fieldValue);
                cellIndex++;
            }
            rowIndex++;
        }

        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=" + CommonUtils.toUtf8String(title) + ".xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    //首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
