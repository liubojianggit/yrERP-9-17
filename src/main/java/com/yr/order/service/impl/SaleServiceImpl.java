package com.yr.order.service.impl;

import com.yr.entitys.bo.orderBO.SaleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.bo.orderBO.SaleImportExcel;
import com.yr.entitys.order.Sale;
import com.yr.order.dao.SaleDao;
import com.yr.order.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleDao saleDao;


    /**
     * Excel批量操作导入的销售订单
     * @param name
     * @param file
     * @return
     */
    public boolean batchImport(String name,MultipartFile file){
        boolean b = false;
        //创建处理EXCEL
        SaleImportExcel readExcel=new SaleImportExcel();
        //解析excel，获取销售信息集合。
        List<Sale> saleList = readExcel.getExcelInfo(name ,file);

        if(saleList != null){
            b = true;
        }

        //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
        for(Sale sale:saleList){
            saleDao.add(sale);
        }
        return b;
    }

    /**
     * 以分页的形式查询sale表的数据
     * @param page
     */
    @Override
    public void query(Page<SaleBo> page) {
        page.setTotalPage(saleDao.getCount(page));
        List<SaleBo>list = saleDao.query(page);
        page.setPageData(list);

    }

    @Override
    public void add(Sale sale) {
        saleDao.add(sale);
    }

    @Override
    public void update(Sale sale) {
        saleDao.update(sale);
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
    public Sale getById(Integer id) {
        return saleDao.getById(id);
    }




}
