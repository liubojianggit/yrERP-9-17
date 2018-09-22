package com.yr.log.dao;

import com.yr.entitys.Log.Log;
import com.yr.entitys.bo.LogBO.LogBo;
import com.yr.entitys.page.Page;

import java.util.List;

public interface LogDao {
    /**
     * 添加日志记录
     * @param log
     */
    public  void add(Log log);

    /**
     * 分页查询
     * @param logBoPage
     * @return
     */
    List<Log> query(Page<LogBo> logBoPage);
}
