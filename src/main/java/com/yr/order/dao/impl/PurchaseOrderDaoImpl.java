package com.yr.order.dao.impl;

import com.yr.entitys.bo.orderBO.PurchaseOrderBo;
import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.page.Page;
import com.yr.order.dao.PurchaseOrderDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PurchaseOrderDaoImpl implements PurchaseOrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PurchaseOrderBo> query(Page<PurchaseOrderBo> page) {
        String jpql="select r from PurchaseOrder r where 1=1 ";
        if(page.getT().getPurchaseName()!=null && !page.getT().getPurchaseName().equals(""))
        {
            jpql+="and r.purc_ware_name like :name ";
        }
        if(page.getT().getPurchaseType()!=null && !page.getT().getPurchaseType().equals(""))
        {
            jpql+="and r.purc_ware_type like :type ";
        }
        Query query =  entityManager.createQuery(jpql);
        if(page.getT().getPurchaseName()!=null && !page.getT().getPurchaseName().equals(""))
        {
            query.setParameter("name","%"+page.getT().getPurchaseName()+"%");
        }
        if(page.getT().getPurchaseType()!=null && !page.getT().getPurchaseType().equals(""))
        {
            query.setParameter("type","%"+page.getT().getPurchaseType()+"%");
        }

        //query.setFirstResult((page.getStart()-1) * page.getPageSize()).setMaxResults(page.getPageSize());//查询分页
        query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());//查询分页
        List<PurchaseOrderBo> list = query.getResultList();
        return list;
    }

    @Override
    public List<PurchaseOrder> queryForList() {
        String jpql ="select r from PurchaseOrder r";
        List<PurchaseOrder> list =  entityManager.createQuery(jpql).getResultList();
        return list;
    }
    @Override
    public Long getCount(PurchaseOrderBo purchaseOrderBO) {
        String jpql= "select count(*) from PurchaseOrder r where 1=1 ";
        if(purchaseOrderBO.getPurchaseName()!=null && !purchaseOrderBO.getPurchaseName().equals(""))
        {
            jpql+="and r.purc_ware_name like :name ";
        }
        if(purchaseOrderBO.getPurchaseType()!=null && !purchaseOrderBO.getPurchaseType().equals(""))
        {
            jpql+="and r.purc_ware_type like :type ";
        }
        Query query =  entityManager.createQuery(jpql);
        if(purchaseOrderBO.getPurchaseName()!=null && !purchaseOrderBO.getPurchaseName().equals(""))
        {
            query.setParameter("name","%"+purchaseOrderBO.getPurchaseName()+"%");
        }
        if(purchaseOrderBO.getPurchaseType()!=null && !purchaseOrderBO.getPurchaseType().equals(""))
        {
            query.setParameter("type","%"+purchaseOrderBO.getPurchaseType()+"%");
        }
        Long value = (Long) query.getSingleResult();

        return value;
    }

    @Override
    public PurchaseOrder getRequisitionById(Integer id) {
       /* String jpql="select r from PurchaseOrder r where r.id=:id";
        Query query =  entityManager.createQuery(jpql).setParameter("id",id);
        Requisition requisition = (Requisition) query.getSingleResult();*/

        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class,id);
        return purchaseOrder;
    }

    @Override
    public void add(PurchaseOrder purchaseOrder) {
        //添加数据
        entityManager.persist(purchaseOrder);
    }

    @Override
    public void update(PurchaseOrder purchaseOrder) {
       /* String jpql="update Requisition r set r.job_num = :jobNumber,r.depa_copde = :deparCode,r.approver = :approver,r.purc_ware_name = :name,r.purc_ware_type = :purcType," +
                "r.purc_ware_num = :number,r.supp_code = :supplireCode,r.unit_price = :unitPrice,r.total_price = :totalPrice,r.status =: status," +
                "r.consignee =: consignee, r.depot_code =: depotCode";

        entityManager.createQuery(jpql).setParameter("jobNumber",requisition.getJobNumber())
                .setParameter("deparCode",requisition.getDepartmentCode())
                .setParameter("approver",requisition.getApprover())
                .setParameter("name",requisition.getPurchasName())
                .setParameter("purcType",requisition.getPurchaseType())
                .setParameter("number",requisition.getPurchaseNumber())
                .setParameter("supplireCode",requisition.getSupplierCode())
                .setParameter("unitPrice",requisition.getUnitPrice())
                .setParameter("totalPrice",requisition.getTotalPrice())
                .setParameter("status",requisition.getStatus())
                .setParameter("consignee",requisition.getConsignee())
                .setParameter("depotCode",requisition.getDepotCode())
                .executeUpdate();*/
        //修改
        entityManager.merge(purchaseOrder);

    }

    @Override
    public void delete(Integer id) {
        /*String jpql = "delete from Requisition u where u.id = :id";
        entityManager.createQuery(jpql).setParameter("id",id).executeUpdate();*/

        PurchaseOrder purchaseOrder =  entityManager.find(PurchaseOrder.class,id);
        entityManager.remove(purchaseOrder);

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        /*String jpql ="delete from Requisition u where u.id in (?1)";
        entityManager.createQuery(jpql).setParameter(1,ids).executeUpdate();*/
        for (Integer id :ids) {
            PurchaseOrder req =  entityManager.find(PurchaseOrder.class,id);
            entityManager.remove(req);
        }

    }
}
