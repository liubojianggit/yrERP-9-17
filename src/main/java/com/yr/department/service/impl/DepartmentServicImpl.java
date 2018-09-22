package com.yr.department.service.impl;

import com.yr.department.dao.DepartmentDao;
import com.yr.department.service.DepartmentService;
import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import com.yr.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("departmentSericeImpl")
@Transactional
public class DepartmentServicImpl implements DepartmentService {
    @Qualifier("departmentDaoImpl")
    @Autowired
    private DepartmentDao departmentDao;

    /**
     *分页查询部门
     * @param page
     * @return
     */
    @Override
    public Page<Departmentbo> query(Page<Departmentbo> page) {
        page.setTotalRecord(departmentDao.getCount((Departmentbo)page.getT()));
        List<Departmentbo> list=departmentDao.query(page);
        page.setPageData(list);
        return page;

    }

    /**
     *根据id查询部门表
     * @param id
     * @return
     */
    @Override
    public Department getById(Integer id) {
        return departmentDao.getById(id);
    }

    /**
     *添加部门
     * @param depart
     */
    @Override
    public void add(Department depart) {
        departmentDao.add(depart);
    }

    /**
     *修改部门表数据
     * @param depart
     */
    @Override
    public void update(Department depart) {
        departmentDao.update(depart);
    }

    /**
     *根据id删除部门表数据
     * @param id
     */
    @Override
    public void delete(Integer id) {
        departmentDao.delete(id);
    }

    /**
     *判断部门表数据时添加时是否为null,null返回false
     * @param depart
     * @return
     */
    @Override
    public boolean isNullAdd(Department depart) {
        if(StringUtils.isNull(depart.getCode())){
            return false;
        }if (StringUtils.isNull(depart.getName())) {
            return false;
        }if (StringUtils.isNull(depart.getSupCode())){
            return false;
        }if (StringUtils.isNull(depart.getCreateEmpno())){
            return false;
        }

        return true;
    }

    /**
     *判修改部门表数据时是否为null，null返回false
     * @param depart
     * @return
     */
    @Override
    public boolean isNullUpdate(Department depart) {
        if(StringUtils.isNull(depart.getCode())){
            return false;
        }if (StringUtils.isNull(depart.getName())) {
            return false;
        }if(StringUtils.isNull(depart.getSupCode())){
            return false;
        }
        if (StringUtils.isNull(depart.getUpdateEmpno())){
            return false;
        }
        return true;
    }

    /**
     * 跳转添加页面无数据
     * @return
     * 单独查询部门的编号，以供父级id选择需要返回一个list
     */
    @Override
    public List<Department> querycod() {

        return departmentDao.querycod();
    }

}
