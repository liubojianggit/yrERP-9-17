package com.yr.order.controller;

import com.yr.order.service.RequisitionExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequisitionExcelController {
    @Autowired
    private RequisitionExcelService excelServiceImpl;


    /**
     * excel 导出
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exports")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tableName = "采购列表";
        if (tableName != "") {
            response.reset(); //清除buffer缓存
            Map<String, Object> map = new HashMap<String, Object>();
            // 指定下载的文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + tableName + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            XSSFWorkbook workbook = null;

            //导出Excel对象
            workbook = excelServiceImpl.exportExcelInfo(tableName);
            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 导入Excel表
     * @param file
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "batchimport", method = RequestMethod.POST)
    public String batchimport(@RequestParam(value="filename") MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;

        //批量导入。参数：文件名，文件。
        boolean b = excelServiceImpl.batchImport(name,file);
        if(b){
            String Msg ="批量导入EXCEL成功！";
            request.getSession().setAttribute("msg",Msg);
        }else{
            String Msg ="批量导入EXCEL失败！";
            request.getSession().setAttribute("msg",Msg);
        }
        return null;
    }
}
