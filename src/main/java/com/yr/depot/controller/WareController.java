package com.yr.depot.controller;

import com.yr.entitys.bo.depotBo.WareSearchBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.depot.Ware;
import com.yr.depot.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Repository(value = "wares")
public class WareController {
    @Autowired
    private WareService ws;
@ModelAttribute
    public void Pojo (@RequestParam(value="id",required = false)Integer id, Map<String,Object> map){
if (id!=null){
    map.put("ware",ws.getWare(id));
}
}
    /**
     * 用于跳转数据查询页面
     * @return
     */
    @RequestMapping(value = "waresTable/list",method = RequestMethod.GET)
    public String getListPage(){
        return "";
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
     * 商品添加方法，用于前台添加数据
     * @param ware
     * @return
     */
    @RequestMapping(value = "waresTable",method =RequestMethod.POST)
public String addWare(@ModelAttribute("ware")Ware ware){
   ws.add(ware);
    return "";
}

    /**
     * 根据id来删除商品
     * @param id
     * @return
     */
    @RequestMapping(value = "waresTable",method = RequestMethod.DELETE)
public String deleteWare(@PathVariable Integer id){
        ws.delete(id);
        return "";
}

    /**
     * 根据id来修改商品的信息
     * @param ware
     * @param map
     * @return
     */
    @RequestMapping(value = "waresTable",method = RequestMethod.PUT)
public String updateWare(@ModelAttribute("ware") Ware ware,Map<String,Object>map){
ws.update(ware);
        return"";
    }
    /**
     * 商品查询方法，前台可以通过这个方法进行数据的查询
     * @param ware
     * @param map
     * @return 查询数据
     */
    @RequestMapping(value = "waresTable",method = RequestMethod.GET)
    @ResponseBody
    public Page<WareSearchBo> queryWare(Page<WareSearchBo> ware, Map<String,Object>map){
        ware = ws.query(ware);
        map.put("ware",ware);
        return ware;
    }


}
