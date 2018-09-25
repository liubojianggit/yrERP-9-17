package com.yr.depot.controller;

import com.yr.depot.service.DepotService;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("depot")
public class DepotController {
    @Qualifier("depotServiceImpl")
    @Autowired
    private DepotService service;

    /**
     * 调用这个类的所有方法前都要执行有@ModelAttribute的方法
      @param id
     * @param map
     * @throws SQLException
     */
    @ModelAttribute
    public void query(@RequestParam(value ="id",required=false) Integer id,Map<String, Object> map) throws SQLException{// 绑定站位符
        if (id != null&&id !=0) {
            Depot depot=service.getById(id);
            map.put("depot", depot);
        }
    }
    /**
     *分页查询仓库列表数据
     * @param depotbo 仓库对象
     * @return
     */
    @RequestMapping(value="/depotTable",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Page<Depotbo> query(Depotbo depotbo ,Page<Depotbo>page){
       page.setT(depotbo);
        service.query(page);
        return page;
    }

    /**
     * 操作的只是跳转仓库添加页面
     * @return
     */
    @RequestMapping(value="/depotTable/add",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String AddEcho(){

        return "depotAdd";//添加页面的jsp前缀
    }

    /**
     * 保存仓库添加的数据，前提添加数据不能为空
     * @param depot
     * @param map
     * @return
     */
    @RequestMapping(value="/depotTable",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String add(Depot depot, Map<String, Object> map){
        boolean isNull =service.isNullAdd(depot);
        if(isNull == false){
            map.put("depot",depot);
            map.put("uperror", 1);//如果修改的值为空就不修改并且跳转到修改页面(重新刷新页面)
            return "depotAdd";
        }else{
            service.add(depot);
            return "depotList";
        }
    }
    /**
     * 根据id删除仓库数据
     * @return 返回分页查询页面
     */
    @RequestMapping(value = "/depotTable/{id}", method = RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "depotList";
    }
    /**
     * 根据id回显修改的用户
     * @param id
     * @param map 放入map中存放request，方便页面拿取
     * @return
     */
    @RequestMapping(value = "/depotTable/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String upEcho(@PathVariable Integer id,Map<String, Object> map,Depotbo depotbo,Page<Depotbo>page) {
        page.setT(depotbo);
        Depot depots = service.getById(id);
        map.put("depot",depots);
        map.put("page", page);
        return "depotAdd";
    }
    /**
     * 保存修改仓库
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value="/depotTable",method = RequestMethod.PUT,produces="application/json;charset=UTF-8")
    public String update(Depot depot
            ,@RequestParam("id")Integer id,Map<String, Object> map,Depotbo acon,Page<Depotbo>page){
        boolean isNull =service.isNullUpdate(depot);
        if(isNull == false ){
           Depot depot1 = service.getById(id);
            map.put("depot", depot1);
            map.put("uperror",2);
            return "depotAdd";
        }else{
            page.setT(acon);
            map.put("page", page);
            service.update(depot);
            return "depotList";
        }
    }
}
