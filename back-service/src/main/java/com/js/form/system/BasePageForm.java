package com.js.form.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: 姜爽
 * @Description: 分页查询继承
 * @Date: 2020/5/4 10:01
 */
@Data
@ToString
public class BasePageForm {
    @ApiModelProperty("起始位置")
    private Integer offset = 0;

    @ApiModelProperty("偏移位置")
    private Integer limit = 10;
}
