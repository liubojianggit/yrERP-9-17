package com.yr.log.service.impl;

import com.yr.entitys.Log.Log;
import com.yr.entitys.bo.LogBO.LogBo;
import com.yr.entitys.page.Page;
import com.yr.log.dao.LogDao;
import com.yr.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService{

    @Autowired
    private LogDao logDaoImpl;

    @Override
    public void add(Log log) {
            logDaoImpl.add(log);
    }

    @Override
    public List<Log> query(Page<LogBo> logBoPage) {
        return logDaoImpl.query(logBoPage);
    }
}
