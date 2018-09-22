package com.yr.order.dao;

import com.yr.entitys.order.Requisition;
import com.yr.util.ExcelBean;

import java.util.List;
import java.util.Map;

public interface ExcelDao {
    /**
     * 查询数据库，并以List集合的方式返回回来
     * @return
     */
    List<Requisition> queryForList();

    /**
     * 获取字段名称
     * @return
     */
   // List<String> getHeadName();

    /**
     * Excel表格对应的表头信息处理类
     * @return
     */
    public Map<Integer, List<ExcelBean>> contentExcel();
}
