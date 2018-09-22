package com.yr.entitys.bo.orderBO;

import com.yr.entitys.order.Sale;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

import java.util.Map;

public class ViewExcel extends AbstractXlsxView{
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        String fileName = "销售列表excel.xls";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "inline; filename="+new String(fileName.getBytes(),"iso8859-1"));
        OutputStream outputStream = response.getOutputStream();
        List<Sale> SaleList =  (List<Sale>) map.get("SaleList");
        // 产生Excel表头
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("基本信息");
        HSSFRow header = sheet.createRow(0);
        // 产生标题列
        header.createCell(0).setCellValue("订单id");
        header.createCell(1).setCellValue("销售订单编号");
        header.createCell(2).setCellValue("购买客户");
        header.createCell(3).setCellValue("销售员");
        header.createCell(4).setCellValue("销售商品编号");
        header.createCell(5).setCellValue("销售商品数量");
        header.createCell(6).setCellValue("销售金额");
        header.createCell(7).setCellValue("销售员联系电话");
        header.createCell(8).setCellValue("备注");
        header.createCell(9).setCellValue("销售单状态");

        HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));
        int rowNumber = 1;
        for (Sale sale: SaleList) {
            HSSFRow row = sheet.createRow(rowNumber++);
            // 产生标题列
            row.createCell(0).setCellValue(sale.getId());
            row.createCell(1).setCellValue(sale.getCode());
            row.createCell(2).setCellValue(sale.getCustomerBuy());
            row.createCell(3).setCellValue(sale.getSalesperson());
            row.createCell(4).setCellValue(sale.getDepotCode());
            row.createCell(5).setCellValue(sale.getNumber());
            row.createCell(6).setCellValue(sale.getMoney());
            row.createCell(7).setCellValue(sale.getsPhoneNumber());
            row.createCell(8).setCellValue(sale.getRemark());
            row.createCell(9).setCellValue(sale.getStates());
        }
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
