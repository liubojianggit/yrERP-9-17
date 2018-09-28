package com.yr.order.service.impl;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.yr.depot.dao.WareDao;
import com.yr.depot.dao.impl.WareDaoImpl;
import com.yr.depot.service.WareService;
import com.yr.entitys.depot.Ware;
import com.yr.entitys.order.SaleOrder;
import com.yr.util.JsonDateValueProcessor;
import com.yr.util.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.entitys.bo.orderBO.SaleOrderBO;
import com.yr.entitys.page.Page;
import com.yr.order.dao.SaleDao;
import com.yr.order.service.SaleService;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleDao saleDao;

    @Autowired
    private WareService ws;//注入商品service的接口

    /**
     * 以分页的形式查询sale表的数据
     * @param page
     */
    @Override
    public String query(Page<SaleOrderBO> page) {
       /* Double wareCount = 0D;// Double类型，后面加D辨认类型
        List<Ware> listWare = ws.getWare();//获取商品单价
        for (Ware ware : listWare) {
            wareCount = ware.getOutUnitPrice();
        } // 单个商品单价*/
        page.setTotalRecord(saleDao.getCount(page));//查询总条数加入page中
        List<SaleOrderBO>list = saleDao.query(page);//分页查询的数据
        String jsonStr = JsonUtils.listToJson(list);
        String json = "{\"code\": 0,\"msg\": \"\",\"count\": "+page.getTotalRecord()+",\"data\":"+jsonStr+"}";
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
    public SaleOrderBO getById(Integer id) {
        SaleOrder saleOrder = saleDao.getById(id);
        SaleOrderBO saleOrderBO = new SaleOrderBO();
        saleOrderBO.setSaleOrder(saleOrder);
        return saleOrderBO;
    }

}
