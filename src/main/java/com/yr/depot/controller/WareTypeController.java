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
     * 用于跳转到添加页面和修改页面
     * @return
     */
    @RequestMapping(value = "waresTable/addAndUpdate",method = RequestMethod.GET)
    public String getAddPage(){
        return "";
    }
    /**
     * 商品类型添加方法，用于前台添加数据
     *
     * @param waretype
     * @return
     */
    @RequestMapping(value = "ware_typeTable", method = RequestMethod.POST)
    public String addWare(@ModelAttribute("wareType") WareType waretype) {
        wts.add(waretype);
        return "";
    }

    /**
     * 根据id来删除商品类型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "ware_typeTable", method = RequestMethod.DELETE)
    public String deleteWare(@PathVariable Integer id) {
        wts.delete(id);
        return "";
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
        wts.update(wareType);
        return "";
    }

    /**
     * 商品类型查询方法，前台可以通过这个方法进行数据的查询
     *
     * @param wareType
     * @param map
     * @return 查询数据
     */
    @RequestMapping(value = "ware_typeTable/list", method = RequestMethod.GET)
    @ResponseBody
    public Page<WareType> queryWare(Page<WareType> wareType, WareTypeBo wareTypeBo, Map<String, Object> map) {
        wareType = wts.query(wareType);
        map.put("wareType", wareType);
        return wareType;
    }
}
