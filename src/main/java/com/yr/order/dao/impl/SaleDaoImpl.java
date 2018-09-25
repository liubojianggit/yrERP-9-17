package com.yr.order.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.yr.entitys.order.SaleOrder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.yr.entitys.bo.orderBO.SaleBO;
import com.yr.entitys.page.Page;
import com.yr.order.dao.SaleDao;
@Repository
public class SaleDaoImpl implements SaleDao {

    //获取到和当前事务关联的 EntityManager对象,通过 @PersistenceContext 注解来标记成员变量
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 查询总条数
     * @param page
     * @return
     */
    @Override
    public Long getCount(Page<SaleBO> page) {
        String jpql="select count(*) from SaleOrder s where 1=1";
        if(!StringUtils.isEmpty(page.getT().getSaleOrder().getCode())){//判断是否为空
            jpql +="and s.code like :code";//模糊查询编号
        }
        if (!StringUtils.isEmpty(page.getT().getSaleOrder().getCustomerBuy())){
            jpql +="and s.customerBuy like :customer_buy";//模糊查询购买客户
        }
        Query query =entityManager.createQuery(jpql);
        if (!StringUtils.isEmpty(page.getT().getSaleOrder().getCode())){
            query.setParameter("code","%"+page.getT().getSaleOrder().getCode()+"%");
        }
        if (!StringUtils.isEmpty(page.getT().getSaleOrder().getCustomerBuy())){
            query.setParameter("customer_buy","%"+page.getT().getSaleOrder().getCustomerBuy()+"%");
        }
        Long count = (Long) query.getSingleResult();
        return (long) count.intValue();//将long转为int
    }

    /**
     * 以分页的形式查询sale表的数据
     * @param page
     * @return
     */
    @Override
    public List<SaleBO> query(Page<SaleBO> page) {
        String jpql = "select s from SaleOrder s where 1=1";
        if (!StringUtils.isEmpty(page.getT().getSaleOrder().getCode())){//判断是否为null
            jpql +="and s.code like :code";
        }
        if (!StringUtils.isEmpty(page.getT().getSaleOrder().getCustomerBuy())){
            jpql +="and s.customerBuy like :customer_buy";
        }
       /* if (page.getSort() == "ASC") {//升序
            jpql +="order by s.id asc";
        }
        if (page.getSort() == "DESC"){//降序
            jpql +="order by s.id desc";
        }*/
        Query query = entityManager.createQuery(jpql);
        if (!StringUtils.isEmpty(page.getT().getSaleOrder().getCode())){
            query.setParameter("code","%"+page.getT().getSaleOrder().getCode()+"%");
        }
        if (!StringUtils.isEmpty(page.getT().getSaleOrder().getCustomerBuy())){
            query.setParameter("customer_buy","%"+page.getT().getSaleOrder().getCustomerBuy()+"%");
        }
        query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());//查询分页
        List<SaleBO> list = query.getResultList();
        return list;
    }

    @Override
    public void add(SaleOrder saleOrder) {
       /* String jpql = "insert into sale_order(code,customer_buy,salesperson,ware_code,number,money,s_phoneNumber,remark,states)values(?1,?2,?3,?4,?5,?6,?7,?8,?9)";
        Query query = entityManager.createNativeQuery(jpql).setParameter(1,sale.getCode()).setParameter(2,sale.getCustomerBuy()).setParameter(3,sale.getSalesperson())
                .setParameter(4,sale.getWareCode()).setParameter(5,sale.getNumber()).setParameter(6,sale.getMoney()).setParameter(7,sale.getsPhoneNumber())
                .setParameter(8,sale.getRemark()).setParameter(9,sale.getStates());
        query.executeUpdate();*/
        entityManager.persist(saleOrder);
    }

    @Override
    public void update(SaleOrder saleOrder) {
       /* String jpql = "update Sale s set s.code=?1,s.customer_buy=?2,s.salesperson=?3,s.ware_code=?4,s.number=?5,s.money=?6,s.s_phoneNumber=?7," +
                "s.remark=?8,s.states=?9 where s.id=?10";
        Query query = entityManager.createNativeQuery(jpql).setParameter(1,sale.getCode()).setParameter(2,sale.getCustomerBuy()).setParameter(3,sale.getSalesperson())
                .setParameter(4,sale.getWareCode()).setParameter(5,sale.getNumber()).setParameter(6,sale.getMoney()).setParameter(7,sale.getsPhoneNumber())
                .setParameter(8,sale.getRemark()).setParameter(9,sale.getStates()).setParameter(10,sale.getId());
        query.executeUpdate();*/
        entityManager.merge(saleOrder);

    }

    /**
     * 根据id删除销售数据
     * @param id
     */
    @Override
    public void delete(Integer id) {
        /*String jpql = "delete from Sale s where s.id = ?1";
        Query query = entityManager.createQuery(jpql).setParameter(1, id);
        query.executeUpdate();*/
        SaleOrder saleOrder = entityManager.find(SaleOrder.class,id);
        entityManager.remove(saleOrder);
    }

    /**
     * 根据id查询销售数据
     * @param id
     * @return
     */
    @Override
    public SaleOrder getById(Integer id) {
      /*  String jpql ="select s from Sale s where s.id = ?1";
        Sale sale = (Sale)entityManager.createQuery(jpql).setParameter(1,id).getSingleResult();*/
        SaleOrder saleOrder = entityManager.find(SaleOrder.class,id);
        return saleOrder;
    }





}
