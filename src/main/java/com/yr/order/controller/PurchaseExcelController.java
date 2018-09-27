package com.yr.order.controller;

import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.order.SaleOrder;
import com.yr.order.service.PurchaseExcelService;
import com.yr.util.ExportExcelUtils;
import com.yr.util.ImportExcelUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchaseExcel")
public class PurchaseExcelController {
    @Autowired
    private PurchaseExcelService purchaseExcelServiceImpl;

    /**
     * 导出采购信息excel表；
     * @param request
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<PurchaseOrder> purchaseOrderList = purchaseExcelServiceImpl.queryForList();
            String tableName = "采购订单";
            String[] rowsName = new String[]{"序号","采购id","采购编号","采购人工号","采购部门编号","审批人",
            "采购商品名称","采购商品类型","采购商品数量","供应商编号","商品单价","商品总价","采购单状态","收货人","收货仓库编号"};
            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objects = null;
            for (int i = 0; i < purchaseOrderList.size(); i++) {
                PurchaseOrder pur = purchaseOrderList.get(i);
                objects = new Object[rowsName.length];
                objects[0] = i;
                objects[1] = pur.getId();
                objects[2] = pur.getCode();
                objects[3] = pur.getJobNumber();
                objects[4] = pur.getDepartmentCode();
                objects[5] = pur.getApprover();
                objects[6] = pur.getPurchasName();
                objects[7] = pur.getPurchaseType();
                objects[8] = pur.getPurchaseNumber();
                objects[9] = pur.getSupplierCode();
                objects[10] = pur.getUnitPrice();
                objects[11] = pur.getTotalPrice();
                objects[12] = pur.getStatus();
                objects[13] = pur.getConsignee();
                objects[14] = pur.getDepotCode();
                dataList.add(objects);
            }
            ExportExcelUtils ex = new ExportExcelUtils(tableName, rowsName, dataList, response);
            ex.exportData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将采购excel 表数据 导入到mysql 数据库中
     * @param excelFile
     * @param request
     * @param response
     * @return map
     */
    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Map<String,Object> analyzeXml(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request, HttpServletResponse response) {
        //上传xml文件
        InputStream inputs;
        boolean result = false;//导入标志
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //上传
            inputs = excelFile.getInputStream();
            String fileName = excelFile.getOriginalFilename();
            String filePath=request.getSession().getServletContext().getRealPath("uploadFile");
            //将excel表写入到硬盘文件夹
            String uploadFileName = ImportExcelUtils.uploadFile(inputs, fileName, filePath);

            //解析Excel，导入MySQL
            result = purchaseExcelServiceImpl.addExcel(filePath+"/"+uploadFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result){
            map.put("message", "成功");
        }else{
            map.put("message", "失败");
        }
        return map;
    }
}
