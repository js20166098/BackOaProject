package com.js.pojo.system;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author jiangshuang
 * @Description 系统日志表
 **/
@ToString
@Data
public class SysLog {
    /**
     * 主键uuid
     **/
    private String uuid;

    /**
     * 操作者
     **/
    private String operUser;

    /**
     * 用户操作
     **/
    private String operation;

    /**
     * 请求时长
     **/
    private String responseTime;

    /**
     * 操作相关类
     **/
    private String method;

    /**
     * 请求参数
     **/
    private String requestParams;

    /**
     * 访问者的IP
     **/
    private String ip;

    /**
     * 操作时间
     **/
    private Date createTime;
}