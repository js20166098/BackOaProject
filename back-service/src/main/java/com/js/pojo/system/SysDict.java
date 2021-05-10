package com.js.pojo.system;

import java.util.Date;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 数据字典(com.js.pojo.system.SysDict)实体类
 *
 * @author 渡劫 dujie
 * @since 2021-05-10 15:58:52
 */
@Data
public class SysDict {
    /**
     * ID
     */
    private Long id;
    /**
     * 字典名称
     */
    private String paramName;
    /**
     * 字典编码
     */
    private String code;
    /**
     * 字典key
     */
    private String paramKey;
    /**
     * 字典值
     */
    private String paramValue;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}