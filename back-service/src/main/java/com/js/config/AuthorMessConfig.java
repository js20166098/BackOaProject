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
@ConfigurationProperties(prefix = "info")
public class AuthorMessConfig {


    /** 项目title **/
    private String title;

    /** 项目名称描述 **/
    private String description;

    /** 项目版本号 **/
    private String version;

    /** 作者名称 **/
    private String author;

    /** 配置url **/
    private String url;

    /** 开发者邮箱 **/
    private String email;
}
