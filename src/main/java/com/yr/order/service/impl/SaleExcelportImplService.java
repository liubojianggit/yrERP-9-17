package com.yr.order.service.impl;

import java.util.List;

import com.yr.entitys.bo.orderBO.SaleImportExcelBO;
import com.yr.entitys.order.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.order.dao.SaleExportDao;
import com.yr.order.service.SaleExcelportService;
@Service
@Transactional
public class SaleExcelportImplService implements SaleExcelportService{
	@Autowired
	private SaleExportDao saleExportDao;


	/**
	 * Excel批量操作导入的销售订单
	 * @param filePath
	 * @return
	 */
	@Override
	public boolean addExcel(String filePath) {


		int result = 0;
		//解析xml文件
		SaleImportExcelBO readExcel=new SaleImportExcelBO();
		List<SaleOrder> saleOrderList = readExcel.getExcelInfo(filePath);
		result = saleExportDao.addExcel(saleOrderList);
		if(result > 0){
			return true;
		}
		return false;
	}

	/**
     * 销售订单表的Excel导出
     */
	@Override
	public List<SaleOrder> queryForList() {
		return saleExportDao.queryForList();
	}
    
    
    
	
}
