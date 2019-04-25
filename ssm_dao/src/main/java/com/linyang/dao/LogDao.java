package com.linyang.dao;

import com.linyang.domian.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface LogDao {

    @Insert("insert into sys_log values(null, #{visitTime},#{username},#{ip},#{method})")
    void save(SysLog sysLog);
}
