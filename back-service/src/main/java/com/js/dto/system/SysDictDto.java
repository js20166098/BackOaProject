package com.js.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.js.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 数据字典(com.js.pojo.system.SysDict)实体类
 *
 * @author 渡劫 dujie
 * @since 2021-05-10 15:58:52
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties
public class SysDictDto extends BasePageDto {
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