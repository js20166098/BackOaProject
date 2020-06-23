package com.js.dto.system;

import com.js.dto.BasePageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @Author jiangshuang
 * @Description 系统日志表Dto类
 **/
@ToString
@Data
@EqualsAndHashCode(callSuper=true)
public class SysLogDto extends BasePageDto {

    @ApiModelProperty("日志主键")
    private String uuid;

    @ApiModelProperty("操作者")
    private String operUser;

    @ApiModelProperty("用户操作")
    private String operation;

    @ApiModelProperty("请求时长")
    private String responseTime;

    @ApiModelProperty("操作相关类")
    private String method;

    @ApiModelProperty("请求参数")
    private String requestParams;

    @ApiModelProperty("访问者的IP")
    private String ip;

    @ApiModelProperty("操作时间")
    private Date createTime;
}