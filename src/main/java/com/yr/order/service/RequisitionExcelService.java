package com.yr.order.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;

public interface RequisitionExcelService {
    /**
     * 导出Excel表格
     * @param headTextName
     * @return
     * @throws Exception
     */
    XSSFWorkbook exportExcelInfo(String headTextName) throws Exception;

    /**
     * 导入excel表格
     * @param name
     * @param file
     * @return
     */
    boolean batchImport(String name,MultipartFile file);
}
