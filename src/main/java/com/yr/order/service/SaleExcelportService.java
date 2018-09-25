package com.yr.order.service;

import java.util.List;

import com.yr.entitys.order.SaleOrder;
import org.springframework.web.multipart.MultipartFile;


public interface SaleExcelportService {
	
	/**
	 * 导出Excel表格
	 * @return
	 */
	 List<SaleOrder> queryForList();
	/**
	 * 导入Excel表格
	 * @param name
	 * @param file
	 * @return
	 */

	  boolean batchImport(String name,MultipartFile file);
}
