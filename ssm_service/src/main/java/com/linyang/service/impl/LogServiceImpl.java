package com.linyang.service.impl;

import com.linyang.dao.LogDao;
import com.linyang.domian.SysLog;
import com.linyang.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;

    @Override
    public void save(SysLog sysLog) {
        logDao.save(sysLog);
    }
}
