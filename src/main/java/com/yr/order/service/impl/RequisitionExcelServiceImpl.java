package com.yr.order.service.impl;

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
        return false;
    }

    /*public static Map<Integer, List<ExcelBean>> contentExcel() {
        List<ExcelBean> excel = new ArrayList<>();
        Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook = null;
        //设置标题栏
        excel.add(new ExcelBean("申请ID", "id", 0));
        excel.add(new ExcelBean("申请编号", "code", 0));
        excel.add(new ExcelBean("审批人", "approver", 0));
        excel.add(new ExcelBean("收货人", "consignee", 0));
        excel.add(new ExcelBean("申请部门编号", "depa_code", 0));
        excel.add(new ExcelBean("收货仓库编号", "depot_code", 0));
        excel.add(new ExcelBean("申请人工号", "job_num", 0));
        excel.add(new ExcelBean("采购商品名称", "purc_ware_name", 0));
        excel.add(new ExcelBean("采购商品数量", "purc_ware_num", 0));
        excel.add(new ExcelBean("采购商品类型", "purc_ware_type", 0));
        excel.add(new ExcelBean("供应商编号", "supp_code", 0));
        excel.add(new ExcelBean("商品总价", "total_price", 0));
        excel.add(new ExcelBean("商品单价", "unit_price", 0));
        excel.add(new ExcelBean("采购单状态", "status", 0));
        excel.add(new ExcelBean("创建人", "createEmp", 0));
        excel.add(new ExcelBean("创建时间", "createTime", 0));
        excel.add(new ExcelBean("修改人", "updateEmp", 0));
        excel.add(new ExcelBean("修改时间", "updateTime", 0));
        map.put(0, excel);
        return map;
    }*/

}
