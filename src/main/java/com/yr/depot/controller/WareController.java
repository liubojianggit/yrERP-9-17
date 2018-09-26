package com.yr.depot.controller;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.bo.depotBo.WareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.depot.Ware;
import com.yr.depot.service.WareService;
import com.yr.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 仓库商品的controller层
 */
@Controller
@Repository(value = "wares")
public class WareController {
    @Autowired
    private WareService ws;
    @Autowired
    private JedisManager jedisManager;
    public static String path = "C:/Users/Administrator/Desktop/photo";//图片路径

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
     * 实现静态头像上传，这里先将图片上传到服务器上
     * @param file
     * @param request
     * @param response
     * @return Map<String,Object>
     * @throws IOException
     */
    @RequestMapping(value="/waresTable/upload",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadFile(@RequestParam("files") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long startTime = FileUtils.getTimeStamp();//获得当前时间的时间戳
        String path = request.getServletContext().getRealPath("/") + "photos";//获取服务器路径         --这里要改成服务器的路径
        String fileName = file.getOriginalFilename();//获得文件名
        fileName = startTime + fileName.substring(fileName.lastIndexOf("."), fileName.length());//构建出一个唯一的文件名
        File filepath=new File(path,fileName);//构建成一个file对象
        //判断目标文件所在的目录是否存在
        if(!filepath.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            filepath.getParentFile().mkdirs();
        }
        //将内存中的数据写入磁盘
        file.transferTo(filepath);
        Map<String,Object> map = new HashMap<>();
        map.put("url", request.getServletContext().getContextPath() + File.separator  + "photos" + File.separator + fileName);
        return map;
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
    public Page<WareBo> queryWare(Page<WareBo> ware, Map<String,Object>map){
        ware = ws.query(ware);
        map.put("ware",ware);
        return ware;
    }


}
