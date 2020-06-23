package com.js.common.response;

import com.js.common.enums.StatusCode;
import lombok.Data;
import lombok.ToString;

/**
 * @Description:统一返回类
 * @Author 姜爽
 * @Date 15:18 2019/12/12
 */
@Data
@ToString
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T result;

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.result = data;
    }
}
