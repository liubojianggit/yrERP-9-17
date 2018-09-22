package com.yr.util;

import com.yr.order.util.DateUtils;
import com.yr.util.ExcelBean;
import net.stxy.one.poi.ExcelBean;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类
 * Created by ASUS on 2018/5/20
 *
 * @Authod Grey Wolf
 */
public class ExcelUtil {

    private static XSSFCellStyle fontStyle;
    private static XSSFCellStyle fontStyle2;

    /**
     * 导出Excel表开始
     * @param sheetName 工作簿名称
     * @param clazz     数据源model类型
     * @param objs      excel标题列以及对应model字段名
     * @param map       标题列行数以及cell字体样式
     */
    public static XSSFWorkbook createExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map, String sheetName) throws Exception{

        // 创建新的Excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;

        //创建标题（头）
        createTableHeader(sheet, map);
        //创建内容
        createTableRows(sheet, map, objs, clazz);
        return workbook;
    }



    /**
     * 根据ExcelMapping 生成列头（多行列头）
     *
     * @param sheet 工作簿
     * @param map   每行每个单元格对应的列头信息
     */
    public static final void createTableHeader(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) { //cell起始位置
        int startIndex = 0;
        //cell终止位置
        int endIndex = 0;
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) { //合并单元格
                if (excels.get(x).getCols() > 1) {
                    if (x == 0) {
                        endIndex += excels.get(x).getCols() - 1;
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    } else {
                        endIndex += excels.get(x).getCols();
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    }
                    XSSFCell cell = row.createCell(startIndex - excels.get(x).getCols());
                    // 设置内容
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) { // 设置格式
                        cell.setCellStyle(excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle(fontStyle);
                } else {
                    XSSFCell cell = row.createCell(x);
                    // 设置内容
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) { // 设置格式
                        cell.setCellStyle(excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle(fontStyle);
                }
            }
        }
    }

    public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException,
            ClassNotFoundException, ParseException {
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<>();
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        List<Integer> widths = new ArrayList<Integer>(ems.size());
        for (Object obj : objs) {
            XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = (ExcelBean) ems.get(i);
                // 获得get方法
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // 如果是日期类型进行转换
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        value = DateUtils.dateToStr((Date) rtn,"yyyy-MM-dd");

                    } else if (rtn instanceof BigDecimal) {
                        NumberFormat nf = new DecimalFormat("#,##0.00");
                        value = nf.format((BigDecimal) rtn).toString();
                    } else if ((rtn instanceof Integer) && (Integer.valueOf(rtn.toString()) < 0)) {
                        value = "--";
                    } else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(fontStyle2);
                // 获得最大列宽
                int width = value.getBytes().length * 300;
                // 还未设置，设置当前
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                } // 比原来大，更新数据
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            rowindex++;
        } // 设置列宽
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }
}
