package com.yr.depot.service.impl;

import com.yr.depot.dao.DepotDao;
import com.yr.depot.service.DepotService;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import com.yr.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("depotServiceImpl")
@Transactional
public class DepotServiceImpl implements DepotService {
    @Qualifier("depotDaoImpl")
    @Autowired
    private DepotDao depotDao;

    /**
     * 分页查询仓库
     * @param page
     * @return
     */
    @Override
    public Page<Depotbo> query(Page<Depotbo> page) {
       page.setTotalRecord(depotDao.getCount((Depotbo)page.getT()));
        List<Depotbo> list=depotDao.query(page);
        page.setPageData(list);
        return page;
    }
    /**
     * 根据id查询仓库数据
     * @param id
     * @return
     */
    @Override
    public Depot getById(Integer id) {

        return depotDao.getById(id);
    }
    /**
     * 为仓库添加数据
     * @param depot
     */
    @Override
    public void add(Depot depot) {
        depotDao.add(depot);
    }
    /**
     * 根据对象修改仓库数据
     * @param depot
     */
    @Override
    public void update(Depot depot) {
        depotDao.update(depot);
    }

    /**
     * 根据id删除仓库数据
     * @param id
     */
    @Override
    public void delete(Integer id) {
        depotDao.delete(id);
    }

    /**
     * 判断添加数据时是否为空值，如果是空的就放回false,不是空就放回true
     * @param depot
     * @return
     */
    @Override
    public boolean isNullAdd(Depot depot) {
        if(StringUtils.isNull(depot.getCode())){
            return false;
        }if (StringUtils.isNull(depot.getName())) {
            return false;
        }if(StringUtils.isNull(depot.getAddr())){
            return false;
        }if (StringUtils.isNull(depot.getCreateEmpno())){
            return false;
        }
        return true;
    }
    /**
     * 判断修改数据时是否为空值，如果是空的就放回false,不是空就放回true
     * @param depot
     * @return
     */
    @Override
    public boolean isNullUpdate(Depot depot) {
        if (StringUtils.isNull(depot.getName())) {
            return false;
        }if(StringUtils.isNull(depot.getAddr())){
            return false;
        }if (StringUtils.isNull(depot.getUpdateEmpno())){
            return false;
        }
        return true;
    }
}
