package com.yr.depot.controller;

import com.yr.depot.service.WareTypeService;
import com.yr.entitys.bo.depotBo.WareTypeBo;
import com.yr.entitys.depot.WareType;
import com.yr.entitys.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 商品类型
 */
@Controller
@RequestMapping(value = "ware_type")
public class WareTypeController {
    @Autowired
    private WareTypeService wts;

    @ModelAttribute
    public void Pojo(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            map.put("wareType", wts.getWareType(id));
        }
    }
    /**
     * 用于跳转数据查询页面
     * @return
     */
    @RequestMapping(value = "ware_typeTable",method = RequestMethod.GET)
    public String getListPage(){
        return "/wareTypeList";
    }
    /**
     * 用于跳转到添加页面
     * @return
     */
    @RequestMapping(value = "ware_typeTable/add",method = RequestMethod.GET)
    public String getAddPage(Map<String,Object>map){
        map.put("wareType",new WareType());
        map.put("supCode",wts.getWareType());
        return "wareTypeAU";
    }
    /**
     * 用于跳转到修改页面
     */
    @RequestMapping(value = "ware_typeTable/{id}",method = RequestMethod.GET)
    public String getUpdatePage(@PathVariable Integer id,Map<String,Object>map){
        map.put("wareType",wts.getWareType(id));
        map.put("supCode",wts.getWareType());
        return "wareTypeAU";
    }
    /**
     * 商品类型添加方法，用于前台添加数据
     *
     * @param waretype
     * @return
     */
    @RequestMapping(value = "ware_typeTable", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addWare(@ModelAttribute("wareType") WareType waretype) {
        waretype.setCreateEmp("wangyong");
       boolean bool = wts.add(waretype);
        if(bool){
            return "{\"code\":1,\"msg\":\"添加成功\"}";
        }else{
            return "{\"code\":2,\"msg\":\"添加失败\"}";
        }
    }

    /**
     * 根据id来删除商品类型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "ware_typeTable/{id}", method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String deleteWare(@PathVariable Integer id) {
        boolean bool = wts.delete(id);
        if(bool){
            return "{\"code\":1,\"msg\":\"删除成功\"}";
        }else{
            return "{\"code\":2,\"msg\":\"删除失败\"}";
        }
    }

    /**
     * 根据id来修改商品类型的信息
     *
     * @param wareType
     * @param map
     * @return
     */
    @RequestMapping(value = "ware_typeTable", method = RequestMethod.PUT)
    public String updateWare(@ModelAttribute("wareType") WareType wareType, Map<String, Object> map) {
        wareType.setUpdateEmp("wangyong1");
        boolean bool = wts.update(wareType);
        if(bool){
            return "{\"code\":1,\"msg\":\"修改成功\"}";
        }else{
            return "{\"code\":2,\"msg\":\"修改失败\"}";
        }
    }

    /**
     * 商品类型查询方法，前台可以通过这个方法进行数据的查询
     *
     * @param wareType
     * @param map
     * @return 查询数据
     */
    @RequestMapping(value = "ware_typeTable/list", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String  queryWare(Page<WareType> page,WareType wareType , Map<String, Object> map) {
        page.setT(wareType);
        String json = wts.query(page);
        map.put("wareType", json);
        return json;
    }
}
