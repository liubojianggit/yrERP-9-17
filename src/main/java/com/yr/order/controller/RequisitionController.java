package com.yr.order.controller;

import com.yr.entitys.bo.orderBO.RequisitionBO;
import com.yr.entitys.page.Page;
import com.yr.entitys.order.Requisition;
import com.yr.order.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 采购列表增删改查接口
 */
@Controller
@RequestMapping(value = "requisition")
public class RequisitionController {
    @Autowired
    private RequisitionService requisitionServiceImpl;

    @ModelAttribute
    public void modelAttribute(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null && id != 0) {
            Requisition requisition = requisitionServiceImpl.getRequisitionById(id);
            map.put("requisition", requisition);
        }
    }

    /**
     * 数据查询接口
     *
     * @return
     */
    @RequestMapping(value = "/requisitionTable/list", method = RequestMethod.GET)
    public String index() {
        return null;
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.GET)
    public Page<Requisition> query(RequisitionBO requisitionBO, Page<RequisitionBO> page) {
        page.setT(requisitionBO);
        Page<Requisition> page1 = requisitionServiceImpl.query(page);
        return page1;

    }

    /**
     * 添加数据接口；
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/requisitionTable/add", method = RequestMethod.GET)
    public String jumpAdd(Map<String, Object> map) {
        map.put("requisition", new Requisition());
        return null;
    }


    @RequestMapping(value = "/requisitionTable", method = RequestMethod.POST)
    public String add(Requisition requisition ,HttpServletRequest request) {
        //将时间戳设置进入创建时间
        requisition.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //将修改时间设进修改时间，初始修改时间，后期会改
        requisition.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //获取session 当中当前登录用户，session属性名从login登录的传过来，
        requisition.setCreateEmp((String) request.getSession().getAttribute(""));
        //这个初始的修改人，后期会改
        requisition.setUpdateEmp((String) request.getSession().getAttribute(""));

        requisitionServiceImpl.add(requisition);
        return null;
    }
    /**
     * 修改接口；
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/requisitionTable/update/{id}", method = RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map) {
        Requisition requisition = requisitionServiceImpl.getRequisitionById(id);
        map.put("requisition", requisition);
        return null;
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.PUT)
    public String update(@ModelAttribute("requisition") Requisition requisition,HttpServletRequest request) {
        //获取当前登录用户并把它设为修改人
        requisition.setUpdateEmp((String)request.getSession().getAttribute(""));
        //获取当前时间为数据修改时间；
        requisition.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        requisitionServiceImpl.update(requisition);
        return null;
    }

    /**
     * 这个是ajax 请求不要跳转，删除接口
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/requisitionTable/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        requisitionServiceImpl.delete(id);
        return null;
    }
}
