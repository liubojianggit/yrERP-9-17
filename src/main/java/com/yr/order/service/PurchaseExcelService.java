package com.yr.order.service;

import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.order.SaleOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PurchaseExcelService {
    /**
     * 查询数据库所有数据信息用于导出Excel表格
     * @return list
     */
    List<PurchaseOrder> queryForList();

    /**
     * 导入excel表
     * @param filePath
     * @return
     */
    //boolean batchImport(String name, MultipartFile file);
    public boolean addExcel(String filePath);
}
