package com.yr.order.service.impl;

import com.yr.entitys.order.PurchaseOrder;
import com.yr.order.dao.PurchaseExcelDao;
import com.yr.order.service.PurchaseExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author
 * @since
 */
@Service
@Transactional
public class PurchaseExcelServiceImpl implements PurchaseExcelService {
    @Autowired
    private PurchaseExcelDao purchaseExcelDaoImpl;
    /**
     * 查询数据库所有数据信息用于导出Excel表格
     * @return list
     */
    @Override
    public List<PurchaseOrder> queryForList() {
        return purchaseExcelDaoImpl.queryForList();
    }

    @Override
    public boolean batchImport(String name, MultipartFile file) {
        return false;
    }
}
