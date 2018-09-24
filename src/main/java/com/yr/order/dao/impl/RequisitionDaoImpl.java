package com.yr.order.dao.impl;

import com.yr.entitys.bo.orderBO.RequisitionBO;
import com.yr.entitys.page.Page;
import com.yr.entitys.order.Requisition;
import com.yr.order.dao.RequisitionDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class RequisitionDaoImpl implements RequisitionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Requisition> query(Page<RequisitionBO> page) {
        String jpql="select r from Requisition r where 1=1 ";
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
        List<Requisition> list = query.getResultList();
        return list;
    }

    @Override
    public List<Requisition> queryForList() {
        String jpql ="select r from Requisition r";
        List<Requisition> list =  entityManager.createQuery(jpql).getResultList();
        return list;
    }
    @Override
    public Long getCount(RequisitionBO requisitionBO) {
        String jpql= "select count(*) from Requisition r where 1=1 ";
        if(requisitionBO.getPurchaseName()!=null && !requisitionBO.getPurchaseName().equals(""))
        {
            jpql+="and r.purc_ware_name like :name ";
        }
        if(requisitionBO.getPurchaseType()!=null && !requisitionBO.getPurchaseType().equals(""))
        {
            jpql+="and r.purc_ware_type like :type ";
        }
        Query query =  entityManager.createQuery(jpql);
        if(requisitionBO.getPurchaseName()!=null && !requisitionBO.getPurchaseName().equals(""))
        {
            query.setParameter("name","%"+requisitionBO.getPurchaseName()+"%");
        }
        if(requisitionBO.getPurchaseType()!=null && !requisitionBO.getPurchaseType().equals(""))
        {
            query.setParameter("type","%"+requisitionBO.getPurchaseType()+"%");
        }
        Long value = (Long) query.getSingleResult();

        return value;
    }

    @Override
    public Requisition getRequisitionById(Integer id) {
       /* String jpql="select r from Requisition r where r.id=:id";
        Query query =  entityManager.createQuery(jpql).setParameter("id",id);
        Requisition requisition = (Requisition) query.getSingleResult();*/

       Requisition requisition = entityManager.find(Requisition.class,id);
        return requisition;
    }

    @Override
    public void add(Requisition requisition) {
        //添加数据
        entityManager.persist(requisition);
    }

    @Override
    public void update(Requisition requisition) {
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
       entityManager.merge(requisition);

    }

    @Override
    public void delete(Integer id) {
        /*String jpql = "delete from Requisition u where u.id = :id";
        entityManager.createQuery(jpql).setParameter("id",id).executeUpdate();*/

        Requisition requisition =  entityManager.find(Requisition.class,id);
        entityManager.remove(requisition);

    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        /*String jpql ="delete from Requisition u where u.id in (?1)";
        entityManager.createQuery(jpql).setParameter(1,ids).executeUpdate();*/
        for (Integer id :ids) {
            Requisition req =  entityManager.find(Requisition.class,id);
            entityManager.remove(req);
        }

    }
}
