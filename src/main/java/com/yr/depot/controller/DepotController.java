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
import java.util.Map;

@Controller
@RequestMapping("/depot")
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
        if (id != null && id != 0) {
            Depot depot=service.getById(id);
            map.put("depot", depot);
        }
    }
    /**
     *分页查询仓库列表数据
     * @param depotbo 仓库对象
     * @return
     */
    @RequestMapping(value="/depotTable/list",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String query(Depotbo depotbo ,Page<Depotbo>page){
        page.setT(depotbo);//将bo类置入对象
        String json = service.query(page);//将list返回一个json字符串
        return json;
    }

    /**
     * 跳转仓库主页面
     * @return
     */
    @RequestMapping(value = "/depotTable",method=RequestMethod.GET)
    public String JumpDepot(){
        return "depotList";
    }

    /**
     * 操作的只是跳转仓库添加页面
     * @return
     */
    @RequestMapping(value="/depotTable/add",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String AddEcho(){

        return "depotAU";//添加页面的jsp前缀
    }

    /**
     * 保存仓库添加的数据
     * @param depot
     * @param map
     * @return
     */
    @RequestMapping(value="/depotTable",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public void add(Depot depot, Map<String, Object> map){

        service.add(depot);

    }

    /**
     * 根据id删除仓库数据
     * @return 返回分页查询页面
     */
    @RequestMapping(value = "/depotTable/{id}", method = RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public String delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return "{\"code\":1,\"msg\":\"删除" + "成功\"}";
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
        return "depotAU";
    }

    /**
     * 保存修改仓库
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/depotTable",method=RequestMethod.PUT,produces="application/json;charset=UTF-8")
    public String updates(Depot depot){
        service.update(depot);
        return "{\"code\":1,\"msg\":\"修改保存成功\"}";
    }
}
