package com.yr.order.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yr.entitys.order.Sale;

public interface SaleExcelportService {
	
	/**
	 * 导出Excel表格
	 * @return
	 */
	 List<Sale> queryForList();
	/**
	 * 导入Excel表格
	 * @param name
	 * @param file
	 * @return
	 */

	  boolean batchImport(String name,MultipartFile file);
}
