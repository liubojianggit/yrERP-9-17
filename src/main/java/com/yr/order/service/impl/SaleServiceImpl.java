package com.yr.order.service.impl;

import java.util.Date;
import java.util.List;

import com.yr.entitys.bo.orderBO.SaleImportExcelBO;
import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.order.SaleOrder;
import com.yr.util.JsonDateValueProcessor;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yr.entitys.bo.orderBO.SaleBO;
import com.yr.entitys.page.Page;
import com.yr.order.dao.SaleDao;
import com.yr.order.service.SaleService;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleDao saleDao;



    /**
     * 以分页的形式查询sale表的数据
     * @param page
     */
    @Override
    public String query(Page<SaleBO> page) {
        page.setTotalRecord(saleDao.getCount(page));//查询总条数加入page中
        List<SaleBO>list = saleDao.query(page);//分页查询的数据
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        String json = "{\"code\": 0,\"msg\": \"\",\"count\": "+page.getTotalRecord()+",\"data\":"+jsonArray+"}";
        return  json;
    }

    @Override
    public void add(SaleOrder saleOrder) {
        saleDao.add(saleOrder);
    }

    @Override
    public void update(SaleOrder saleOrder) {
        saleDao.update(saleOrder);
    }

    /**
     * 根据id删除销售单的信息
     * @param id
     */
    @Override
    public void delete(Integer id) {
        saleDao.delete(id);
    }

    /**
     * 根据id查询销售单的数据
     * @param id
     * @return
     */
    @Override
    public SaleOrder getById(Integer id) {
        return saleDao.getById(id);
    }

}
