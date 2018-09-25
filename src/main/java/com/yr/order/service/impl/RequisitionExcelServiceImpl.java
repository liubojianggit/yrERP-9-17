package com.yr.order.service.impl;

import com.yr.entitys.bo.orderBO.RequisitionImportExcelBO;
import com.yr.entitys.order.Requisition;
import com.yr.order.dao.RequisitionExcelDao;
import com.yr.order.service.RequisitionExcelService;
import com.yr.util.ExcelBean;
import com.yr.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RequisitionExcelServiceImpl implements RequisitionExcelService {

    @Autowired
    private RequisitionExcelDao requisitionExcelDaoImpl;

    @Override
    public XSSFWorkbook exportExcelInfo(String headTextName) throws Exception {
        //根据条件查询数据，把数据装载到一个list中
        List<Requisition> list = requisitionExcelDaoImpl.queryForList();

        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map = requisitionExcelDaoImpl.contentExcel();
        //Map<Integer,List<ExcelBean>> map =ExcelServiceImpl.contentExcel();
        XSSFWorkbook xssfWorkbook=null;
        String sheetName = headTextName + "分享内容";
        //调用ExcelUtil的方法
        xssfWorkbook = ExcelUtil.createExcelFile(Requisition.class, list, map, sheetName);
        return xssfWorkbook;
    }

    @Override
    public boolean batchImport(String name, MultipartFile file) {
        boolean isvalue = false;
        //创建处理Excel
        RequisitionImportExcelBO requisitionImportExcelBO = new RequisitionImportExcelBO();
        //解析excel,获取采购信息集合
        List<Requisition> requisitionList = requisitionImportExcelBO.getExcelInfo(name,file);
        if (requisitionList !=null)
        {
            isvalue=true;
        }

        //迭代添加采购信息，
        for (Requisition requisition:requisitionList) {
             requisitionExcelDaoImpl.add(requisition);
        }
        return isvalue;
    }
}
