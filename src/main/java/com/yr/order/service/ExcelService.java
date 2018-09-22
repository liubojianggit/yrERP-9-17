package com.yr.order.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.InvocationTargetException;

public interface ExcelService {
    /**
     * 导出Excel表格
     * @param headTextName
     * @return
     * @throws Exception
     */
    XSSFWorkbook exportExcelInfo(String headTextName) throws Exception;
}
