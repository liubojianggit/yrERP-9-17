package com.yr.log.dao.impl;

import com.yr.entitys.Log.Log;
import com.yr.entitys.bo.LogBO.LogBo;
import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.page.Page;
import com.yr.log.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LogDaoImpl implements LogDao {

    @Autowired
    private EntityManager entityManager;

    /**
     * 添加日志记录
     * @param log
     */
    @Override
    public void add(Log log) {
        entityManager.persist(log);

        /*String jpql="insert into log(count,fieldNewValue,fieldOldValue,modular,table,type,createTime,createEmp,updateTime,updateEmp) " +
                "value(?,?,?,?,?,?,?,?,?,?)";
        entityManager.createQuery(jpql).setParameter(1,log.getContent())
                .setParameter(2,log.getFieldNewValue())
                .setParameter(3,log.getFieldOldValue())
                .setParameter(4,log.getModular())
                .setParameter(5,log.getTable())
                .setParameter(6,log.getType())
                .setParameter(7,log.getCreateTime())
                .setParameter(8,log.getCreateEmp())
                .setParameter(9,log.getUpdateTime())
                .setParameter(10,log.getUpdateEmp())
                .executeUpdate();*/
    }

    /**
     * 查询日志记录；
     * @param logBoPage
     * @return
     */
    @Override
    public List<Log> query(Page<LogBo> logBoPage) {
        String jpql = "select o from Log o where 1 = 1 ";
        Query query = entityManager.createQuery(jpql);
        //查询分页
        query.setFirstResult((logBoPage.getStart()-1) * logBoPage.getPageSize()).setMaxResults(logBoPage.getPageSize());
        //获得分页后的数据集合
        List<Log> list = query.getResultList();
        return list;
    }
}
