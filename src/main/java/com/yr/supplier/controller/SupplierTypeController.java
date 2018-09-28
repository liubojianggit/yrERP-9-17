package com.yr.supplier.controller;

import com.yr.entitys.bo.supplierBO.SuppWareTypeBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.SuppWareType;
import com.yr.supplier.service.SuppWareTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 供应商品类型页面和后台交互层（controller）
 */
@Controller
@RequestMapping(value = "suppWareType")
public class SupplierTypeController {
    @Autowired
    private SuppWareTypeService swts;


    @ModelAttribute
    public void Pojo (@RequestParam(value="id",required = false)Integer id, Map<String,Object> map){
        if (id!=null){
            map.put("suppWareType",swts.getSuppWareType(id));
        }
    }

    /**
     * 用于跳转数据查询页面
     * @return suppWareType/suppWareTypeTable
     */
    @RequestMapping(value = "suppWareTypeTable",method = RequestMethod.GET)
    public String getListPage(){
        return "supp_ware_typeList";
    }
    /**
     * 用于跳转到添加页面和修改页面
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable/add",method = RequestMethod.GET)
    public String getAddPage(Map<String ,Object>map){
        map.put("suppWareType",new SuppWareType());
        map.put("suppWareTypes",swts.getSuppWareType());
        return "supp_ware_typeAU";
    }
    @RequestMapping(value = "suppWareTypeTable/{id}",method = RequestMethod.GET)
    public String getUpdatePage(@PathVariable Integer id,Map<String ,Object>map){
        map.put("suppWareType",swts.getSuppWareType(id));
        map.put("suppWareTypes",swts.getSuppWareType());
        return "supp_ware_typeAU";
    }

    /**
     * 供应商品类型添加方法，用于前台添加数据
     * @param suppWareType
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable",method =RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addWare(@ModelAttribute("suppWareType")SuppWareType suppWareType){
        suppWareType.setCreateEmp("wangyong");
        boolean bool = swts.add(suppWareType);
        if(bool){
            return "{\"code\":1,\"msg\":\"添加成功\"}";
        }else{
            return "{\"code\":2,\"msg\":\"添加失败\"}";
        }
    }

    /**
     * 根据id来删除供应商品类型
     * @param id
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable/{id}",method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String deleteWare(@PathVariable Integer id){
        boolean bool = swts.delete(id);
        if(bool){
            return "{\"code\":1,\"msg\":\"删除成功\"}";
        }else{
            return "{\"code\":2,\"msg\":\"删除失败\"}";
        }
    }

    /**
     * 根据id来修改供应商品类型的信息
     * @param suppWareType
     * @param map
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable",method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String updateWare(@ModelAttribute("suppWareType") SuppWareType suppWareType, Map<String,Object>map){
        suppWareType.setUpdateEmp("wangyong9");
        boolean bool =  swts.update(suppWareType);
        if(bool){
            return "{\"code\":1,\"msg\":\"修改成功\"}";
        }else{
            return "{\"code\":2,\"msg\":\"修改失败\"}";
        }
    }
    /**
     * 供应商品类型查询方法，前台可以通过这个方法进行数据的查询
     * @param suppWareType
     * @param map
     * @return 查询数据
     */
    @RequestMapping(value = "suppWareTypeTable/list",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String  queryWare(Page<SuppWareTypeBo> suppWareType,SuppWareTypeBo suppWareTypeBo, Map<String,Object>map){
        suppWareType.setT(suppWareTypeBo);
        String  json = swts.query(suppWareType);
        map.put("suppWareType",json);
        return json;
    }

}
