package com.js.mapper.system;

import com.js.pojo.system.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author jiangshuang
 * @Description 系统日志相关Mapper
 **/
@Mapper
public interface SysLogMapper {

    /**
     * 添加日志
     **/
    int addSysLog(SysLog sysLog);

    /**
     * 查看所有的系统日志
     **/
    List<SysLog> showAllLog();
}