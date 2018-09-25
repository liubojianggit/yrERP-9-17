package com.yr.order.service.impl;

import java.util.List;

import com.yr.entitys.bo.orderBO.SaleImportExcelBO;
import com.yr.entitys.order.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yr.order.dao.SaleDao;
import com.yr.order.dao.SaleExportDao;
import com.yr.order.service.SaleExcelportService;
@Service
@Transactional
public class SaleExcelportImplService implements SaleExcelportService{
	@Autowired
	private SaleExportDao saleExportDao;
	
	@Autowired
	private SaleDao saleDao;
	
	  /**
     * Excel批量操作导入的销售订单
     * @param name
     * @param file
     * @return
     */
    public boolean batchImport(String name,MultipartFile file){
        boolean b = false;
        //创建处理EXCEL
        SaleImportExcelBO readExcel=new SaleImportExcelBO();
        //解析excel，获取销售信息集合。
        List<SaleOrder> saleList = readExcel.getExcelInfo(name ,file);

        if(saleList != null){
            b = true;
        }

        //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
        for(SaleOrder sale:saleList){
        	saleDao.add(sale);
        }
        return b;
    }

    /**
     * 销售订单表的Excel导出
     */
	@Override
	public List<SaleOrder> queryForList() {
		return saleExportDao.queryForList();
	}
    
    
    
	
}
