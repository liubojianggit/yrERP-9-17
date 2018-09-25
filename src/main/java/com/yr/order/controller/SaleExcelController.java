package com.yr.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.entitys.order.SaleOrder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yr.order.service.SaleExcelportService;
import com.yr.util.ExportExcelUtils;

@Controller
@RequestMapping("/SaleExcel")
public class SaleExcelController {// 销售订单的导出导入

    private static Log log = LogFactory.getLog(SaleExcelController.class);
    @Autowired
    private SaleExcelportService saleExcelport;

    /**
     * 导入Excel表
     *
     * @param file
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "Export", method = RequestMethod.POST)
    public String batchimport(@RequestParam(value = "filename") MultipartFile file, HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        log.info("AddController ..batchimport() start");
        // 判断文件是否为空
        if (file == null)
            return null;
        // 获取文件名
        String name = file.getOriginalFilename();
        // 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (name == null || ("").equals(name) && size == 0)
            return null;

        // 批量导入。参数：文件名，文件。
        boolean b = saleExcelport.batchImport(name, file);
        if (b) {
            String Msg = "批量导入EXCEL成功！";
            request.getSession().setAttribute("msg", Msg);
        } else {
            String Msg = "批量导入EXCEL失败！";
            request.getSession().setAttribute("msg", Msg);
        }
        return "saleList";
    }

    /**
     * Excel导出销售订单表记录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/Import", method = RequestMethod.POST)
    public ModelAndView exportExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            SaleOrder sale = new SaleOrder();
            // 查出用户数据
            List<SaleOrder> salelist = saleExcelport.queryForList();
            String title = "销售订单表";
            String[] rowsName = new String[] { "序号","订单id", "订单编号", "购买客户", "销售员", "销售商品编号", "销售商品数量", "销售金额", "销售员联系电话",
                    "备注", "销售单状态", "收货人", "审批人", "申请退货人姓名", "申请退货人联系电话", "销售商品的仓库编号", "创建人", "创建时间", "修改人", "修改时间" };

            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            for (int i = 0; i < salelist.size(); i++) {
                SaleOrder po = salelist.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i;// 序号
                objs[1] = po.getId();// 销售订单id
                objs[2] = po.getCode();// 销售订单编号
                objs[3] = po.getCustomerBuy(); // 购买客户
                objs[4] = po.getSalesperson();// 销售员
                objs[5] = po.getWareCode();// 销售商品编号
                objs[6] = po.getNumber(); // 销售商品数量
                objs[7] = po.getMoney();// 销售金额
                objs[8] = po.getsPhoneNumber();// 销售员联系电话
                objs[9] = po.getRemark(); // 备注
                objs[10] = po.getStates();// 销售单状态（0退货，1交易成功）
                objs[11] = po.getConsignee();// 收货人
                objs[12] = po.getApprover(); // 审批人
                objs[13] = po.getRequName();// 申请退货人姓名
                objs[14] = po.getrPhoneNumber();// 申请退货人联系电话
                objs[15] = po.getDepotCode(); // 销售商品的仓库编号
                objs[16] = po.getCreateEmp();// 创建人
                objs[17] = po.getCreateTime();// 创建时间
                objs[18] = po.getUpdateEmp();// 修改人
                objs[19] = po.getUpdateTime();// 修改时间
                dataList.add(objs);
            }
            // 工具类样式
            ExportExcelUtils ex = new ExportExcelUtils(title, rowsName, dataList, response);
            ex.exportData();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
