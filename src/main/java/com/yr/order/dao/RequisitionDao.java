package com.yr.order.dao;

import com.yr.entitys.bo.orderBO.RequisitionBO;
import com.yr.entitys.page.Page;
import com.yr.entitys.order.Requisition;

import java.util.List;

public interface RequisitionDao {
    /**
     * 分页查询
     * @param page
     * @return
     */
    List<Requisition> query(Page<RequisitionBO> page);

    /**
     * 查询数据库，并以List集合的方式返回回来
     * @return
     */
    List<Requisition> queryForList();

    /**
     * 根据采购表id 查询数据
     * @param id
     * @return
     */
    Requisition getRequisitionById(Integer id);

    /**
     * 查询数据库表总条数
     * @param requisitionBO
     * @return
     */
    Long getCount(RequisitionBO requisitionBO);

    /**
     * 添加
     * @param requisition
     */
    void  add(Requisition requisition);

    /**
     * 修改
     * @param requisition
     */
    void  update(Requisition requisition);

    /**
     * 删除
     * @param id
     */
    void  delete(Integer id);

    /**
     * 根据选中的id,批量删除
     * @param ids
     */
    void deleteBatch(List<Integer> ids);
    //void deleteBatch(int [] ids);
}
