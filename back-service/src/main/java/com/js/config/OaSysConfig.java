package com.js.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 姜爽
 * @Description: OA系统常用配置信息
 * @Date: 2020/5/19 7:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "oa.config")
public class OaSysConfig {

    /** 邮件发送者 **/
    private String username;

    /** 编码格式 **/
    private String defaultEncoding;

    /** 节目文件上传路径 **/
    private String upfilePath;

    /** 配置开发环境拦截器开关 **/
    private Boolean interceptorSwitch = false;
}
