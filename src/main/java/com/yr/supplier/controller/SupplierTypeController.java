package com.yr.supplier.controller;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.bo.supplierBO.SuppWareTypeBo;
import com.yr.entitys.bo.supplierBO.SupplierWareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.SuppWareType;
import com.yr.entitys.supplier.supplierWares;
import com.yr.supplier.service.SuppWareTypeService;
import com.yr.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable/list",method = RequestMethod.GET)
    public String getListPage(){
        return "";
    }
    /**
     * 用于跳转到添加页面和修改页面
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable/addAndUpdate",method = RequestMethod.GET)
    public String getAddPage(){
        return "";
    }

    /**
     * 供应商品类型添加方法，用于前台添加数据
     * @param suppWareType
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable",method =RequestMethod.POST)
    public String addWare(@ModelAttribute("suppWareType")SuppWareType suppWareType){
        swts.add(suppWareType);
        return "";
    }

    /**
     * 根据id来删除供应商品类型
     * @param id
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable",method = RequestMethod.DELETE)
    public String deleteWare(@PathVariable Integer id){
        swts.delete(id);
        return "";
    }

    /**
     * 根据id来修改供应商品类型的信息
     * @param suppWareType
     * @param map
     * @return
     */
    @RequestMapping(value = "suppWareTypeTable",method = RequestMethod.PUT)
    public String updateWare(@ModelAttribute("suppWareType") SuppWareType suppWareType, Map<String,Object>map){
        swts.update(suppWareType);
        return"";
    }
    /**
     * 供应商品类型查询方法，前台可以通过这个方法进行数据的查询
     * @param suppWareType
     * @param map
     * @return 查询数据
     */
    @RequestMapping(value = "suppWareTypeTable",method = RequestMethod.GET)
    @ResponseBody
    public Page<SuppWareTypeBo> queryWare(Page<SuppWareTypeBo> suppWareType, Map<String,Object>map){
        suppWareType = swts.query(suppWareType);
        map.put("suppWareType",suppWareType);
        return suppWareType;
    }

}
